package ui;

import javafx.fxml.FXML;
import ui.MainUI;
public class RootLayoutController {

    private MainUI main;

    public RootLayoutController(){

    }

    public void setMain(MainUI main) {
        this.main = main;
    }

    @FXML
    private void handleShowData(){
        main.showDataController();
    }
}
