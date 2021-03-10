package ui;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import simu.model.DataAccessObject;

public class DataController {

    @FXML
    private BarChart<String, Integer> Asiakkaat;
    @FXML
    private BarChart<String, Integer> Keskiaika;
    @FXML
    private BarChart<String, Integer> Jonopituus;
    @FXML
    private BarChart<String, Integer> Prosentti;

    public DataController(){
        DataAccessObject dao = new DataAccessObject();
    }


}