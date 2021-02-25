package simu.framework;

import simu.model.Palvelupiste;

import static javafx.application.Platform.runLater;

public abstract class Moottori{

    private double simulointiaika = 0;

    private Kello kello;

    protected Tapahtumalista tapahtumalista;
    protected Palvelupiste[] palvelupisteet;


    public Moottori(){

        kello = Kello.getInstance(); // Otetaan kello muuttujaan yksinkertaistamaan koodia

        tapahtumalista = new Tapahtumalista();

        // Palvelupisteet luodaan simu.model-pakkauksessa Moottorin aliluokassa


    }

    public void setSimulointiaika(double aika) {
        simulointiaika = aika;
    }


    public void aja(){
        alustukset(); // luodaan mm. ensimmäinen tapahtuma
        while (simuloidaan()){
            kello.setAika(nykyaika());
            suoritaBTapahtumat();
            yritaCTapahtumat();
            runLater(() -> UpdateUi());
            try{
                Thread.sleep(500);
                while(getPause()){
                    Thread.sleep(500);
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("Vuoronumeroautomaatti suljettu");
        suljeVuoronumero();
        while(!tapahtumalista.onTyhjä()){
            kello.setAika(nykyaika());
            suoritaBTapahtumat();
            yritaCTapahtumat();
            runLater(() -> UpdateUi());
        }

        tulokset();

    }

    private void suoritaBTapahtumat(){
        while (tapahtumalista.getSeuraavanAika() == kello.getAika()){
            suoritaTapahtuma(tapahtumalista.poista());
            if(tapahtumalista.onTyhjä()){
                break;
            }
        }
    }

    private void yritaCTapahtumat(){
        for (Palvelupiste p: palvelupisteet){
            if (!p.onVarattu() && p.onJonossa() && p.onAktiivinen()){
                p.aloitaPalvelu();

            }
        }



    }


    private double nykyaika(){
        return tapahtumalista.getSeuraavanAika();
    }

    private boolean simuloidaan(){
        Trace.out(Trace.Level.INFO, "Kello on: " + kello.getAika());
        return kello.getAika() < simulointiaika;
    }


    protected abstract  void suljeVuoronumero();

    protected abstract void alustukset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa

    protected abstract void suoritaTapahtuma(Tapahtuma t);  // Määritellään simu.model-pakkauksessa Moottorin aliluokassa

    protected abstract void tulokset(); // Määritellään simu.model-pakkauksessa Moottorin aliluokassa

    protected abstract void UpdateUi();

    protected abstract boolean getPause();

}