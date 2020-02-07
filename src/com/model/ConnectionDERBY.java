package com.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDERBY {
    private static Connection con;
    private final String URL = "jdbc:derby:file:C:\\Users\\Hanane\\Desktop\\cours\\JAVA\\DERBY\\testdb";
    //on mets \\ pour windows 	//on se connecte à la base de donnée jdbc: type de driver(dans ce cas hsqldb)
   // private final String user = "SA";
    //private final String pw = "";
    public ConnectionDERBY() {

        if(con==null)
            System.out.println("trying to connect...") ;//pour afficher le message qu'une seule fois

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection(this.URL);
            //si connection ok => affiche :
            System.out.println("Connected...") ;
        }  catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getCon() {

        if(con == null) {//si aucune connection auparavant

            System.out.println("1ère connection BD..");
            new ConnectionDERBY();
        }
        return con;
    }




}
