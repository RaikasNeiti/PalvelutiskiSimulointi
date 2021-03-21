package simu.model;

import java.util.LinkedList;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import ui.SimuAnimation;

/**
 * Tämä luokka luo palvelupisteet ja palevelee asiakkaita.
 *
 * @author Joni Tahvanainen ja Felix Uimonen
 * @version 1
 */

public class Palvelupiste {
    private LinkedList<Asiakas> jono;

    private ContinuousGenerator generator;
    private Tapahtumalista tapahtumalista;
    private TapahtumanTyyppi skeduloitavanTapahtumanTyyppi;
    private double KokonaisPalveluAika = 0;
    private int id;
    private static int i = 0;
    private boolean aktiivinen;
    private Asiakas palveltava;

    private double inactive = 0;
    private double wentInactive;

    private boolean varattu = false;


    /**
     * @param generator
     * @param tapahtumalista tapahtumalista
     * @param tyyppi tapahtumatyyppi
     * @param jono linkedlist jono
     * @param aktiivinen onko aktiivinen palvelupiste
     */
    public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi, LinkedList<Asiakas> jono , boolean aktiivinen){
        this.tapahtumalista = tapahtumalista;
        this.generator = generator;
        this.skeduloitavanTapahtumanTyyppi = tyyppi;
        this.jono = jono;
        id = i++;
        this.aktiivinen = aktiivinen;

    }


    /**
     * lisää asiakaat jonoon.
     *
     * @param a asiakas
     */
    public void lisaaJonoon(Asiakas a){

        jono.add(a);

    }

    /**
     *
     * Ottaa asiakkaan jonosta. ja muutttaa palvelupisteen pois varatusta tilasta.
     *
     * @return palveltavan asiakkaan
     */
    public Asiakas otaJonosta(){  // Poistetaan palvelussa ollut
        varattu = false;
        return palveltava;
    }

    /**
     * Aloittaa palvelun.
     */
    public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana  (väärin!!!!)
        varattu = true;
        double palveluaika = generator.sample();
        KokonaisPalveluAika += palveluaika;
        palveltava = jono.poll();
        tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika, id));
    }

    /**
     * @return Koknaispalveluaika eli kuinka kauan
     */
    public double getKokonaisPalveluAika(){
        return KokonaisPalveluAika;
    }

    /**
     * @return inaktiivinen aika.
     */
    public double getInactive() {
        if(inactive == 0 && !aktiivinen){
            return 0;
        }
        else {
            return Kello.getInstance().getAika() - inactive;
        }
    }

    /**
     * @return palvelupisteen id
     */
    public int getId(){return id;}


    /**
     * @return palvelupiste on varattu.
     */
    public boolean onVarattu(){
        return varattu;
    }

    /**
     * @return palvelupiste on aktiivinen
     */
    public boolean onAktiivinen(){ return aktiivinen;}


    /**
     * @return true tai false jos jonossa on asiakkaita.
     */
    public boolean onJonossa(){
        return jono.size() != 0;
    }

    /**
     *
     * vaihtaa aktiiviseksi.
     *
     * @param aktiivinen onko piste aktiivinen
     */
    public void vaihdaAktiivinen(boolean aktiivinen){
        this.aktiivinen = aktiivinen;
    }

    /**
     * @return palauttaa käytössäoloprosenttin
     */
    public double kaytossaoloProsentti(){
        if(inactive == 0 && !aktiivinen){
            return 0;
        }
        else {
            return 100 *KokonaisPalveluAika/(Kello.getInstance().getAika() - inactive);
        }
    }

    /**
     * Nollaa muuttujan i:n
     */
    public static void nollaa(){
        i=0;
    }

    /**
     * Nollaa tulokset.
     */
    public void nollaaTulokset() {
        KokonaisPalveluAika = 0;
        inactive = 0;

    }
}
