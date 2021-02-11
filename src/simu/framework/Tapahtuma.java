package simu.framework;

import simu.model.TapahtumanTyyppi;

public class Tapahtuma implements Comparable<Tapahtuma> {


    private TapahtumanTyyppi tyyppi;
    private double aika;
    int palvelija;

    public Tapahtuma(TapahtumanTyyppi tyyppi, double aika, int palvelija){
        this.tyyppi = tyyppi;
        this.aika = aika;
        this.palvelija = palvelija;
    }
    public Tapahtuma(TapahtumanTyyppi tyyppi, double aika) {
        this.tyyppi = tyyppi;
        this.aika = aika;
    }

    public void setTyyppi(TapahtumanTyyppi tyyppi) {
        this.tyyppi = tyyppi;
    }
    public TapahtumanTyyppi getTyyppi() {
        return tyyppi;
    }
    public void setAika(double aika) {
        this.aika = aika;
    }
    public double getAika() {
        return aika;
    }
    public int getPalvelija(){return palvelija;}

    @Override
    public int compareTo(Tapahtuma arg) {
        if (this.aika < arg.aika) return -1;
        else if (this.aika > arg.aika) return 1;
        return 0;
    }




}
