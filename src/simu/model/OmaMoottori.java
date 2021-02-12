package simu.model;

import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;

import java.util.LinkedList;


public class OmaMoottori extends Moottori{

    private Saapumisprosessi saapumisprosessi;
    private Vuoronumero vuoro;
    private Kello kello;
    private LinkedList<Asiakas> jonoA;
    private LinkedList<Asiakas> jonoB;
    private LinkedList<Asiakas> jonoC;
    private boolean auki = true;


    public OmaMoottori(){

        palvelupisteet = new Palvelupiste[9];
        kello = Kello.getInstance();
        jonoA = new LinkedList<>();
        jonoB = new LinkedList<>();
        jonoC = new LinkedList<>();


        palvelupisteet[0]=new Palvelupiste(new Normal(20,6), tapahtumalista, TapahtumanTyyppi.TISKI1, jonoA, true);
        palvelupisteet[1]=new Palvelupiste(new Normal(20,6), tapahtumalista, TapahtumanTyyppi.TISKI1, jonoA, true);
        palvelupisteet[2]=new Palvelupiste(new Normal(20,6), tapahtumalista, TapahtumanTyyppi.TISKI1, jonoA,false);
        palvelupisteet[3]=new Palvelupiste(new Normal(25,10), tapahtumalista, TapahtumanTyyppi.TISKI2, jonoB, true);
        palvelupisteet[4]=new Palvelupiste(new Normal(25,10), tapahtumalista, TapahtumanTyyppi.TISKI2, jonoB, true);
        palvelupisteet[5]=new Palvelupiste(new Normal(25,10), tapahtumalista, TapahtumanTyyppi.TISKI2, jonoB, false);
        palvelupisteet[6]=new Palvelupiste(new Normal(15,3), tapahtumalista, TapahtumanTyyppi.TISKI3, jonoC,true);
        palvelupisteet[7]=new Palvelupiste(new Normal(15,3), tapahtumalista, TapahtumanTyyppi.TISKI3, jonoC, true);
        palvelupisteet[8]=new Palvelupiste(new Normal(15,3), tapahtumalista, TapahtumanTyyppi.TISKI3, jonoC,false);

        saapumisprosessi = new Saapumisprosessi(new Negexp(5,2), tapahtumalista, TapahtumanTyyppi.ARR1);
        vuoro = new Vuoronumero(palvelupisteet);
    }


    @Override
    protected void alustukset() {
        //alustukset
        auki = true;
        saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
    }

    @Override
    protected void suoritaTapahtuma(Tapahtuma t){  // B-vaiheen tapahtumat

        Asiakas a;
        switch (t.getTyyppi()){

            case ARR1:
                if(auki) {
                    vuoro.uusiAskiakas();
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
                "Tiskillä A: " + Asiakas.getAsiakasA() + " asiakasta. Käyttössäoloprosentti "+ round((palvelupisteet[0].getKokonaisPalveluAika() / kello.getAika()) * 100, 100.0)   +"%\n" +
                "Tiskillä B: " + Asiakas.getAsiakasB() + " asiakasta. Käyttössäoloprosentti "+ round((palvelupisteet[3].getKokonaisPalveluAika() / kello.getAika())* 100, 100.0)  +"%\n" +
                "Tiskillä C: " + Asiakas.getAsiakasC() + " asiakasta. Käyttössäoloprosentti "+ round((palvelupisteet[6].getKokonaisPalveluAika() / kello.getAika())* 100, 100.0)  +"%\n" );
        Asiakas.loppuTulokset();
        for (Palvelupiste p: palvelupisteet){
            System.out.println("Käyttössäoloprosentti " + "palvelupiste " + p.getId() + " " +round(p.kaytossaoloProsentti(), 100));

        }
        System.out.println("Tiskien käyttössäoloprosenttit:");
        int i;
        double palveluAika = 0, aktiivinen = 0;
        double TPalveluAika = 0, TAktiivinen = 0;
        for(i = 0 ;i < 9; i++){
            palveluAika += palvelupisteet[i].getKokonaisPalveluAika();
            aktiivinen += palvelupisteet[i].getInactive();
            if(i==2){
                System.out.println("TiskiA: " + round(100 * palveluAika/aktiivinen, 100));
                TPalveluAika += palveluAika;
                palveluAika = 0;
                TAktiivinen += aktiivinen;
                aktiivinen = 0;
            }
            else if(i==5){
                System.out.println("TiskiB: " + round(100 * palveluAika/aktiivinen, 100));
                TPalveluAika += palveluAika;
                palveluAika = 0;
                TAktiivinen += aktiivinen;
                aktiivinen = 0;
            }
            else if(i==8){
                System.out.println("TiskiC: " + round(100 * palveluAika/aktiivinen, 100));
                TPalveluAika += palveluAika;
                TAktiivinen += aktiivinen;
                System.out.println("Kaikki tiskit: " + round(100 * TPalveluAika/TAktiivinen, 100));
            }
        }

    }
    public void suljeVuoronumero(){
        auki = false;
    }

    public void jononPituudet(){
        System.out.println("Jonon A pituus: " + jonoA.size());
        System.out.println("Jonon B pituus: " + jonoB.size());
        System.out.println("Jonon C pituus: " + jonoC.size());
    }

    public static double round(double luku, double tarkkuus){
        return Math.round(luku*tarkkuus)/tarkkuus;
    }




}
