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


public class OmaMoottori extends Moottori implements IOmaMoottori{
    DataAccessObject dao;
    SimuAnimation simu;
    private String vuorossajonoon;
    private Saapumisprosessi saapumisprosessi;
    private Vuoronumero vuoro;
    private Kello kello;
    private LinkedList<Asiakas> jonoA;
    private LinkedList<Asiakas> jonoB;
    private LinkedList<Asiakas> jonoC;
    private SimuAnimation anim;
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


    private boolean cbA_a;
    private double txtA_ap;
    private double txtA_ah;
    private boolean cbA_b;
    private double txtA_bp;
    private double txtA_bh;
    private boolean cbA_c;
    private double txtA_cp;
    private double txtA_ch;

    private boolean cbB_a;
    private double txtB_ap;
    private double txtB_ah;
    private boolean cbB_b;
    private double txtB_bp;
    private double txtB_bh;
    private boolean cbB_c;
    private double txtB_cp;
    private double txtB_ch;

    private boolean cbC_a;
    private double txtC_ap;
    private double txtC_ah;
    private boolean cbC_b;
    private double txtC_bp;
    private double txtC_bh;
    private boolean cbC_c;
    private double txtC_cp;
    private double txtC_ch;

    private boolean pause = false;

    public OmaMoottori() {

        palvelupisteet = new Palvelupiste[9];
        kello = Kello.getInstance();
        jonoA = new LinkedList<>();
        jonoB = new LinkedList<>();
        jonoC = new LinkedList<>();
        dao = new DataAccessObject(this);



    }
    public void setAnim(SimuAnimation simu){
        this.simu = simu;
    }

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
    public void setSpeed(boolean muutos){
        if(muutos){
            speed = speed * 0.9;
        }
        else{
            speed = speed * 1.1;
        }

    }

    public int getSpeed(){
        return (int) speed;
    }

  


    @Override
    protected void alustukset() {
        //alustukset
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


    @Override
    protected void tulokset() {
        //System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());

        System.out.println("Tulokset\n" +
                "Tiskillä A: " + Asiakas.getAsiakasA() + " asiakasta.\n" +
                "Tiskillä B: " + Asiakas.getAsiakasB() + " asiakasta.\n" +
                "Tiskillä C: " + Asiakas.getAsiakasC() + " asiakasta.\n");
        Asiakas.loppuTulokset();
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

    public void suljeVuoronumero() {
        auki = false;
        kelloSuljettu = kello.getAika();
        runLater(() -> simu.suljeVuoronumero());

    }

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

    public static double round(double luku, double tarkkuus) {
        return Math.round(luku * tarkkuus) / tarkkuus;
    }

    public void setTiskiA(boolean cbA_a, double txtA_ap, double txtA_ah, boolean cbA_b, double txtA_bp, double txtA_bh, boolean cbA_c, double txtA_cp ,double txtA_ch){
        this.cbA_a = cbA_a;
        this.txtA_ap = txtA_ap;
        this.txtA_ah = txtA_ah;
        this.cbA_b = cbA_b;
        this.txtA_bp = txtA_bp;
        this.txtA_bh = txtA_bh;
        this.cbA_c = cbA_c;
        this.txtA_cp = txtA_cp;
        this.txtA_ch = txtA_ch;
    }

    public void setTiskiB(boolean cbB_a, double txtB_ap, double txtB_ah, boolean cbB_b, double txtB_bp, double txtB_bh, boolean cbB_c, double txtB_cp ,double txtB_ch){
        this.cbB_a = cbB_a;
        this.txtB_ap = txtB_ap;
        this.txtB_ah = txtB_ah;
        this.cbB_b = cbB_b;
        this.txtB_bp = txtB_bp;
        this.txtB_bh = txtB_bh;
        this.cbB_c = cbB_c;
        this.txtB_cp = txtB_cp;
        this.txtB_ch = txtB_ch;
    }

    public void setTiskiC(boolean cbC_a, double txtC_ap, double txtC_ah, boolean cbC_b, double txtC_bp, double txtC_bh, boolean cbC_c, double txtC_cp ,double txtC_ch){
        this.cbC_a = cbC_a;
        this.txtC_ap = txtC_ap;
        this.txtC_ah = txtC_ah;
        this.cbC_b = cbC_b;
        this.txtC_bp = txtC_bp;
        this.txtC_bh = txtC_bh;
        this.cbC_c = cbC_c;
        this.txtC_cp = txtC_cp;
        this.txtC_ch = txtC_ch;
    }

    public void setPause(){
        if(!pause){
            pause = true;
        }
        else{
            pause = false;
        }
        simu.closedown();

    }


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

    public void setTiskiAktiivinen(int i, boolean aktiivinen){
        palvelupisteet[i].vaihdaAktiivinen(aktiivinen);
    }

    public boolean getPause(){return pause;}


    public void setCheckbox(boolean[] checkbox) {
        this.checkboxes = checkbox;
    }

    public void setPalveluajat(double[] palveluajat) {
        this.palveluajat = palveluajat;
    }

    public void setHajonnat(double[] hajonnat) {
        this.hajonnat = hajonnat;
    }

    public void setJakaumat(double hajontaA, double hajontaB){
        this.hajontaA = hajontaA;
        this.hajontaB = hajontaB;
    }

    public void setSaapumistiheys(long saapumisväliaika, long saapumishajonta){
        this.saapumisväliaika = saapumisväliaika;
        this.saapumishajonta = saapumishajonta;
    }
}
