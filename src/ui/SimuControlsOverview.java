package ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import simu.model.IOmaMoottori;
/**
 * Luokka controlloi pääsimulaattorin UI:tä
 *
 * @author Joni Tahvanainen ja Felix Uimonen
 * @version 1
 */

public class SimuControlsOverview {

    MainUI main;
    IOmaMoottori m;
    private double time;
    private boolean[] checkbox = new boolean[9];
    private boolean jakaumaTarkistus = true;


    @FXML
    private TextField asiakaskeskiarvo;
    @FXML
    private TextField asiakashajonta;
    @FXML
    private TextField jakaumaA;
    @FXML
    private TextField jakaumaB;
    @FXML
    private TextField jakaumaC;

    @FXML
    private Label ErrorLabel;


    @FXML
    private TextField field;

    /**
     * PA = palveluaika.
     * HA = palveluajan hajonta.
     */
    @FXML
    private CheckBox tiskiA1_CB;
    @FXML
    private TextField tiskiA1_PA;       //palveluaika
    @FXML
    private TextField tiskiA1_HA;       //palveluajan hajonta
    @FXML
    private CheckBox tiskiA2_CB;
    @FXML
    private TextField tiskiA2_PA;
    @FXML
    private TextField tiskiA2_HA;
    @FXML
    private CheckBox tiskiA3_CB;
    @FXML
    private TextField tiskiA3_PA;
    @FXML
    private TextField tiskiA3_HA;

    @FXML
    private CheckBox tiskiB1_CB;
    @FXML
    private TextField tiskiB1_PA;
    @FXML
    private TextField tiskiB1_HA;
    @FXML
    private CheckBox tiskiB2_CB;
    @FXML
    private TextField tiskiB2_PA;
    @FXML
    private TextField tiskiB2_HA;
    @FXML
    private CheckBox tiskiB3_CB;
    @FXML
    private TextField tiskiB3_PA;
    @FXML
    private TextField tiskiB3_HA;

    @FXML
    private CheckBox tiskiC1_CB;
    @FXML
    private TextField tiskiC1_PA;
    @FXML
    private TextField tiskiC1_HA;
    @FXML
    private CheckBox tiskiC2_CB;
    @FXML
    private TextField tiskiC2_PA;
    @FXML
    private TextField tiskiC2_HA;
    @FXML
    private CheckBox tiskiC3_CB;
    @FXML
    private TextField tiskiC3_PA;
    @FXML
    private TextField tiskiC3_HA;


    public SimuControlsOverview(){

    }


    /**
     * initialize javafx listenereitä.
     */
    @FXML
    private void initialize(){
        jakaumaA.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try{
                    if(jakaumaA != null && Double.parseDouble(t1) + Double.parseDouble(jakaumaB.getText()) >= 100){
                        ErrorLabel.setTextFill(Color.web("#FF0000"));
                        ErrorLabel.setText("Jakaumien summa ei voi olla yli 100");
                        jakaumaTarkistus = false;
                    }else{
                        ErrorLabel.setText("");
                        jakaumaTarkistus = true;
                        jakaumaC.setText(Double.toString(100 - Double.parseDouble(jakaumaA.getText()) - Double.parseDouble(jakaumaB.getText())));
                    }
                } catch (Exception e){

                }

            }
        });
        jakaumaB.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try{
                    if(jakaumaB != null && Double.parseDouble(t1) + Double.parseDouble(jakaumaA.getText()) >= 100){
                        ErrorLabel.setTextFill(Color.web("#FF0000"));
                        ErrorLabel.setText("Jakaumien summa ei voi olla yli 100");
                        jakaumaTarkistus = false;
                    } else {
                        ErrorLabel.setText("");
                        jakaumaTarkistus = true;
                        jakaumaC.setText(Double.toString(100 - Double.parseDouble(jakaumaA.getText()) - Double.parseDouble(jakaumaB.getText())));
                    }
                }catch (Exception e){

                }
            }
        });
    }


    /**
     * Tuodaan luokat MainUI ja IOmamoottrin tähän luokkaan käytettäväksi.
     *
     * @param main MainUi luokka
     * @param m IOmamoottori luokka
     */
    public void setMainUI(MainUI main, IOmaMoottori m){
        this.m = m;
        this.main = main;
    }


    /**
     * Otetaan talteen kaikki tiskin A asetukset.
     */
    public void getTiskiA(){

        boolean cbA_a = tiskiA1_CB.isSelected();
        double txtA_ap = Double.parseDouble(tiskiA1_PA.getText());
        double txtA_ah = Double.parseDouble(tiskiA1_HA.getText());

        boolean cbA_b = tiskiA2_CB.isSelected();
        double txtA_bp = Double.parseDouble(tiskiA2_PA.getText());
        double txtA_bh = Double.parseDouble(tiskiA2_HA.getText());

        boolean cbA_c = tiskiA3_CB.isSelected();
        double txtA_cp = Double.parseDouble(tiskiA3_PA.getText());
        double txtA_ch = Double.parseDouble(tiskiA3_HA.getText());
    }

    /**
     * Otetaan talteen kaikki tiskin B asetukset.
     */
    public void getTiskiB(){
        boolean cbB_a = tiskiB1_CB.isSelected();
        double txtB_ap = Double.parseDouble(tiskiB1_PA.getText());
        double txtB_ah = Double.parseDouble(tiskiB1_HA.getText());

        boolean cbB_b = tiskiB2_CB.isSelected();
        double txtB_bp = Double.parseDouble(tiskiB2_PA.getText());
        double txtB_bh = Double.parseDouble(tiskiB2_HA.getText());

        boolean cbB_c = tiskiB3_CB.isSelected();
        double txtB_cp = Double.parseDouble(tiskiB3_PA.getText());
        double txtB_ch = Double.parseDouble(tiskiB3_HA.getText());
    }
    /**
     * Otetaan talteen kaikki tiskin C asetukset.
     */
    public void getTiskiC(){
        boolean cbC_a = tiskiC1_CB.isSelected();
        double txtC_ap = Double.parseDouble(tiskiC1_PA.getText());
        double txtC_ah = Double.parseDouble(tiskiC1_HA.getText());

        boolean cbC_b = tiskiC2_CB.isSelected();
        double txtC_bp = Double.parseDouble(tiskiC2_PA.getText());
        double txtC_bh = Double.parseDouble(tiskiC2_HA.getText());

        boolean cbC_c = tiskiC3_CB.isSelected();
        double txtC_cp = Double.parseDouble(tiskiC3_PA.getText());
        double txtC_ch = Double.parseDouble(tiskiC3_HA.getText());
    }

    /**
     * @return palautetaan booleon array josta löytyy kaikkien tiskien ovatko ne päällä.
     */
    public boolean[] getCheckbox(){
        checkbox[0] = tiskiA1_CB.isSelected();
        checkbox[1] = tiskiA2_CB.isSelected();
        checkbox[2] = tiskiA3_CB.isSelected();
        checkbox[3] = tiskiB1_CB.isSelected();
        checkbox[4] = tiskiB2_CB.isSelected();
        checkbox[5] = tiskiB3_CB.isSelected();
        checkbox[6] = tiskiC1_CB.isSelected();
        checkbox[7] = tiskiC2_CB.isSelected();
        checkbox[8] = tiskiC3_CB.isSelected();

        return checkbox;
    }

    /**
     * @return palautetaan double array josta löytyy jokaisen tiskin palveluajat.
     */
    public double[] getPalveluajat(){
        double[] palveluajat = new double[9];
        palveluajat[0] = Double.parseDouble(tiskiA1_PA.getText());
        palveluajat[1] = Double.parseDouble(tiskiA2_PA.getText());
        palveluajat[2] = Double.parseDouble(tiskiA3_PA.getText());
        palveluajat[3] = Double.parseDouble(tiskiB1_PA.getText());
        palveluajat[4] = Double.parseDouble(tiskiB2_PA.getText());
        palveluajat[5] = Double.parseDouble(tiskiB3_PA.getText());
        palveluajat[6] = Double.parseDouble(tiskiC1_PA.getText());
        palveluajat[7] = Double.parseDouble(tiskiC2_PA.getText());
        palveluajat[8] = Double.parseDouble(tiskiC3_PA.getText());
        return palveluajat;
    }

    /**
     * @return palautetaan double array josta löytyy jokaisen tiskin hajonnat
     */
    public double[] getHajonnat(){
        double[] hajonnat = new double[9];
        hajonnat[0] = Double.parseDouble(tiskiA1_HA.getText());
        hajonnat[1] = Double.parseDouble(tiskiA2_HA.getText());
        hajonnat[2] = Double.parseDouble(tiskiA3_HA.getText());
        hajonnat[3] = Double.parseDouble(tiskiB1_HA.getText());
        hajonnat[4] = Double.parseDouble(tiskiB2_HA.getText());
        hajonnat[5] = Double.parseDouble(tiskiB3_HA.getText());
        hajonnat[6] = Double.parseDouble(tiskiC1_HA.getText());
        hajonnat[7] = Double.parseDouble(tiskiC2_HA.getText());
        hajonnat[8] = Double.parseDouble(tiskiC3_HA.getText());
        return hajonnat;

    }


    /**
     * Aloitetaan simulaattori jos jakaumatarkistus menee läpi.
     */
    @FXML
    private void handleStartButton(){
        if(jakaumaTarkistus){
            try {
                getTiskiA();
                getTiskiB();
                getTiskiC();

                m.setCheckbox(getCheckbox());
                m.setPalveluajat(getPalveluajat());
                m.setHajonnat(getHajonnat());
                m.setJakaumat(Double.parseDouble(jakaumaA.getText()), Double.parseDouble(jakaumaB.getText()));
                m.setSaapumistiheys((long) Double.parseDouble(asiakaskeskiarvo.getText()), (long) Double.parseDouble(asiakashajonta.getText()));
                System.out.println(tiskiA1_CB.isSelected());
                time = Double.parseDouble(field.getText());



                System.out.println(time);
                main.SimuStart(time);
            } catch (NumberFormatException e) {
                System.out.println("Not a number.");
            }
        }
    }


    /**
     * Sammuttaa ohjelman
     */
    @FXML
    private void handleStopButton(){
        try{
            System.exit(0);
        } catch (Exception e){
            System.out.println("Virhe");
        }
    }

}
