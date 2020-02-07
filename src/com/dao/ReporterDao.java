
package com.dao;

import com.model.ConnectionDERBY;
import com.model.Reporter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporterDao {


    private static String URL = "jdbc:derby:C:/Users/Hanane/Desktop/cours/JAVA/DERBY/testdb";
    private static String tableName = "JOURNALISTE";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;


    public static void main(String[] args) {
        createConnection();

    }

    private static void createConnection() {
        if (conn == null)
            System.out.println("trying to connect...");//pour afficher le message qu'une seule fois
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //Get a connection
            conn = DriverManager.getConnection(URL);
            //si connection ok => affiche :
            System.out.println("Connected...");

            Statement state = conn.createStatement();
        //L'objet ResultSet contient le résultat de la requête SQL

            ResultSet result = state.executeQuery("SELECT * FROM "+tableName);
         //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

    /*
    public void List<Reporter> selectAllReporters() {
        List<Reporter> reporters = new ArrayList<Reporter>();
        try {

            Statement smt = this.cnx.createStatement();
            String sql = "SELECT * FROM JOURNALISTE";
            //System.out.println(sql);
            ResultSet rs = smt.executeQuery(sql);

            Reporter reporter = new Reporter();
            try {
                while (rs.next()) {
                    int id = rs.getInt("id_journaliste");
                    String nom = rs.getString("login");
                    int age = rs.getInt("credit");
                    reporter.setI

                    reporters.add(reporter);
                    //System.out.println(salarie.getId() + " " + salarie.getNom() + "  " + salarie.getPrenom() + "  " + salarie.getAge() + "  " + salarie.getSalaire());
                }
                rs.close();
                smt.close();
            } catch (SQLException ex) {
                System.out.println("ERRUER D'AFFICHAGE");
            }

            //System.out.println("affichage ok");
            rs.close();
            smt.close();
        } catch (SQLException ex) {
            System.out.println("problème d'insertion");
        }

        return salaries;

    }
}
   /* protected ConnectionDERBY cnx = null;


    public ReporterDao() {

        ConnectionDERBY connection = new ConnectionDERBY();

        //DataSource dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/");
        this.cnx = (ConnectionDERBY) connection.getCon();

    }

    public void closeConn() {
        try {
            this.cnx.close();
        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("erreur de fermeture de connexion");
        }
    }

    public boolean findReporter(int id){
        Reporter reporter = new Reporter();
        boolean trouve = false;

        try{
            Statement smt = this.cnx.getCo
            String sql = "SELECT * FROM JOURNALISTE WHERE ID_JOURNALISTE="+id;
        }catch(SQLException e){

        }
    }
}
*/