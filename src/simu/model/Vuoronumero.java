package simu.model;

public class Vuoronumero {
    double a = 33.0;
    double b = 60.0;
    static int asiakasID = 1;
    Palvelupiste[] palvelupisteet;

    public Vuoronumero(Palvelupiste[] palvelupisteet){
        this.palvelupisteet = palvelupisteet;
    }

    public String uusiAskiakas(){
        double rand = (Math.random() * 100);
        Asiakas asiakas = new Asiakas(asiakasID++);
        if(rand < a){
            palvelupisteet[0].lisaaJonoon(asiakas);
            asiakas.setTiski("TiskiA");
            return "Tiski A";
        }
        else if(rand < b){
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

    public static int getAsiakasID(){return asiakasID;}

}
