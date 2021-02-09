package simu.model;

public class Vuoronumero {
    double a = 33.0;
    double b = 60.0;

    Palvelupiste[] palvelupisteet;

    public Vuoronumero(Palvelupiste[] palvelupisteet){
        this.palvelupisteet = palvelupisteet;
    }

    public void uusiAskiakas(){
        double rand = (Math.random() * 100);
        Asiakas asiakas = new Asiakas();
        if(rand < a){
            palvelupisteet[0].lisaaJonoon(asiakas);
            asiakas.setTiski("TiskiA");
        }
        else if(rand < b){
            palvelupisteet[1].lisaaJonoon(asiakas);
            asiakas.setTiski("TiskiB");
        }
        else if(rand <= 100){
            palvelupisteet[2].lisaaJonoon(asiakas);
            asiakas.setTiski("TiskiC");
        }
    }

}
