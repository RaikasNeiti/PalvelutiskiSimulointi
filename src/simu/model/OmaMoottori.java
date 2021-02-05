package simu.model;

import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;

public class OmaMoottori extends Moottori{

    private Saapumisprosessi saapumisprosessi;
    Vuoronumero vuoro;


    public OmaMoottori(){

        palvelupisteet = new Palvelupiste[3];


        palvelupisteet[0]=new Palvelupiste(new Normal(10,6), tapahtumalista, TapahtumanTyyppi.TISKI1);
        palvelupisteet[1]=new Palvelupiste(new Normal(10,10), tapahtumalista, TapahtumanTyyppi.TISKI2);
        palvelupisteet[2]=new Palvelupiste(new Normal(5,3), tapahtumalista, TapahtumanTyyppi.TISKI3);

        saapumisprosessi = new Saapumisprosessi(new Negexp(15,5), tapahtumalista, TapahtumanTyyppi.ARR1);
        vuoro = new Vuoronumero(palvelupisteet);
    }


    @Override
    protected void alustukset() {
        saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
    }

    @Override
    protected void suoritaTapahtuma(Tapahtuma t){  // B-vaiheen tapahtumat

        Asiakas a;
        switch (t.getTyyppi()){

            case ARR1: vuoro.uusiAskiakas();
                saapumisprosessi.generoiSeuraava();
                break;
            case TISKI1: a = palvelupisteet[0].otaJonosta();
                a.setPoistumisaika(Kello.getInstance().getAika());
                a.raportti();
                break;
            case TISKI2: a = palvelupisteet[1].otaJonosta();
                a.setPoistumisaika(Kello.getInstance().getAika());
                a.raportti();
                break;
            case TISKI3:
                a = palvelupisteet[2].otaJonosta();
                a.setPoistumisaika(Kello.getInstance().getAika());
                a.raportti();
        }
    }


    @Override
    protected void tulokset() {
        System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
        System.out.println("Tulokset ... puuttuvat vielä");
    }


}
