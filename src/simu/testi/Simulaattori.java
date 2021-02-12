package simu.testi;
import simu.framework.*;
import simu.framework.Trace.Level;
import simu.model.OmaMoottori;
import ui.MainUI;

public class Simulaattori {

    public static void main(String[] args) {
        //testi
        Trace.setTraceLevel(Level.INFO);
        Moottori m = new OmaMoottori();
        m.setSimulointiaika(1500);
        m.aja();
        MainUI.main(args);



    }

}
