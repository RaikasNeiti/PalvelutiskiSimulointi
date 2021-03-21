package simu.model;


import java.sql.*;
/**
 * Tietokannan käsittelyssä käytettävä luokka
 *
 * @author Joni Tahvanainen ja Felix Uimonen
 * @version 1
 */


public class DataAccessObject {

    Connection connection;
    Statement statement = null;
    ResultSet rs = null;
    OmaMoottori m;


    /**
     * Luo yhteyden tietokantaan
     */
    public DataAccessObject() {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost/FJData?user=olso&password=olso");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getErrorCode());
            System.err.println(e.getSQLState());
            System.err.println("JBDC-ajurin lataus epäonnistui");
            System.exit(-1);
        }
    }
    /**
     * Luo yhteyden tietokantaan
     */
    public DataAccessObject(OmaMoottori m) {
        this.m = m;
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost/FJData?user=olso&password=olso");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getErrorCode());
            System.err.println(e.getSQLState());
            System.err.println("JBDC-ajurin lataus epäonnistui");
            System.exit(-1);
        }
    }

    /**
     * Lisää simulaation tulokset tietokantaan
     * @return Virheentarkitus tieetokantaan lisääämisessä
     */
    public boolean lisääTulokset(){
        try {
            statement = connection.createStatement();
            int x = statement.executeUpdate("INSERT INTO tulokset(aika, maara, maaraA, maaraB, maaraC, keskiaika, keskiaikaA, keskiaikaB, keskiaikaC, jononPituusA, jononPituusB, jononPituusC,kayttoprosentti," +
                    " kayttoprosenttiA,kayttoprosenttiB, kayttoprosenttiC) VALUES('" + m.loppuAika + "', '" + m.asiakkaidenMäärä + "', '" + m.määräA + "', '" + m.määräB + "', '" + m.määräC + "'" +
                    ", '" + m.keskiaika + "', '" + m.keskiaikaA + "', '" + m.keskiaikaB + "', '" + m.keskiaikaC + "', '" + m.jononKeskipituusA + "', '" + m.jononKeskipituusB + "', '" + m.jononKeskipituusC + "'" +
                    ", '" + m.käyttöprosentti + "', '" + m.käyttöprosenttiA + "', '" + m.käyttöprosenttiB + "', '" + m.käyttöprosenttiC + "')");
            if (x == 1) {
                return true;
            } else {
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Hakee tulokset tietokannasta
     * @return tietokannasta haetut tulokset
     */
    public ResultSet getRs() {
        try{
        statement = connection.createStatement();
        rs = statement.executeQuery("SELECT * FROM tulokset");
        return rs;
        }catch (SQLException e){

        }
        return null;
    }
}
