package simu.model;

import simu.framework.Kello;
import simu.framework.Trace;

import javax.persistence.*;

@Entity
@Table(name="asiakas")
// Asiakas koodataan simulointimallin edellyttämällä tavalla (data!)
public class Asiakas {
    @Id
    @Column(name ="id")
    private int id;
    @Column(name ="saapumisaika")
    private double saapumisaika;
    @Column(name ="poistumisaika")
    private double poistumisaika;

    private static double sum = 0, sumA = 0, sumB = 0, sumC = 0;
    static int AsiakasA = 0, AsiakasB = 0, AsiakasC= 0;
    static int i = 1;

    @Column(name ="tiski")
    private String tiski;

    public Asiakas(){
        this.id = i;

        saapumisaika = Kello.getInstance().getAika();
        Trace.out(Trace.Level.INFO, "Uusi asiakas:" + id + ":"+saapumisaika);
    }

    public static int getMäärä() {return i;}

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
    public String getTiski() {return tiski; }

    public void raportti(){
        //Trace.out(Trace.Level.INFO, "Asiakas "+id+ " saapui:" +saapumisaika);
        Trace.out(Trace.Level.INFO,"Asiakas "+id+ " poistui:" +poistumisaika);
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

    public int getId() {
        return id;
    }


    public static double getSum() {
        return sum;
    }

    public static double getSumA() {
        return sumA;
    }

    public static double getSumB() {
        return sumB;
    }

    public static double getSumC() {
        return sumC;
    }

    public static void nollaaAsaikkaat(){
        sum = 0;
        sumA = 0;
        sumB = 0;
        sumC = 0;
        i = 1;
        AsiakasA = 0;
        AsiakasB = 0;
        AsiakasC = 0;
    }
}
