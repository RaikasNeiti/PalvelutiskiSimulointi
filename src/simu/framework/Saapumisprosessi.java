package simu.framework;
import eduni.distributions.*;
import simu.model.TapahtumanTyyppi;

/**
 * Luokka luo uusia asiakkaita
 *
 * @author Joni Tahvanainen ja Felix Uimonen
 * @version 1
 */
public class Saapumisprosessi {



    private ContinuousGenerator generaattori;
    private Tapahtumalista tapahtumalista;
    private TapahtumanTyyppi tyyppi;

    /**
     * @param g Generator
     * @param tl tapahtumalista
     * @param tyyppi tapahatumatyyppi
     */
    public Saapumisprosessi(ContinuousGenerator g, Tapahtumalista tl, TapahtumanTyyppi tyyppi){
        this.generaattori = g;
        this.tapahtumalista = tl;
        this.tyyppi = tyyppi;
    }

    /**
     * Luo seuraava.
     */
    public void generoiSeuraava(){
        Tapahtuma t = new Tapahtuma(tyyppi, Kello.getInstance().getAika()+generaattori.sample());
        tapahtumalista.lisaa(t);
    }

}
