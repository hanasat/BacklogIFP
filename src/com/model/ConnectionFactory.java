package com.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    private Connection connection;
    private  String URL = "jdbc:derby:C:/Users/Hanane/Desktop/cours/JAVA/DERBY/testdb";
    //on mets \\ pour windows 	//on se connecte à la base de donnée jdbc: type de driver(dans ce cas hsqldb)
    // private final String user = "SA";
    //private final String pw = "";
    public ConnectionFactory() {

        if(connection ==null)
            System.out.println("trying to connect...") ;//pour afficher le message qu'une seule fois

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(this.URL);
            //si connection ok => affiche :
            System.out.println("Connected...") ;
        }  catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public  Connection getConnection() {

        if(connection == null) {//si aucune connection auparavant

            System.out.println("1ère connection BD..");
            new ConnectionFactory();
        }
        return connection;
    }

}
