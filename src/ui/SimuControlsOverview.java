package ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import simu.model.OmaMoottori;

public class SimuControlsOverview {

    MainUI main;
    private double time;

    @FXML
    private TextField field;

    public SimuControlsOverview(){

    }

    public void setMainUI(MainUI main){
        this.main = main;
    }

    @FXML
    private void handleStartButton(){
        try {
            time = Double.parseDouble(field.getText());

            System.out.println(time);
            main.SimuStart(time);


        } catch (NumberFormatException e) {
            System.out.println("Not a number.");
        }

    }

}
