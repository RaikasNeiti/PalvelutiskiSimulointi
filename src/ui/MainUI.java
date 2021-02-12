package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.util.converter.DoubleStringConverter;
import simu.framework.Trace;
import simu.model.OmaMoottori;



public class MainUI extends Application {
    private double value;
    OmaMoottori  m;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Trace.setTraceLevel(Trace.Level.INFO);
        m = new OmaMoottori();
        initUI(stage);

    }

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
}
