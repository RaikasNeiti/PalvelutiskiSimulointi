package ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import simu.model.OmaMoottori;

public class SimuAnimation {
    MainUI main;
    private boolean pause = false;
    OmaMoottori m;
    private int saapumisid = 1;
    private Image vihree = new Image("/resources/img/Vihreekuutio.png");
    private Image punainen = new Image("/resources/img/Punainenkuutio.png");
    private Image harmaa = new Image("/resources/img/Harmaakuutio.png");
    private boolean A_a = false, A_b = false, A_c = false, B_a = false, B_b = false, B_c = false, C_a = false, C_b = false, C_c = false;
    private int i;


    @FXML
    private ImageView button_A_a;
    @FXML
    private ImageView button_A_b;
    @FXML
    private ImageView button_A_c;

    @FXML
    private TextArea console;

    @FXML
    private ImageView button_B_a;
    @FXML
    private ImageView button_B_b;
    @FXML
    private ImageView button_B_c;

    @FXML
    private ImageView button_C_a;
    @FXML
    private ImageView button_C_b;
    @FXML
    private ImageView button_C_c;

    @FXML
    private Label tiskiAjono;
    @FXML
    private Label tiskiBjono;
    @FXML
    private Label tiskiCjono;

    @FXML
    private TextArea vuoronumero;

    @FXML
    private ImageView TiskiAJono1;
    @FXML
    private ImageView TiskiAJono2;
    @FXML
    private ImageView TiskiAJono3;
    @FXML
    private ImageView TiskiAJono4;
    @FXML
    private ImageView TiskiAJono5;

    @FXML
    private ImageView TiskiBJono1;
    @FXML
    private ImageView TiskiBJono2;
    @FXML
    private ImageView TiskiBJono3;
    @FXML
    private ImageView TiskiBJono4;
    @FXML
    private ImageView TiskiBJono5;

    @FXML
    private ImageView TiskiCJono1;
    @FXML
    private ImageView TiskiCJono2;
    @FXML
    private ImageView TiskiCJono3;
    @FXML
    private ImageView TiskiCJono4;
    @FXML
    private ImageView TiskiCJono5;




    public void setMainUI(MainUI main, OmaMoottori m){
        this.m = m;
        this.main = main;
        m.setAnim(this);
    }
    public void setCheckbox(boolean[] check){

        if(check[0] == true){
            A_a = true;
            button_A_a.setImage(vihree);
        }
        if(check[1] == true){
            A_b = true;
            button_A_b.setImage(vihree);
        }
        if(check[2] == true){
            A_c = true;
            button_A_c.setImage(vihree);
        }
        if(check[3] == true){
            B_a = true;
            button_B_a.setImage(vihree);
        }
        if(check[4] == true){
            B_b = true;
            button_B_b.setImage(vihree);
        }
        if(check[5] == true){
            B_c = true;
            button_B_c.setImage(vihree);
        }
        if(check[6] == true){
            C_a = true;
            button_C_a.setImage(vihree);
        }
        if(check[7] == true){
            C_b = true;
            button_C_b.setImage(vihree);
        }
        if(check[8] == true){
            C_c = true;
            button_C_c.setImage(vihree);
        }

    }
    public void SetConsole(int id, double poistumisaika, String tiski){
        console.setText(console.getText() + "Asiakas: "+ id +" Poistui tiskiltä "+ tiski.substring(5,6) + "\nKello " + poistumisaika + "\n");
        console.selectEnd();
        console.deselect();
    }
    public void setSaapui(){
        console.setText(console.getText()+"Asiakas " + saapumisid++ + " saapui jonoon \n");
        console.selectEnd();
        console.deselect();
    }


    public void UpdateJonot(int[] jonot){

        if(jonot[0] >= 1){ TiskiAJono1.setImage(harmaa); }
        else{ TiskiAJono1.setImage(null); }

        if(jonot[0] >= 2){ TiskiAJono2.setImage(harmaa); }
        else{ TiskiAJono2.setImage(null); }

        if(jonot[0] >= 3){ TiskiAJono3.setImage(harmaa); }
        else{ TiskiAJono3.setImage(null); }

        if(jonot[0] >= 4){ TiskiAJono4.setImage(harmaa); }
        else{ TiskiAJono4.setImage(null); }

        if(jonot[0] >= 5){ TiskiAJono5.setImage(harmaa); }
        else{ TiskiAJono5.setImage(null); }

        if(jonot[0] >= 6){ tiskiAjono.setText("+ " + Integer.toString(jonot[0]- 5)); }
        else{ tiskiAjono.setText(""); }


        if(jonot[1] >= 1){ TiskiBJono1.setImage(harmaa); }
        else{ TiskiBJono1.setImage(null); }

        if(jonot[1] >= 2){ TiskiBJono2.setImage(harmaa); }
        else{ TiskiBJono2.setImage(null); }

        if(jonot[1] >= 3){ TiskiBJono3.setImage(harmaa); }
        else{ TiskiBJono3.setImage(null); }

        if(jonot[1] >= 4){ TiskiBJono4.setImage(harmaa); }
        else{ TiskiBJono4.setImage(null); }

        if(jonot[1] >= 5){ TiskiBJono5.setImage(harmaa); }
        else{ TiskiBJono5.setImage(null); }

        if(jonot[1] >= 6){ tiskiBjono.setText("+ " + Integer.toString(jonot[1]- 5)); }
        else{ tiskiBjono.setText(""); }



        if(jonot[2] >= 1){ TiskiCJono1.setImage(harmaa); }
        else{ TiskiCJono1.setImage(null); }

        if(jonot[2] >= 2){ TiskiCJono2.setImage(harmaa); }
        else{ TiskiCJono2.setImage(null); }

        if(jonot[2] >= 3){ TiskiCJono3.setImage(harmaa); }
        else{ TiskiCJono3.setImage(null); }

        if(jonot[2] >= 4){ TiskiCJono4.setImage(harmaa); }
        else{ TiskiCJono4.setImage(null); }

        if(jonot[2] >= 5){ TiskiCJono5.setImage(harmaa); }
        else{ TiskiCJono5.setImage(null); }

        if(jonot[2] >= 6){ tiskiCjono.setText("+ " + Integer.toString(jonot[2]- 5)); }
        else{ tiskiCjono.setText(""); }

    }
    public void UpdateTiskit(boolean[] varattu, boolean[] aktiivinen){

        if(!aktiivinen[0]){
            button_A_a.setImage(harmaa);
        } else if(aktiivinen[0] && varattu[0]){
            button_A_a.setImage(punainen);
        } else {
            button_A_a.setImage(vihree);
        }

        if(!aktiivinen[1]){
            button_A_b.setImage(harmaa);
        } else if(aktiivinen[1] && varattu[1]){
            button_A_b.setImage(punainen);
        } else {
            button_A_b.setImage(vihree);
        }

        if(!aktiivinen[2]){
            button_A_c.setImage(harmaa);
        } else if(aktiivinen[2] && varattu[2]){
            button_A_c.setImage(punainen);
        } else {
            button_A_c.setImage(vihree);
        }

        if(!aktiivinen[3]){
            button_B_a.setImage(harmaa);
        } else if(aktiivinen[3] && varattu[3]){
            button_B_a.setImage(punainen);
        } else {
            button_B_a.setImage(vihree);
        }

        if(!aktiivinen[4]){
            button_B_b.setImage(harmaa);
        } else if(aktiivinen[4] && varattu[4]){
            button_B_b.setImage(punainen);
        } else {
            button_B_b.setImage(vihree);
        }

        if(!aktiivinen[5]){
            button_B_c.setImage(harmaa);
        } else if(aktiivinen[5] && varattu[5]){
            button_B_c.setImage(punainen);
        } else {
            button_B_c.setImage(vihree);
        }

        if(!aktiivinen[6]){
            button_C_a.setImage(harmaa);
        } else if(aktiivinen[6] && varattu[6]){
            button_C_a.setImage(punainen);
        } else {
            button_C_a.setImage(vihree);
        }

        if(!aktiivinen[7]){
            button_C_b.setImage(harmaa);
        } else if(aktiivinen[7] && varattu[7]){
            button_C_b.setImage(punainen);
        } else {
            button_C_b.setImage(vihree);
        }

        if(!aktiivinen[8]){
            button_C_c.setImage(harmaa);
        } else if(aktiivinen[8] && varattu[8]){
            button_C_c.setImage(punainen);
        } else {
            button_C_c.setImage(vihree);
        }
    }
    public void UpdateVuoronumero(String vuoro){

        vuoronumero.setText(vuoronumero.getText() + vuoro + "\n");
        vuoronumero.setScrollTop(Double.MAX_VALUE);
    }
    @FXML
    private void handleButtonPause(){

        if(pause){
            console.setText(console.getText() + "Resumed\n" );
            console.selectEnd();
            console.deselect();
            pause = false;
        }else{
            console.setText(console.getText() + "Paused\n" );
            console.selectEnd();
            console.deselect();
            pause = true;
        }
        m.setPause();


    }
    @FXML
    public void closedown(){
        Stage stage = (Stage) button_A_a.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void handleSpeedUp(){
        m.setSpeed(true);
        console.setText(console.getText()+"Nopeutettu. \n");
        console.selectEnd();
        console.deselect();
    }
    @FXML
    private void handleSlowDown(){
        m.setSpeed(false);
        console.setText(console.getText()+"Hidastettu. \n");
        console.selectEnd();
        console.deselect();
    }


    @FXML
    private void handleButtonA_a(){
        if(A_a == true){
            button_A_a.setImage(harmaa);
            A_a = false;
        }
        else{
            button_A_a.setImage(vihree);
            A_a = true;
        }

        m.setTiskiAktiivinen(0 , A_a);
    }
    @FXML
    private void handleButtonA_b(){
        if(A_b == true){
            button_A_b.setImage(harmaa);
            A_b = false;
        }
        else{
            button_A_b.setImage(vihree);
            A_b = true;
        }
        m.setTiskiAktiivinen(1 , A_b);
    }
    @FXML
    private void handleButtonA_c(){
        if(A_c == true){
            button_A_c.setImage(harmaa);
            A_c = false;
        }
        else{
            button_A_c.setImage(vihree);
            A_c = true;
        }
        m.setTiskiAktiivinen(2, A_c);
    }
    @FXML
    private void handleButtonB_a(){
        if(B_a == true){
            button_B_a.setImage(harmaa);
            B_a = false;
        }
        else{
            button_B_a.setImage(vihree);
            B_a = true;
        }
        m.setTiskiAktiivinen(3 , B_a);
    }
    @FXML
    private void handleButtonB_b(){
        if(B_b == true){
            button_B_b.setImage(harmaa);
            B_b = false;
        }
        else{
            button_B_b.setImage(vihree);
            B_b = true;
        }
        m.setTiskiAktiivinen(4 , B_b);
    }
    @FXML
    private void handleButtonB_c(){
        if(B_c == true){
            button_B_c.setImage(harmaa);
            B_c = false;
        }
        else{
            button_B_c.setImage(vihree);
            B_c = true;
        }
        m.setTiskiAktiivinen(5, B_c);
    }
    @FXML
    private void handleButtonC_a(){
        if(C_a == true){
            button_C_a.setImage(harmaa);
            C_a = false;
        }
        else{
            button_C_a.setImage(vihree);
            C_a = true;
        }
        m.setTiskiAktiivinen(6 , C_a);
    }
    @FXML
    private void handleButtonC_b(){
        if(C_b == true){
            button_C_b.setImage(harmaa);
            C_b = false;
        }
        else{
            button_C_b.setImage(vihree);
            C_b = true;
        }
        m.setTiskiAktiivinen(7 , C_b);
    }
    @FXML
    private void handleButtonC_c(){
        if(C_c == true){
            button_C_c.setImage(harmaa);
            C_c = false;
        }
        else{
            button_C_c.setImage(vihree);
            C_c = true;
        }
        m.setTiskiAktiivinen(8, C_c);
    }
}
