package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.Text;
import simu.model.OmaMoottori;

public class SimuControlsOverview {

    MainUI main;
    OmaMoottori m;
    private double time;
    private boolean[] checkbox = new boolean[9];

    @FXML
    private TextField field;

    @FXML
    private CheckBox tiskiA_a;
    @FXML
    private TextField tiskiA_ap;
    @FXML
    private TextField tiskiA_ah;
    @FXML
    private CheckBox tiskiA_b;
    @FXML
    private TextField tiskiA_bp;
    @FXML
    private TextField tiskiA_bh;
    @FXML
    private CheckBox tiskiA_c;
    @FXML
    private TextField tiskiA_cp;
    @FXML
    private TextField tiskiA_ch;

    @FXML
    private CheckBox tiskiB_a;
    @FXML
    private TextField tiskiB_ap;
    @FXML
    private TextField tiskiB_ah;
    @FXML
    private CheckBox tiskiB_b;
    @FXML
    private TextField tiskiB_bp;
    @FXML
    private TextField tiskiB_bh;
    @FXML
    private CheckBox tiskiB_c;
    @FXML
    private TextField tiskiB_cp;
    @FXML
    private TextField tiskiB_ch;

    @FXML
    private CheckBox tiskiC_a;
    @FXML
    private TextField tiskiC_ap;
    @FXML
    private TextField tiskiC_ah;
    @FXML
    private CheckBox tiskiC_b;
    @FXML
    private TextField tiskiC_bp;
    @FXML
    private TextField tiskiC_bh;
    @FXML
    private CheckBox tiskiC_c;
    @FXML
    private TextField tiskiC_cp;
    @FXML
    private TextField tiskiC_ch;


    public SimuControlsOverview(){

    }

    public void setMainUI(MainUI main, OmaMoottori m){
        this.m = m;
        this.main = main;
    }

    public void getTiskiA(){
        boolean cbA_a = tiskiA_a.isSelected();
        double txtA_ap = Double.parseDouble(tiskiA_ap.getText());
        double txtA_ah = Double.parseDouble(tiskiA_ah.getText());

        boolean cbA_b = tiskiA_b.isSelected();
        double txtA_bp = Double.parseDouble(tiskiA_bp.getText());
        double txtA_bh = Double.parseDouble(tiskiA_bh.getText());

        boolean cbA_c = tiskiA_c.isSelected();
        double txtA_cp = Double.parseDouble(tiskiA_cp.getText());
        double txtA_ch = Double.parseDouble(tiskiA_ch.getText());

        m.setTiskiA(cbA_a, txtA_ap, txtA_ah, cbA_b, txtA_bp, txtA_bh , cbA_c, txtA_cp, txtA_ch);
    }

    public void getTiskiB(){
        boolean cbB_a = tiskiB_a.isSelected();
        double txtB_ap = Double.parseDouble(tiskiB_ap.getText());
        double txtB_ah = Double.parseDouble(tiskiB_ah.getText());

        boolean cbB_b = tiskiB_b.isSelected();
        double txtB_bp = Double.parseDouble(tiskiB_bp.getText());
        double txtB_bh = Double.parseDouble(tiskiB_bh.getText());

        boolean cbB_c = tiskiB_c.isSelected();
        double txtB_cp = Double.parseDouble(tiskiB_cp.getText());
        double txtB_ch = Double.parseDouble(tiskiB_ch.getText());

        m.setTiskiB(cbB_a, txtB_ap, txtB_ah, cbB_b, txtB_bp, txtB_bh , cbB_c, txtB_cp, txtB_ch);
    }

    public void getTiskiC(){
        boolean cbC_a = tiskiC_a.isSelected();
        double txtC_ap = Double.parseDouble(tiskiC_ap.getText());
        double txtC_ah = Double.parseDouble(tiskiC_ah.getText());

        boolean cbC_b = tiskiC_b.isSelected();
        double txtC_bp = Double.parseDouble(tiskiC_bp.getText());
        double txtC_bh = Double.parseDouble(tiskiC_bh.getText());

        boolean cbC_c = tiskiC_c.isSelected();
        double txtC_cp = Double.parseDouble(tiskiC_cp.getText());
        double txtC_ch = Double.parseDouble(tiskiC_ch.getText());

        m.setTiskiC(cbC_a, txtC_ap, txtC_ah, cbC_b, txtC_bp, txtC_bh , cbC_c, txtC_cp, txtC_ch);
    }
    public boolean[] getCheckbox(){
        checkbox[0] = tiskiA_a.isSelected();
        checkbox[1] = tiskiA_b.isSelected();
        checkbox[2] = tiskiA_c.isSelected();
        checkbox[3] = tiskiB_a.isSelected();
        checkbox[4] = tiskiB_b.isSelected();
        checkbox[5] = tiskiB_c.isSelected();
        checkbox[6] = tiskiC_a.isSelected();
        checkbox[7] = tiskiC_b.isSelected();
        checkbox[8] = tiskiC_c.isSelected();

        return checkbox;
    }


    @FXML
    private void handleStartButton(){
        try {
            getTiskiA();
            getTiskiB();
            getTiskiC();

            System.out.println(tiskiA_a.isSelected());
            time = Double.parseDouble(field.getText());

            System.out.println(time);
            main.SimuStart(time);


        } catch (NumberFormatException e) {
            System.out.println("Not a number.");
        }

    }


    @FXML
    private void handleStopButton(){
        try{
            System.exit(0);
        } catch (Exception e){
            System.out.println("Virhe");
        }
    }

}
