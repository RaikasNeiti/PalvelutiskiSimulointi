package ui;

import javafx.fxml.FXML;
import ui.MainUI;
/**
 * Avaa rootlayout ui:n jossa on löytyy menu josta voi avata databasen.
 *
 * @author Joni Tahvanainen ja Felix Uimonen
 * @version 1
 */

public class RootLayoutController {

    private MainUI main;

    public RootLayoutController(){

    }

    /**
     *
     * Luokalla on tämän jälkeen käytössä MainUI.
     *
     * @param main Main UI luokka
     */
    public void setMain(MainUI main) {
        this.main = main;
    }

    /**
     * Avaa Databasen.
     */
    @FXML
    private void handleShowData(){
        main.showDataController();
    }
}
