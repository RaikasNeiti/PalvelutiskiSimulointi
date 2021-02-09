package simu.model;

import simu.framework.Kello;
import simu.framework.Trace;


// TODO:
// Asiakas koodataan simulointimallin edellyttämällä tavalla (data!)
public class Asiakas {
    private double saapumisaika;
    private double poistumisaika;
    private int id;
    private static int i = 1;
    private static double sum = 0, sumA = 0, sumB = 0, sumC = 0;
    static int AsiakasA = 0, AsiakasB = 0, AsiakasC= 0;
    private String tiski;

    public Asiakas(){
        id = i++;

        saapumisaika = Kello.getInstance().getAika();
        Trace.out(Trace.Level.INFO, "Uusi asiakas:" + id + ":"+saapumisaika);
    }

    public double getPoistumisaika() {
        return poistumisaika;
    }

    public void setPoistumisaika(double poistumisaika) {
        this.poistumisaika = poistumisaika;
    }

    public double getSaapumisaika() {
        return saapumisaika;
    }

    public void setSaapumisaika(double saapumisaika) {
        this.saapumisaika = saapumisaika;
    }

    public void setTiski(String tiski){this.tiski = tiski;}

    public void raportti(){
        //Trace.out(Trace.Level.INFO, "Asiakas "+id+ " saapui:" +saapumisaika);
        //Trace.out(Trace.Level.INFO,"Asiakas "+id+ " poistui:" +poistumisaika);
        //Trace.out(Trace.Level.INFO,"Asiakas "+id+ " viipyi:" +(poistumisaika-saapumisaika));
        Trace.out(Trace.Level.INFO,"Asiakas käytti "+ tiski + ":");
        sum += (poistumisaika-saapumisaika);
        switch(tiski){
            case "TiskiA":
                sumA += (poistumisaika-saapumisaika);
                AsiakasA++;
                break;
            case "TiskiB":
                sumB += (poistumisaika-saapumisaika);
                AsiakasB++;
                break;
            case "TiskiC":
                sumC += (poistumisaika-saapumisaika);
                AsiakasC++;
                break;
        }

    }
    public static void loppuTulokset(){
        double keskiarvo =  OmaMoottori.round( sum/i, 100.0);
        System.out.println("Asiakkaiden läpimenoaikojen keskiarvo "+ keskiarvo);
        System.out.println("Tiskillä A: " + OmaMoottori.round( sumA /AsiakasA, 100.0));
        System.out.println("Tiskillä B: " + OmaMoottori.round( sumB /AsiakasB, 100.0));
        System.out.println("Tiskillä C: " + OmaMoottori.round( sumC /AsiakasC, 100.0));
    }
    public static int getAsiakasA(){
        return AsiakasA;
    }
    public static int getAsiakasB(){
        return AsiakasB;
    }
    public static int getAsiakasC(){
        return AsiakasC;
    }

}
