package ui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import simu.model.OmaMoottori;

public class SimuAnimation {
    MainUI main;
    OmaMoottori m;
    @FXML
    private CheckBox checkbox_A_a;
    @FXML
    private CheckBox checkbox_A_b;
    @FXML
    private CheckBox checkbox_A_c;

    @FXML
    private CheckBox checkbox_B_a;
    @FXML
    private CheckBox checkbox_B_b;
    @FXML
    private CheckBox checkbox_B_c;

    @FXML
    private CheckBox checkbox_C_a;
    @FXML
    private CheckBox checkbox_C_b;
    @FXML
    private CheckBox checkbox_C_c;


    public void setMainUI(MainUI main, OmaMoottori m){
        this.m = m;
        this.main = main;
    }
    public void setCheckbox(boolean[] check){
        checkbox_A_a.setSelected(check[0]);
        checkbox_A_b.setSelected(check[1]);
        checkbox_A_c.setSelected(check[2]);
        checkbox_B_a.setSelected(check[3]);
        checkbox_B_b.setSelected(check[4]);
        checkbox_B_c.setSelected(check[5]);
        checkbox_C_a.setSelected(check[6]);
        checkbox_C_b.setSelected(check[7]);
        checkbox_C_c.setSelected(check[8]);


    }

    @FXML
    private void handleCheckboxA_a(){
        m.setTiskiAktiivinen(0 , checkbox_A_a.isSelected());
    }
    @FXML
    private void handleCheckboxA_b(){
        m.setTiskiAktiivinen(1 , checkbox_A_b.isSelected());
    }
    @FXML
    private void handleCheckboxA_c(){
        m.setTiskiAktiivinen(2, checkbox_A_c.isSelected());
    }

    @FXML
    private void handleCheckboxB_a(){
        m.setTiskiAktiivinen(3 , checkbox_B_a.isSelected());
    }
    @FXML
    private void handleCheckboxB_b(){
        m.setTiskiAktiivinen(4 , checkbox_B_b.isSelected());
    }
    @FXML
    private void handleCheckboxB_c(){
        m.setTiskiAktiivinen(5, checkbox_B_c.isSelected());
    }

    @FXML
    private void handleCheckboxC_a(){
        m.setTiskiAktiivinen(6 , checkbox_C_a.isSelected());
    }
    @FXML
    private void handleCheckboxC_b(){
        m.setTiskiAktiivinen(7 , checkbox_C_b.isSelected());
    }
    @FXML
    private void handleCheckboxC_c(){
        m.setTiskiAktiivinen(8, checkbox_C_c.isSelected());
    }





}
