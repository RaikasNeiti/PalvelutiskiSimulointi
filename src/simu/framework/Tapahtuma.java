package simu.framework;

import simu.model.TapahtumanTyyppi;

/**
 * Simullation läpiviemisessä käytettävien tapahtumien luontiin ja käsittellyyn käytettävä luokka
 *
 * @author Joni Tahvanainen ja Felix Uimonen
 * @version 1
 */
public class Tapahtuma implements Comparable<Tapahtuma> {


    private TapahtumanTyyppi tyyppi;
    private double aika;
    private int palvelija;

    /**
     * @param tyyppi Taphtuman tyyppi
     * @param aika Tapahtuman taphtuma-aika
     * @param palvelija Tapahtuman käsittelevä palvelupiste
     */
    public Tapahtuma(TapahtumanTyyppi tyyppi, double aika, int palvelija){
        this.tyyppi = tyyppi;
        this.aika = aika;
        this.palvelija = palvelija;
    }

    /**
     * @param tyyppi Taphtuman tyyppi
     * @param aika Tapahtuman taphtuma-aika
     */
    public Tapahtuma(TapahtumanTyyppi tyyppi, double aika) {
        this.tyyppi = tyyppi;
        this.aika = aika;
    }


    /**
     * Palautta Tapahtuman tyypin
     * @return Tapahtuman tyyppi
     */
    public TapahtumanTyyppi getTyyppi() {
        return tyyppi;
    }

    /**
     * palauttaa Taphtuma-ajan
     * @return Tapahtuman tapahtuma-aika
     */
    public double getAika() {
        return aika;
    }

    /**
     * palautta Tapahtuman palvelupisteen
     * @return Tapahtuman palvelija
     */
    public int getPalvelija(){return palvelija;}

    /**
     * Priorityqueuen vaatima metodi listan järjestämistä varten
     * @param arg Verrattava taphtuma
     * @return Onko tapahtuma suurempi, pienempi vai yhtäsuuri kuin verratava taphtuma
     */
    @Override
    public int compareTo(Tapahtuma arg) {
        if (this.aika < arg.aika) return -1;
        else if (this.aika > arg.aika) return 1;
        return 0;
    }




}
