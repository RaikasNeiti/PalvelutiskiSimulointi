package simu.model;

public class Saie extends Thread{
    OmaMoottori m;


    public Saie(OmaMoottori m){
        this.m = m;

    }


    public void run(){
        m.aja();
    }
}
