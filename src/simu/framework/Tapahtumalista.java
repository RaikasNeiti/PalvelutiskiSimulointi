package simu.framework;

import java.util.PriorityQueue;

/**
 * Luo priorityqueue tyypisen listan joka järjestää tapahtumat aikajärjestukseen ja käsitelee listaa
 *
 * @author Joni Tahvanainen ja Felix Uimonen
 * @version 1
 */
public class Tapahtumalista {
    /**
     * Priorityqueue lista jota luokkaka käsitelee
     */
    private PriorityQueue<Tapahtuma> lista = new PriorityQueue<Tapahtuma>();

    public Tapahtumalista(){

    }

    /**
     * Poistaa tapahtuma listan ensimmäisen tapahtuman
     * @return Listasta poistettu tapahtuma
     */
    public Tapahtuma poista(){
        return lista.remove();
    }

    /**
     * Lisää tapahtuma listaan uuden tapahtuman
     * @param t Tapahtumalistaan lisättävä tapahtuma
     */
    public void lisaa(Tapahtuma t){
        lista.add(t);
    }

    /**
     * Hakee listan ensimmäisen tapahtuman tapahtuma-ajan
     * @return Seuraavan tapahtuman tapahtuma-ajan
     */
    public double getSeuraavanAika(){
        return lista.peek().getAika();
    }

    /**
     * Tarkistaa onko tapahtumalista tyhjä
     * @return Onko tapahtumalista
     */
    public boolean onTyhjä(){
        return lista.size() == 0;
    }

    /**
     * Tyhjää tapahtumalistan tapahtumista
     */
    public void nollaa(){
        lista.clear();
    }



}
