package simu.model;

public class Saie extends Thread{
    IOmaMoottori m;


    public Saie(IOmaMoottori m){
        this.m = m;

    }


    public void run(){
        m.aja();
    }
}
