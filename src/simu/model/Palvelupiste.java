package simu.model;

import java.util.LinkedList;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import ui.SimuAnimation;

// TODO:
// Palvelupistekohtaiset toiminnallisuudet, laskutoimitukset (+ tarvittavat muuttujat) ja raportointi koodattava
public class Palvelupiste {
    private LinkedList<Asiakas> jono; // Tietorakennetoteutus

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

    //JonoStartegia strategia; //optio: asiakkaiden järjestys

    private boolean varattu = false;


    public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi, LinkedList<Asiakas> jono , boolean aktiivinen){
        this.tapahtumalista = tapahtumalista;
        this.generator = generator;
        this.skeduloitavanTapahtumanTyyppi = tyyppi;
        this.jono = jono;
        id = i++;
        this.aktiivinen = aktiivinen;

    }


    public void lisaaJonoon(Asiakas a){   // Jonon 1. asiakas aina palvelussa (väärin!!!!!)

        jono.add(a);

    }

    public Asiakas otaJonosta(){  // Poistetaan palvelussa ollut
        varattu = false;
        return palveltava;
    }

    public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana  (väärin!!!!)
        varattu = true;
        double palveluaika = generator.sample();
        KokonaisPalveluAika += palveluaika;
        palveltava = jono.poll();
        tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika, id));
    }
    public double getKokonaisPalveluAika(){
        return KokonaisPalveluAika;
    }
    public double getInactive() {
        if(inactive == 0 && !aktiivinen){
            return 0;
        }
        else {
            return Kello.getInstance().getAika() - inactive;
        }
    }
    public int getId(){return id;}



    public boolean onVarattu(){
        return varattu;
    }
    public boolean onAktiivinen(){ return aktiivinen;}


    public boolean onJonossa(){
        return jono.size() != 0;
    }

    public void vaihdaAktiivinen(boolean aktiivinen){
        this.aktiivinen = aktiivinen;
    }

    public double kaytossaoloProsentti(){
        if(inactive == 0 && !aktiivinen){
            return 0;
        }
        else {
            return 100 *KokonaisPalveluAika/(Kello.getInstance().getAika() - inactive);
        }
    }

    public static void nollaa(){
        i=0;
    }

    public void nollaaTulokset() {
        KokonaisPalveluAika = 0;
        inactive = 0;

    }
}
