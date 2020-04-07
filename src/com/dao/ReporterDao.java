package com.dao;
import com.model.ConnectionFactory;
import com.model.Reporter;
import java.sql.*;

public class ReporterDao {

    private ConnectionFactory connectionFactory;

    public ReporterDao(){
        connectionFactory = new ConnectionFactory();
        }


    public void selectAllReporters() {

        try {
            Connection connection = this.connectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM  JOURNALISTE");
            ResultSet result = statement.executeQuery();
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
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void findReporter(int id) {
        Reporter reporter = new Reporter();
        System.out.println("\n---------------------SELECT REPORTER----------------------------");

        try {
            Connection connection = this.connectionFactory.getConnection();
            String sql="SELECT * FROM JOURNALISTE WHERE id_journaliste="+id;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql);
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();
            try {
                 result.next();
                 reporter = new Reporter(result.getInt("id_journaliste"),result.getString("login"),result.getInt("credit"));
                 System.out.println(reporter.toString());
                result.close();
                statement.close();
            }catch(SQLException ex) {
                System.out.println("le journaliste dont l'id = "+id+", n'existe pas dans la BD");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
