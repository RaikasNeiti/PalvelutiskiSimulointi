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
import simu.model.OmaMoottori;
import simu.model.Saie;


import javax.xml.crypto.Data;
import java.io.IOException;


public class MainUI extends Application {
    OmaMoottori m;
    SimuControlsOverview simuControlsOverview;
    private BorderPane rootLayout;
    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Trace.setTraceLevel(Trace.Level.INFO);
        m = new OmaMoottori();

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Testi");

        //initUI(stage);
        initRootLayout();

        showSimuControls();


    }



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

            // Set the persons into the controller.


            dialogStage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
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

    /*
    private void initUI(Stage stage){
        BorderPane roota = new BorderPane();
        HBox root = new HBox(5);
        HBox roots = new HBox();
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.BASELINE_RIGHT);


        Button asetaBtn = new Button("Aseta Aika");

        TextField field = new TextField();
        TextFormatter<Double> formatter = new TextFormatter<>(new DoubleStringConverter(), 1500d);
        field.setTextFormatter(formatter);


        var text = new Text("Palvelutiski simulaattori.");

        text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        text.setId("text");

        root.getChildren().addAll(field,asetaBtn);
        root.setAlignment(Pos.BOTTOM_CENTER);
        roots.getChildren().add(text);
        roots.setAlignment(Pos.TOP_CENTER);
        roota.setCenter(root);
        roota.setTop(roots);
        Scene scene = new Scene(roota,300,150);
        stage.setTitle("MainUi");
        stage.setScene(scene);
        stage.show();

        asetaBtn.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
               try{
                   value = Double.parseDouble(field.getText());
                   System.out.println(value);
                   m.setSimulointiaika(value);
                   m.aja();
               } catch (NumberFormatException e) {
                   System.out.println("Not a number.");
               }
            }
        });

    }

     */


    public static void main(String[] args) {
        launch(args);
    }
}
