package simu.model;

import ui.SimuAnimation;

public interface IOmaMoottori {
    void alustaTiskit();

    void setSimulointiaika(double time);

    void setAnim(SimuAnimation simuAnimation);

    void aja();

    void setPause();

    void setSpeed(boolean b);

    void setTiskiAktiivinen(int i, boolean a_a);

    void setCheckbox(boolean[] checkbox);

    void setPalveluajat(double[] palveluajat);

    void setHajonnat(double[] hajonnat);
}
