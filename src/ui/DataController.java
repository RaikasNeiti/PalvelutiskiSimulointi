package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import simu.model.Asiakas;
import simu.model.DataAccessObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class DataController {

    @FXML
    private BarChart<String, Integer> Asiakkaat;
    @FXML
    private BarChart<String, Integer> Keskiaika;
    @FXML
    private BarChart<String, Integer> Jonopituus;
    @FXML
    private BarChart<String, Integer> Prosentti;
    @FXML
    private Label aika;

    ResultSet rs = null;
    DataAccessObject dao;

    private double time;
    private int maara;
    private int maaraA;
    private int maaraB;
    private int maaraC;
    private double keskiaika;
    private double keskiaikaA;
    private double keskiaikaB;
    private double keskiaikaC;
    private double jonopituusA;
    private double jonopituusB;
    private double jonopituusC;
    private double prosentti;
    private double prosenttiA;
    private double prosenttiB;
    private double prosenttiC;

    @FXML
    private CategoryAxis AsiakkaatxAxis;
    @FXML
    private CategoryAxis KeskiaikaxAxis;
    @FXML
    private CategoryAxis jonopituusxAxis;
    @FXML
    private CategoryAxis prosenttixAxis;
    private ObservableList<String> AsiakasList = FXCollections.observableArrayList();
    private ObservableList<String> KeskiaikaList = FXCollections.observableArrayList();
    private ObservableList<String> jonoList = FXCollections.observableArrayList();
    private ObservableList<String> prosenttiList = FXCollections.observableArrayList();

    public DataController(){ dao = new DataAccessObject(); }

    @FXML
    private void initialize(){
        try{
            rs = dao.getRs();
            rs.last();
        } catch (SQLException e){

        }
        String[] columns = {"AsiakkaidenMäärä","MääräA","MääräB","MääräC"};
        this.AsiakasList.addAll(Arrays.asList(columns));
        this.AsiakkaatxAxis.setCategories(this.AsiakasList);
        String[] columns2 = {"Keskiaika","KeskiaikaA","KeskiaikaB","KeskiaikaC"};
        this.KeskiaikaList.addAll(Arrays.asList(columns2));
        this.KeskiaikaxAxis.setCategories(this.KeskiaikaList);
        String[] columns3 = {"JononkeskipituusA","JononkeskipituusB","JononkeskipituusC"};
        this.jonoList.addAll(Arrays.asList(columns3));
        this.jonopituusxAxis.setCategories(this.jonoList);
        String[] columns4 = {"Käyttöprosentti","KäyttöprosenttiA","KäyttöprosenttiB","KäyttöprosenttiC"};
        this.prosenttiList.addAll(Arrays.asList(columns4));
        this.prosenttixAxis.setCategories(this.prosenttiList);




    }
    @FXML
    private void handlePrevious(){
        try{
            if(!rs.isLast()){
                rs.next();
                nullData();
                setData();
            }
        } catch (SQLException e){
            System.out.println("ei Toiminut");
        }

    }
    @FXML
    private void handleNext(){
        try{
            if(!rs.isFirst()){
                rs.previous();
                nullData();
                setData();
            }
        } catch (SQLException e){
            System.out.printf("Ei");

        }

    }
    public void nullData(){
        this.Asiakkaat.getData().clear();
        this.Keskiaika.getData().clear();
        this.Jonopituus.getData().clear();
        this.Prosentti.getData().clear();
    }



    public void setData(){
        try{
            maara = rs.getInt(3);
            maaraA = rs.getInt(4);
            maaraB = rs.getInt(5);
            maaraC = rs.getInt(6);
            keskiaika = rs.getDouble(7);
            keskiaikaA= rs.getDouble(8);
            keskiaikaB= rs.getDouble(9);
            keskiaikaC= rs.getDouble(10);
            jonopituusA= rs.getDouble(11);
            jonopituusB= rs.getDouble(12);
            jonopituusC= rs.getDouble(13);
            prosentti= rs.getDouble(14);
            prosenttiA= rs.getDouble(15);
            prosenttiB= rs.getDouble(16);
            prosenttiC= rs.getDouble(17);
            time = rs.getDouble(2);

        } catch (SQLException e) {

        }

        Series<String, Integer> series1 = new Series();
        series1.getData().add(new Data("AsiakkaidenMäärä", maara));
        series1.getData().add(new Data("MääräA", maaraA));
        series1.getData().add(new Data("MääräB", maaraB));
        series1.getData().add(new Data("MääräC", maaraC));
        this.Asiakkaat.getData().add(series1);
        Series<String, Integer> series2 = new Series();
        series2.getData().add(new Data("Keskiaika", keskiaika));
        series2.getData().add(new Data("KeskiaikaA", keskiaikaA));
        series2.getData().add(new Data("KeskiaikaB", keskiaikaB));
        series2.getData().add(new Data("KeskiaikaC", keskiaikaC));
        this.Keskiaika.getData().add(series2);
        Series<String, Integer> series3 = new Series();
        series3.getData().add(new Data("JononkeskipituusA", jonopituusA));
        series3.getData().add(new Data("JononkeskipituusB", jonopituusB));
        series3.getData().add(new Data("JononkeskipituusC", jonopituusC));
        this.Jonopituus.getData().add(series3);
        Series<String, Integer> series4 = new Series();
        series4.getData().add(new Data("Käyttöprosentti", prosentti));
        series4.getData().add(new Data("KäyttöprosenttiA", prosenttiA));
        series4.getData().add(new Data("KäyttöprosenttiB", prosenttiB));
        series4.getData().add(new Data("KäyttöprosenttiC", prosenttiC));
        this.Prosentti.getData().add(series4);
        aika.setText("Simulaation kesto: " + Double.toString(time));
    }
}