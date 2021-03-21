package ui;

import com.sun.javafx.iio.ios.IosDescriptor;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import simu.framework.Trace;
import simu.model.IOmaMoottori;

import simu.model.OmaMoottori;
import simu.model.Saie;


import javax.xml.crypto.Data;
import java.io.IOException;

/**
 * Luokka kutsuu jokaista Ui elementtiä.
 * ja availee niitä sen mukaan.
 *
 * @author Joni Tahvanainen ja Felix Uimonen
 * @version 1
 */



public class MainUI extends Application {
    IOmaMoottori m;
    SimuControlsOverview simuControlsOverview;
    private BorderPane rootLayout;
    private Stage primaryStage;


    /**
     * Start.
     *
     * @param primaryStage primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Trace.setTraceLevel(Trace.Level.INFO);
        m = new OmaMoottori();

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Simulaattori Projekti");

        //initUI(stage);
        initRootLayout();

        showSimuControls();


    }


    /**
     * Avaa alku ruudun kun sovellus käynnistetään.
     */
    public void initRootLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainUI.class.getResource("fxml/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            RootLayoutController rootlayout = loader.getController();
            rootlayout.setMain(this);


            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Avaa simulaattorin.
     */
    public void showSimuControls() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainUI.class.getResource("fxml/SimuControlsOverview.fxml"));
            AnchorPane ControlsOverview = (AnchorPane) loader.load();
            simuControlsOverview = loader.getController();

            simuControlsOverview.setMainUI(this, m);

            rootLayout.setCenter(ControlsOverview);

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    /**
     * Avaa DataBasen
     */
    public void showDataController() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainUI.class.getResource("fxml/Data.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            DataController controller = loader.getController();
            controller.setData();

            // Set the persons into the controller.


            dialogStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Metodi aloittaa simulaattorin.
     *
     * @param time On kuinka kauan simulaattori on käynnissä.
     */
    public void SimuStart(double time){
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainUI.class.getResource("fxml/SimuAnimationOverview.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            SimuAnimation sa = loader.getController();

            sa.setMainUI(this,m);
            sa.setCheckbox(simuControlsOverview.getCheckbox());
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Simu Animation");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.show();
            m.alustaTiskit();
            m.setSimulointiaika(time);
            Saie saie = new Saie(m);
            saie.start();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
