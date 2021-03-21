package simu.model;

import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;
import ui.SimuAnimation;

import java.util.LinkedList;

import static javafx.application.Platform.runLater;

/**
 * Simulaation läpiviemiseen käytettävä luokka
 *
 * @author Joni Tahvanainen ja Felix Uimonen
 * @version 1
 */
public class OmaMoottori extends Moottori implements IOmaMoottori{
    private DataAccessObject dao;
    private SimuAnimation simu;
    private String vuorossajonoon;
    private Saapumisprosessi saapumisprosessi;
    private Vuoronumero vuoro;
    private Kello kello;
    private LinkedList<Asiakas> jonoA;
    private LinkedList<Asiakas> jonoB;
    private LinkedList<Asiakas> jonoC;

    private double dAika = 0;
    private boolean auki = true;
    private double speed = 500;

    private boolean[] checkboxes;
    private double[] palveluajat;
    private double[] hajonnat;
    private double hajontaA;
    private double hajontaB;
    private long saapumisväliaika;
    private long saapumishajonta;

    protected double jononAPituus = 0;
    protected double jononBPituus = 0;
    protected double jononCPituus = 0;
    protected double kelloSuljettu;
    protected double käyttöprosentti;
    protected double käyttöprosenttiA;
    protected double käyttöprosenttiB;
    protected double käyttöprosenttiC;
    protected double loppuAika ;
    protected int asiakkaidenMäärä;
    protected int määräA;
    protected int määräB;
    protected int määräC;
    protected double keskiaika;
    protected double keskiaikaA;
    protected double keskiaikaB;
    protected double keskiaikaC;
    protected double jononKeskipituusA;
    protected double jononKeskipituusB;
    protected double jononKeskipituusC;

    private boolean pause = false;

    public OmaMoottori() {

        palvelupisteet = new Palvelupiste[9];
        kello = Kello.getInstance();
        jonoA = new LinkedList<>();
        jonoB = new LinkedList<>();
        jonoC = new LinkedList<>();
        dao = new DataAccessObject(this);



    }

    /**
     * Simullattorin UI:n controllerin asetaaminen
     * @param simu Simullation UI:n kontrolleri
     */
    public void setAnim(SimuAnimation simu){
        this.simu = simu;
    }

    /**
     * Luo Simulaatiossa käytettävien palvelupisteiden ja saapumisprosessin luonti
     */
    public void alustaTiskit(){
        palvelupisteet[0] = new Palvelupiste(new Normal(palveluajat[0], hajonnat[0]), tapahtumalista, TapahtumanTyyppi.TISKI1, jonoA, checkboxes[0]);
        palvelupisteet[1] = new Palvelupiste(new Normal(palveluajat[1], hajonnat[1]), tapahtumalista, TapahtumanTyyppi.TISKI1, jonoA, checkboxes[1]);
        palvelupisteet[2] = new Palvelupiste(new Normal(palveluajat[2], hajonnat[2]), tapahtumalista, TapahtumanTyyppi.TISKI1, jonoA, checkboxes[2]);
        palvelupisteet[3] = new Palvelupiste(new Normal(palveluajat[3], hajonnat[3]), tapahtumalista, TapahtumanTyyppi.TISKI2, jonoB, checkboxes[3]);
        palvelupisteet[4] = new Palvelupiste(new Normal(palveluajat[4], hajonnat[4]), tapahtumalista, TapahtumanTyyppi.TISKI2, jonoB, checkboxes[4]);
        palvelupisteet[5] = new Palvelupiste(new Normal(palveluajat[5], hajonnat[5]), tapahtumalista, TapahtumanTyyppi.TISKI2, jonoB, checkboxes[5]);
        palvelupisteet[6] = new Palvelupiste(new Normal(palveluajat[6], hajonnat[6]), tapahtumalista, TapahtumanTyyppi.TISKI3, jonoC, checkboxes[6]);
        palvelupisteet[7] = new Palvelupiste(new Normal(palveluajat[7], hajonnat[7]), tapahtumalista, TapahtumanTyyppi.TISKI3, jonoC, checkboxes[7]);
        palvelupisteet[8] = new Palvelupiste(new Normal(palveluajat[8], hajonnat[8]), tapahtumalista, TapahtumanTyyppi.TISKI3, jonoC, checkboxes[8]);
        vuoro = new Vuoronumero(palvelupisteet, hajontaA, hajontaB);
        saapumisprosessi = new Saapumisprosessi(new Negexp(saapumisväliaika, saapumishajonta), tapahtumalista, TapahtumanTyyppi.ARR1);
    }

    /**
     * Muuttaa simulaation läpikäymisnopeutta
     * @param muutos Simullaation nopuden muutos suunta
     */
    public void setSpeed(boolean muutos){
        if(muutos){
            speed = speed * 0.9;
        }
        else{
            speed = speed * 1.1;
        }

    }

    /**
     * paluttaa simulaation kierrosten välisen viiveen
     * @return viiveen pituus
     */
    public int getSpeed(){
        return (int) speed;
    }


    /**
     *  Simulaation nollaus ja uuden simulaation käynnistys
     */
    @Override
    protected void alustukset() {
        auki = true;
        kello.setAika(0);
        jonoA.clear();
        jonoB.clear();
        jonoC.clear();
        tapahtumalista.nollaa();
        Asiakas.nollaaAsiakkaat();
        Palvelupiste.nollaa();
        dAika=0;
        jononAPituus = 0;
        jononBPituus = 0;
        jononCPituus = 0;
        for(Palvelupiste p : palvelupisteet){
            p.nollaaTulokset();
        }
        saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
    }

    /**
     * Tapahtuman käsittely ja sen seuraukset
     * @param t Käsiteltävä tapahtuma
     */
    @Override
    protected void suoritaTapahtuma(Tapahtuma t) {  // B-vaiheen tapahtumat

        Asiakas a;
        switch (t.getTyyppi()) {

            case ARR1:
                if (auki) {
                    runLater(() ->  simu.setSaapui());
                    vuorossajonoon = vuoro.uusiAskiakas();
                    saapumisprosessi.generoiSeuraava();
                }
                break;
            case TISKI1:
            case TISKI2:
            case TISKI3:
                a = palvelupisteet[t.getPalvelija()].otaJonosta();
                a.setPoistumisaika(Kello.getInstance().getAika());
                a.raportti();
                runLater(() -> simu.SetConsole(a.getId(), a.getPoistumisaika(), a.getTiski()));
                dAika = kello.getAika();
                break;
        }

        if(auki) {
            jononAPituus += (kello.getAika() - dAika) * jonoA.size();
            jononBPituus += (kello.getAika() - dAika) * jonoB.size();
            jononCPituus += (kello.getAika() - dAika) * jonoC.size();
            dAika = kello.getAika();
        }

    }


    /**
     * Simulaation tuloksien käsittelyä
     */
    @Override
    protected void tulokset() {
        //System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());

        System.out.println("Tulokset\n" +
                "Tiskillä A: " + Asiakas.getAsiakasA() + " asiakasta.\n" +
                "Tiskillä B: " + Asiakas.getAsiakasB() + " asiakasta.\n" +
                "Tiskillä C: " + Asiakas.getAsiakasC() + " asiakasta.\n");
        for (Palvelupiste p : palvelupisteet) {
            System.out.println("Käyttössäoloprosentti " + "palvelupiste " + p.getId() + " " + round(p.kaytossaoloProsentti(), 100));
        }
        System.out.println("Tiskien käyttössäoloprosenttit:");
        int i;
        double palveluAika = 0, aktiivinen = 0;
        double TPalveluAika = 0, TAktiivinen = 0;
        for (i = 0; i < 9; i++) {
            palveluAika += palvelupisteet[i].getKokonaisPalveluAika();
            aktiivinen += palvelupisteet[i].getInactive();
            if (i == 2) {
                System.out.println("TiskiA: " + round(100 * palveluAika / aktiivinen, 100));
                käyttöprosenttiA = round(100 * palveluAika / aktiivinen, 100);
                TPalveluAika += palveluAika;
                palveluAika = 0;
                TAktiivinen += aktiivinen;
                aktiivinen = 0;
            } else if (i == 5) {
                System.out.println("TiskiB: " + round(100 * palveluAika / aktiivinen, 100));
                käyttöprosenttiB = round(100 * palveluAika / aktiivinen, 100);
                TPalveluAika += palveluAika;
                palveluAika = 0;
                TAktiivinen += aktiivinen;
                aktiivinen = 0;
            } else if (i == 8) {
                System.out.println("TiskiC: " + round(100 * palveluAika / aktiivinen, 100));
                käyttöprosenttiC = round(100 * palveluAika / aktiivinen, 100);
                TPalveluAika += palveluAika;
                TAktiivinen += aktiivinen;
                System.out.println("Kaikki tiskit: " + round(100 * TPalveluAika / TAktiivinen, 100));
                käyttöprosentti = round(100 * TPalveluAika / TAktiivinen, 100);
            }
        }
        tallenaDatabaseen();
        runLater(() -> simu.closedown());

    }

    /**
     * Vuoronumeroautomaatin sulkeminen simulasatioajan päätyttyä
     */
    public void suljeVuoronumero() {
        auki = false;
        kelloSuljettu = kello.getAika();
        runLater(() -> simu.suljeVuoronumero());

    }

    /**
     * UI:n päivittäminen
     */
    public void UpdateUi() {
        int[] jonot = new int[3];
        boolean[] varattu = new boolean[9];
        boolean[] aktiivinen = new boolean[9];
        System.out.println("Jonon A pituus: " + jonoA.size());
        jonot[0] = jonoA.size();
        System.out.println("Jonon B pituus: " + jonoB.size());
        jonot[1] = jonoB.size();
        System.out.println("Jonon C pituus: " + jonoC.size());
        jonot[2] = jonoC.size();
        runLater(() -> simu.UpdateJonot(jonot));
        for (int i = 0; i < 9; i++ ){
            varattu[i] = palvelupisteet[i].onVarattu();
            aktiivinen[i] = palvelupisteet[i].onAktiivinen();
        }
        runLater(() -> simu.UpdateTiskit(varattu, aktiivinen));
        if(auki) {
            runLater(() -> simu.UpdateVuoronumero(vuorossajonoon));
        }

    }

    /**
     * Lukujen pyöristukseen käytettävä funktio
     * @param luku Pöyristettävä luku
     * @param tarkkuus Kuinka monta decimaalia pyöristetyssä luvussa on
     * @return Pyöristetty luku
     */
    public static double round(double luku, double tarkkuus) {
        return Math.round(luku * tarkkuus) / tarkkuus;
    }

    /**
     * Simulaation läpikäymisen pysättäminen ja jatkaminen
     */
    public void setPause(){
        if(!pause){
            pause = true;
        }
        else{
            pause = false;
        }


    }


    /**
     * Simulaation tulosten tallentaminen tietokantaan.
     */
    public void tallenaDatabaseen(){
        loppuAika = round(kello.getAika(),100);
        asiakkaidenMäärä = Asiakas.getMäärä();
        määräA = Asiakas.getAsiakasA();
        määräB = Asiakas.getAsiakasB();
        määräC = Asiakas.getAsiakasC();
        keskiaika = round(Asiakas.getSum() / asiakkaidenMäärä, 100);
        keskiaikaA = round(Asiakas.getSumA() / määräA, 100);
        keskiaikaB = round(Asiakas.getSumB() / määräB, 100);
        keskiaikaC = round(Asiakas.getSumC() / määräC, 100);
        jononKeskipituusA = round(jononAPituus / kelloSuljettu,100);
        jononKeskipituusB = round(jononBPituus / kelloSuljettu,100);
        jononKeskipituusC = round(jononCPituus / kelloSuljettu,100);
        System.out.println(jononKeskipituusA);
        System.out.println(jononKeskipituusB);
        System.out.println(jononKeskipituusC);
        dao.lisääTulokset();


    }

    /**
     * Vaihtaa halutun palvelpisteen tilaa
     * @param i Palvelupisteen indeksi
     * @param aktiivinen Palvelupisteen nykyinen tila
     */
    public void setTiskiAktiivinen(int i, boolean aktiivinen){
        palvelupisteet[i].vaihdaAktiivinen(aktiivinen);
    }

    /**
     *
     * @return palauttaa
     */
    public boolean getPause(){return pause;}


    /**
     * palvelupisteiden aktiivisuuden asettaminen
     * @param checkbox taulukko joka sisältää palvelupisteiden aktiivisuuden tilan
     */
    public void setCheckbox(boolean[] checkbox) {
        this.checkboxes = checkbox;
    }

    /**
     * palvelupisteiden palveluaikojen asettaminen
     * @param palveluajat taulukko joka sisältää palvelupisteiden palveluajat
     */
    public void setPalveluajat(double[] palveluajat) {
        this.palveluajat = palveluajat;
    }

    /**
     * * Palvelupisteiden palveluaikojen hajonnan asettaminen
     * @param hajonnat Taulukko joka sisältää palvelupisteiden palveluaikojen hajonnat
     */
    public void setHajonnat(double[] hajonnat) {
        this.hajonnat = hajonnat;
    }

    /**
     * Tiskien välisen jakuman muuttujien asettaminen
     * @param hajontaA Tiskille A menevien asiakkaiden osuus prosenteina
     * @param hajontaB Tiskille B menevien asiakkaiden osuus prosenteina
     */
    public void setJakaumat(double hajontaA, double hajontaB){
        this.hajontaA = hajontaA;
        this.hajontaB = hajontaB;
    }

    /**
     * Asiakaiden saapmustiheyden asettaaminen
     * @param saapumisväliaika sappumisväliaikojen keskiarvo
     * @param saapumishajonta saapumiväliaikojen hajonta
     */
    public void setSaapumistiheys(long saapumisväliaika, long saapumishajonta){
        this.saapumisväliaika = saapumisväliaika;
        this.saapumishajonta = saapumishajonta;
    }
}
