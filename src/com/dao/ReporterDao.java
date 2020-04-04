
package com.dao;

import com.model.ConnectionDERBY;
import com.model.Reporter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporterDao {

    private static Connection cnx = null;

    public ReporterDao(){

        ConnectionDERBY connection = new ConnectionDERBY();
        this.cnx = connection.getCon();

    }


    public static void selectAllReporters() {

        try {
            Statement state = cnx.createStatement();
            ResultSet result = state.executeQuery("SELECT * FROM  JOURNALISTE");
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();
            int numberCols = resultMeta.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                //print Column Names
                System.out.print(resultMeta.getColumnLabel(i) + "\t\t");
            }
            System.out.println("\n-------------------------------------------------");

            while (result.next()) {
                int id = result.getInt(1);
                String login = result.getString(2);
                int credit = result.getInt(3);
                System.out.println(id + "\t\t" + login + "\t\t" + credit);
            }
            result.close();
            state.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public static void findReporter(int id) {

        Reporter reporter = new Reporter();
        System.out.println("\n---------------------SELECT REPORTER----------------------------");

        try {
            Statement state = cnx.createStatement();
            String sql="SELECT * FROM JOURNALISTE WHERE id_journaliste="+id;
            ResultSet result = state.executeQuery(sql);
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();
            try {
                 result.next();
                 reporter = new Reporter(result.getInt("id_journaliste"),result.getString("login"),result.getInt("credit"));
                 System.out.println(reporter.toString());
                result.close();
                state.close();
            }catch(SQLException ex) {
                System.out.println("le journaliste dont l'id = "+id+", n'existe pas dans la BD");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
