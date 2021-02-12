package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        initUI(stage);

    }

    private void initUI(Stage stage){
        BorderPane roota = new BorderPane();
        HBox root = new HBox(5);
        HBox roots = new HBox();
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.BASELINE_RIGHT);

        Button aamuBtn = new Button("aamu");
        Button paivaBtn = new Button("paiva");
        Button iltaBtn = new Button("ilta");
        Button yoBtn = new Button("yo");

        var text = new Text("Napsauta painiketta.");

        text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        text.setId("text");

        root.getChildren().addAll(aamuBtn, paivaBtn, iltaBtn, yoBtn);
        root.setAlignment(Pos.BOTTOM_CENTER);
        roots.getChildren().add(text);
        roots.setAlignment(Pos.TOP_CENTER);
        roota.setCenter(root);
        roota.setTop(roots);
        Scene scene = new Scene(roota,300,150);
        stage.setTitle("Row of buttons");
        stage.setScene(scene);
        stage.show();

    }
}
