package simu.model;


import java.sql.*;



public class DataAccessObject {

    Connection connection;
    Statement statement = null;
    ResultSet rs = null;
    OmaMoottori m;


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
    /*
    public boolean lisääTulokset(){
        int x =  statement.executeUpdate("INSERT INTO tulokset VALUES('" + m + "', '" + m + "', '" + m + "')");
        if (x == 1) {
            return true;
        }else {
            return false;
        }
    }

     */

}
