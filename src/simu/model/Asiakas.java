package simu.model;

import simu.framework.Kello;
import simu.framework.Trace;


/**
 * Luokan tarkoitus
 *
 * @author Joni Tahvanainen ja Felix Uimonen
 * @version 1
 */
public class Asiakas {

    /**
     * Asikkaiden yksilöintiin käytettävä numero
     */
    private int id;
    /**
     * Asiakkaan saapumisaika vuoronumerroautomaatille
     */
    private double saapumisaika;
    /**
     * Asiakkaa poistumisaika palvelupisteeltä
     */
    private double poistumisaika;
    /**
     * Asikakkaiden läpimenoaikojen kokonaissumma
     */
    private static double sum = 0;
    /**
     * Tiskin A asikakkaiden läpimenoaikojen kokonaissumma
     */
    private static double sumA = 0;
    /**
     * Tiskin B asikakkaiden läpimenoaikojen kokonaissumma
     */
    private static double sumB = 0;
    /**
     * Tiskin C asikakkaiden läpimenoaikojen kokonaissumma
     */
    private static double sumC = 0;
    /**
     * Tiskin A askiakkaiden kokonaismäärä
     */
    private static int AsiakasA = 0;
    /**
     * Tiskin B askiakkaiden kokonaismäärä
     */
    private static int AsiakasB = 0;
    /**
     * Tiskin C askiakkaiden kokonaismäärä
     */
    private static int AsiakasC= 0;
    /**
     * Asikkaiden id luomisessa käytettävä muuttuja
     */
    private static int i = 1;
    /**
     *Asiakkaan käyttämä tiksi
     */
    private String tiski;

    /**
     * Asettaa asiakaalle id:n sekä saapumisajan
     */
    public Asiakas(){
        this.id = i++;

        saapumisaika = Kello.getInstance().getAika();
        Trace.out(Trace.Level.INFO, "Uusi asiakas:" + id + ":"+saapumisaika);
    }

    /**
     * Palautta askiakkaiden kokonaismäärän
     * @return Asiakkaiden kokonaismäärä
     */
    public static int getMäärä() {return i - 1;}

    /**
     * Palautta asiakkaan poistumisajan
     * @return Asiakkaan poistumisaika
     */
    public double getPoistumisaika() {
        return poistumisaika;
    }

    /**
     * Asettaa asiakkaan poistumisajan
     * @param poistumisaika Asikakkaan poistumisaika
     */
    public void setPoistumisaika(double poistumisaika) {
        this.poistumisaika = poistumisaika;
    }

    /**
     * Asettaa askiakkaan käyttämän tiskin
     * @param tiski Asiakkaan käuttämä tiski
     */
    public void setTiski(String tiski){this.tiski = tiski;}

    /**
     * Palautta asiakkan käyttämän tiskin
     * @return Asiakkaan käyttämä tiski
     */
    public String getTiski() {return tiski; }

    /**
     * Luo dataa askaiakkaasta, asiakkaan poitumisen jälkeen
     */
    public void raportti(){
        Trace.out(Trace.Level.INFO,"Asiakas "+id+ " poistui:" +poistumisaika);
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

    /**
     * Palautta tiskina A asiakasmäärän
     * @return Tiskiä A käyttäneiden asiakkaiden määrä
     */
    public static int getAsiakasA(){
        return AsiakasA;
    }

    /**
     * Palautta tiskina B asiakasmäärän
     * @return Tiskiä B käyttäneiden asiakkaiden määrä
     */
    public static int getAsiakasB(){
        return AsiakasB;
    }

    /**
     * Palautta tiskina C asiakasmäärän
     * @return Tiskiä C käyttäneiden asiakkaiden kokonaismäärä
     */
    public static int getAsiakasC(){
        return AsiakasC;
    }

    /**
     * palautta asiakkaan id:n
     * @return Asiakkaan id
     */
    public int getId() {
        return id;
    }

    /**
     * Palautta asiakkaiden läpimenoaikojen summan
     * @return Asiakkaiden läpimenoaikojen summa
     */
    public static double getSum() {
        return sum;
    }

    /**
     * Palautta tiskin A asiakkaiden läpimenoaikojen summan
     * @return Tiskin A asiakkaiden läpimenoaikojen summa
     */
    public static double getSumA() {
        return sumA;
    }

    /**
     * Palautta tiskin B asiakkaiden läpimenoaikojen summan
     * @return Tiskin B asiakkaiden läpimenoaikojen summa
     */
    public static double getSumB() {
        return sumB;
    }

    /**
     * Palautta tiskin C asiakkaiden läpimenoaikojen summan
     * @return Tiskin C asiakkaiden läpimenoaikojen summa
     */
    public static double getSumC() {
        return sumC;
    }

    /**
     * Nollaa kaikki staatiset muuttujat
     */
    public static void nollaaAsiakkaat(){
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
