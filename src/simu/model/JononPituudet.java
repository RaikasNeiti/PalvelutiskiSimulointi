package simu.model;

import javax.persistence.*;

@Entity
@Table(name="jononPituus")
public class JononPituudet {
    @Id
    @Column
    private int id;
    @Column
    private double dAika;
    @Column
    private int jononAPituus;
    @Column
    private int jononBPituus;
    @Column
    private int jononCPituus;

    private static int i = 1;

    public JononPituudet(double dAika, int jononAPituus, int jononBPituus, int jononCPituus){
        this.dAika =dAika;
        this.jononAPituus = jononAPituus;
        this.jononBPituus = jononBPituus;
        this.jononCPituus = jononCPituus;
        id = i++;

    }
    public JononPituudet(){}

    public double getdAika() {
        return dAika;
    }

    public void setdAika(double dAika) {
        this.dAika = dAika;
    }

    public int getJononAPituus() {
        return jononAPituus;
    }

    public void setJononAPituus(int jononAPituus) {
        this.jononAPituus = jononAPituus;
    }

    public int getJononBPituus() {
        return jononBPituus;
    }

    public void setJononBPituus(int jononBPituus) {
        this.jononBPituus = jononBPituus;
    }

    public int getJononCPituus() {
        return jononCPituus;
    }

    public void setJononCPituus(int jononCPituus) {
        this.jononCPituus = jononCPituus;
    }
}
