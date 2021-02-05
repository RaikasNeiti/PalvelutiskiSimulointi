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
    private static long sum = 0;
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
        double keskiarvo =  1.0 * sum/id;
        //System.out.println("Asiakkaiden läpimenoaikojen keskiarvo "+ keskiarvo);
    }

}
