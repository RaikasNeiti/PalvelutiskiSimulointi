package simu.model;

import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;
import ui.SimuAnimation;

import java.util.LinkedList;


public class OmaMoottori extends Moottori {

    SimuAnimation simu;
    private String vuorossajonoon;
    private Saapumisprosessi saapumisprosessi;
    private Vuoronumero vuoro;
    private Kello kello;
    private LinkedList<Asiakas> jonoA;
    private LinkedList<Asiakas> jonoB;
    private LinkedList<Asiakas> jonoC;
    private boolean auki = true;
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

    public OmaMoottori() {

        palvelupisteet = new Palvelupiste[9];
        kello = Kello.getInstance();
        jonoA = new LinkedList<>();
        jonoB = new LinkedList<>();
        jonoC = new LinkedList<>();


        saapumisprosessi = new Saapumisprosessi(new Negexp(5, 2), tapahtumalista, TapahtumanTyyppi.ARR1);
        vuoro = new Vuoronumero(palvelupisteet);
    }
    public void setAnim(SimuAnimation simu){
        this.simu = simu;
    }

    public void alustaTiskit(){
        palvelupisteet[0] = new Palvelupiste(new Normal(txtA_ap, txtA_ah), tapahtumalista, TapahtumanTyyppi.TISKI1, jonoA, cbA_a);
        palvelupisteet[1] = new Palvelupiste(new Normal(txtA_bp, txtA_bh), tapahtumalista, TapahtumanTyyppi.TISKI1, jonoA, cbA_b);
        palvelupisteet[2] = new Palvelupiste(new Normal(txtA_cp, txtA_ch), tapahtumalista, TapahtumanTyyppi.TISKI1, jonoA, cbA_c);
        palvelupisteet[3] = new Palvelupiste(new Normal(txtB_ap, txtB_ah), tapahtumalista, TapahtumanTyyppi.TISKI2, jonoB, cbB_a);
        palvelupisteet[4] = new Palvelupiste(new Normal(txtB_bp, txtB_bh), tapahtumalista, TapahtumanTyyppi.TISKI2, jonoB, cbB_b);
        palvelupisteet[5] = new Palvelupiste(new Normal(txtB_cp, txtB_ch), tapahtumalista, TapahtumanTyyppi.TISKI2, jonoB, cbB_c);
        palvelupisteet[6] = new Palvelupiste(new Normal(txtC_ap, txtC_ah), tapahtumalista, TapahtumanTyyppi.TISKI3, jonoC, cbC_a);
        palvelupisteet[7] = new Palvelupiste(new Normal(txtC_bp, txtC_bh), tapahtumalista, TapahtumanTyyppi.TISKI3, jonoC, cbC_b);
        palvelupisteet[8] = new Palvelupiste(new Normal(txtC_cp, txtC_ch), tapahtumalista, TapahtumanTyyppi.TISKI3, jonoC, cbC_c);
    }


    @Override
    protected void alustukset() {
        //alustukset
        auki = true;
        saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
    }

    @Override
    protected void suoritaTapahtuma(Tapahtuma t) {  // B-vaiheen tapahtumat

        Asiakas a;
        switch (t.getTyyppi()) {

            case ARR1:
                if (auki) {
                    vuorossajonoon = vuoro.uusiAskiakas();

                    saapumisprosessi.generoiSeuraava();
                    break;
                }
            case TISKI1:
            case TISKI2:
            case TISKI3:
                a = palvelupisteet[t.getPalvelija()].otaJonosta();
                a.setPoistumisaika(Kello.getInstance().getAika());
                a.raportti();
                break;
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
                TPalveluAika += palveluAika;
                palveluAika = 0;
                TAktiivinen += aktiivinen;
                aktiivinen = 0;
            } else if (i == 5) {
                System.out.println("TiskiB: " + round(100 * palveluAika / aktiivinen, 100));
                TPalveluAika += palveluAika;
                palveluAika = 0;
                TAktiivinen += aktiivinen;
                aktiivinen = 0;
            } else if (i == 8) {
                System.out.println("TiskiC: " + round(100 * palveluAika / aktiivinen, 100));
                TPalveluAika += palveluAika;
                TAktiivinen += aktiivinen;
                System.out.println("Kaikki tiskit: " + round(100 * TPalveluAika / TAktiivinen, 100));
            }
        }

    }

    public void suljeVuoronumero() {
        auki = false;
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
        simu.UpdateJonot(jonot);
        for (int i = 0; i < 9; i++ ){
            varattu[i] = palvelupisteet[i].onVarattu();
            aktiivinen[i] = palvelupisteet[i].onAktiivinen();
        }
        simu.UpdateTiskit(varattu, aktiivinen);
        simu.UpdateVuoronumero(vuorossajonoon);


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

    public void setTiskiAktiivinen(int i, boolean aktiivinen){
        palvelupisteet[i].vaihdaAktiivinen(aktiivinen);
    }


}
