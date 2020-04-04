package com.dao;


import com.model.ConnectionDERBY;
import com.model.News;
import com.model.Tag;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDao {

    private static String tableName = "NEWS";
    // jdbc Connection
    private static Connection conn = null;

    public NewsDao() {
        ConnectionDERBY connection = new ConnectionDERBY();
        this.conn = connection.getCon();
    }


    // Find News whith news's id
    public News findNewsById(int id) {
        News news = null;

        System.out.println("\n---------------------SELECT NEWS OF JOURNALISTE----------------------------");

        try {

            String sql = "select * FROM NEWS where id_news= "+id;
            PreparedStatement state = conn.prepareStatement(sql);
            String sql2 = "select * from news inner join tag_news " +
                    " on news.id_news = tag_news.id_tag_news " +
                    " inner join tag " +
                    " on tag.id = tag_news.id_tag " +
                    " where news.id_news=" + id;
            ResultSet result = state.executeQuery();
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();
            try {
                result.next();
                //System.out.println("je passe");
                news = new News(result.getInt("id_news"), result.getString("titre"), result.getInt("id_news_journaliste"), result.getDate("datePost"));
                System.out.println(news.toString());
                result.close();
                state.close();
            } catch (SQLException ex) {
                System.out.println("la news dont l'id = " + id + ", n'existe pas dans la BD");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return news;
    }



    //find news by Reporter's Name
    public News findNewsByReportersName(String nameReporter) {
        System.out.println("************************FIND NEWS BY REPORTER'S NAME ****************");
        News news = null;

        try {
            Statement smt = this.conn.createStatement();
            String sql = "select * from news join journaliste on news.id_news_journaliste=journaliste.id_journaliste where journaliste.id_journaliste in (select id_journaliste from journaliste where login = "+nameReporter+");";
            ResultSet rs = smt.executeQuery(sql);
                rs.next();
                news = new News(rs.getInt("id_news"), rs.getString("titre"), rs.getInt("id_news_journaliste"), rs.getDate("datepost"));
                System.out.println("news by name "+news.toString());
                rs.close();
                smt.close();
        } catch (SQLException e) {
            System.out.println("prb d'éxecution de la requette"+e.getErrorCode());
        }
        return news;
    }




    private  void selectAllNews(){
        System.out.println("************************SELECT ALL NEWS ****************");
        try {
            Statement state = conn.createStatement();
            ResultSet result = state.executeQuery("SELECT * FROM " + tableName);
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
                String titre = result.getString(2);
                String contenu = result.getString(3);
                Date datePost = result.getDate(4);
                int id_news_journaliste = result.getInt(5);

                System.out.println(id + "\t\t" + titre + "\t\t" + contenu+ "\t\t" +id_news_journaliste+ "\t\t" +datePost);
            }
            result.close();
            state.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

//find tag's news
    public List<Tag> findTagsOfNews(int id){
        System.out.println("************************Find Tag4S list Of News ****************");
        Tag tag = null;
        List<Tag> tags = new ArrayList<Tag>();
        try{
            Statement smt = this.conn.createStatement();
            String sql = " select tag.id , tag.nom from tag " +
                    "join tag_news on tag_news.fk_id_tag = tag.id " +
                    "join news on news.id_news=tag_news.fk_id_news " +
                    "where news.id_news = "+id ;
            ResultSet rs = smt.executeQuery(sql);
                while(rs.next()) {
                    tag = new Tag(rs.getInt("id"), rs.getString("nom"));
                    tags.add(tag);
                    System.out.println(tag.getId_tag() + ", " + tag.getNom());
                }
                 rs.close();
                smt.close();
        }catch(SQLException ex){
           System.out.println(ex.getMessage());
        }

        return tags;
    }

//insert news

public  void insertNews(News s) {

    System.out.println("************************INSERT News ****************");
    try {

        Statement smt = conn.createStatement();

        String sql = "INSERT INTO news (id_news,titre,datepost,id_news_journaliste,contenu) VALUES ("+s.getId_news()+",'"+
        s.getTitre()+"','"+s.getDatePost()+"',"+s.getId_news_reporter()+",'"+s.getContenu()+"')";
        System.out.println(sql);
        int rs = smt.executeUpdate(sql);//c'est une methode de l'interface statement on utilise executeUpdate pour update, insert, delete
        System.out.println("insertion ok : "+rs);
        smt.close();
    }catch(SQLException ex) {
        System.out.println("problème d'insertion : "+ ex.getMessage());
    }

}
public void insertNewsByTags(int id,List<Tag> tags) {
    System.out.println("************************INSERT News by Tags ****************");

   News n =  this.findNewsById(id);

    for (Tag t : tags) {
        try {
            String sql = "insert into tag_news (fk_id_news,fk_id_tag) values("+n.getId_news()+","+t.getId_tag()+ ")";
            PreparedStatement smt = conn.prepareStatement(sql);
           int result = smt.executeUpdate();
            System.out.println("insertion OK");
            smt.close();
        } catch (SQLException e) {
            System.out.println("problème d'insertion : " + e.getMessage());
        }

    }
}


}