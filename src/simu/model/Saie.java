package simu.model;
/**
 * Avaa uuden Saieen simulaattorin käynnistykseen.
 *
 * @author Joni Tahvanainen ja Felix Uimonen
 * @version 1
 */

public class Saie extends Thread{
    IOmaMoottori m;


    /**
     * @param m IOmamoottori
     */
    public Saie(IOmaMoottori m){
        this.m = m;

    }


    /**
     * Ajaa simulaattorin.
     */
    public void run(){
        m.aja();
    }
}
