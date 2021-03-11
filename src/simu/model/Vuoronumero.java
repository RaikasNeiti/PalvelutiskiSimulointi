package simu.model;


public class Vuoronumero {
    double a = 33.0;
    double b = 60.0;
    private Palvelupiste[] palvelupisteet;
    private double hajontaA;
    private double hajontaB;

    public Vuoronumero(Palvelupiste[] palvelupisteet, double hajontaA, double hajontaB){
        this.palvelupisteet = palvelupisteet;
        this.hajontaA = hajontaA;
        this.hajontaB = hajontaB;

    }

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
