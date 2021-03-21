package simu.model;

/**
 * Luokan tarkoituksena on luoda vuoronumeroautomaatti ja antaa asiakkaille vuoronumero ja mille tiskille he menevät.
 *
 * @author Joni Tahvanainen ja Felix Uimonen
 * @version 1
 */

public class Vuoronumero {

    private Palvelupiste[] palvelupisteet;
    private double hajontaA;
    private double hajontaB;

    /**
     * luo vuoronumeroautomaatin.
     *
     * @param palvelupisteet palvelupisteet.
     * @param hajontaA Hajonta A
     * @param hajontaB Hajonta B
     */
    public Vuoronumero(Palvelupiste[] palvelupisteet, double hajontaA, double hajontaB){
        this.palvelupisteet = palvelupisteet;
        this.hajontaA = hajontaA;
        this.hajontaB = hajontaB;

    }

    /**
     * @return Mille tiskille uusi asiakas menee, joko Tiski A, Tiski B, Tiski C.
     */
    public String uusiAskiakas(){
        double rand = (Math.random() * 100);
        Asiakas asiakas = new Asiakas();
        if(rand < hajontaA){
            palvelupisteet[0].lisaaJonoon(asiakas);
            asiakas.setTiski("TiskiA");
            return "Tiski A";
        }
        else if(rand < hajontaB + hajontaA){
            palvelupisteet[3].lisaaJonoon(asiakas);
            asiakas.setTiski("TiskiB");
            return "Tiski B";
        }
        else if(rand <= 100){
            palvelupisteet[6].lisaaJonoon(asiakas);
            asiakas.setTiski("TiskiC");
            return "Tiski C";
        }
        return null;
    }

}
