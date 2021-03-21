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

/**
 * Datacontolleri hoitaa kaikki tarvittavat  UI elementit kun halutaan esittää Dataa.
 *
 * @author Joni Tahvanainen ja Felix Uimonen
 * @version 1
 */

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

    /**
     * Luodaan dao joka on DataAccessObject.
     */
    public DataController(){ dao = new DataAccessObject(); }

    /**
     * Otetaan viimeisin tieto databasesistä ja tehdään valmiiksi barcahrtit että observablelistit.
     */
    @FXML
    private void initialize(){
        try{
            rs = dao.getRs();
            rs.last();
        } catch (SQLException e){

        }
        String[] columns = {"Kaikki","Tiski A","Tiski B","Tiski C"};
        this.AsiakasList.addAll(Arrays.asList(columns));
        this.AsiakkaatxAxis.setCategories(this.AsiakasList);
        String[] columns2 = {"Kaikki","Tiski A","Tiski B","Tiski C"};
        this.KeskiaikaList.addAll(Arrays.asList(columns2));
        this.KeskiaikaxAxis.setCategories(this.KeskiaikaList);
        String[] columns3 = {"Tiski A","Tiski B","Tiski C"};
        this.jonoList.addAll(Arrays.asList(columns3));
        this.jonopituusxAxis.setCategories(this.jonoList);
        String[] columns4 = {"Kaikki","Tiski A","Tiski B","Tiski C"};
        this.prosenttiList.addAll(Arrays.asList(columns4));
        this.prosenttixAxis.setCategories(this.prosenttiList);




    }

    /**
     * Avataan edellinen database.
     */
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

    /**
     * Avataan seuraava database.
     */
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

    /**
     * Poistatetaan tiedot barcharteistä
     */
    public void nullData(){
        this.Asiakkaat.getData().clear();
        this.Keskiaika.getData().clear();
        this.Jonopituus.getData().clear();
        this.Prosentti.getData().clear();
    }


    /**
     * Luodaan barchartit.
     */
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
        series1.getData().add(new Data("Kaikki", maara));
        series1.getData().add(new Data("Tiski A", maaraA));
        series1.getData().add(new Data("Tiski B", maaraB));
        series1.getData().add(new Data("Tiski C", maaraC));
        series1.setName("Asiakkaiden määrä");
        this.Asiakkaat.getData().add(series1);
        Series<String, Integer> series2 = new Series();
        series2.getData().add(new Data("Kaikki", keskiaika));
        series2.getData().add(new Data("Tiski A", keskiaikaA));
        series2.getData().add(new Data("Tiski B", keskiaikaB));
        series2.getData().add(new Data("Tiski C", keskiaikaC));
        series2.setName("Asiakkaiden läpimenoaikojen keskiarvo");
        this.Keskiaika.getData().add(series2);
        Series<String, Integer> series3 = new Series();
        series3.getData().add(new Data("Tiski A", jonopituusA));
        series3.getData().add(new Data("Tiski B", jonopituusB));
        series3.getData().add(new Data("Tiski C", jonopituusC));
        series3.setName("Keskimääräiset jononpituudet");
        this.Jonopituus.getData().add(series3);
        Series<String, Integer> series4 = new Series();
        series4.getData().add(new Data("Kaikki", prosentti));
        series4.getData().add(new Data("Tiski A", prosenttiA));
        series4.getData().add(new Data("Tiski B", prosenttiB));
        series4.getData().add(new Data("Tiski C", prosenttiC));
        series4.setName("Tiskien käyttöprosentti");
        this.Prosentti.getData().add(series4);
        aika.setText("Simulaation kesto: " + Double.toString(time));
    }
}