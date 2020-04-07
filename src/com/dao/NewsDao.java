package com.dao;

import com.Exception.MyExceptions;

import com.model.ConnectionFactory;
import com.model.News;
import com.model.Tag;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDao {

    private static String tableName = "NEWS";
    News news = new News();
    // jdbc Connection

    private ConnectionFactory connectionFactory;

    public NewsDao() {
      connectionFactory = new ConnectionFactory();

    }
    // Find News whith news's id
    public News findNewsById(int id) throws Exception {

            System.out.println("\n---------------------SELECT NEWS OF JOURNALISTE----------------------------");
        try {
            Connection connection = this.connectionFactory.getConnection();
            String sql = "select * FROM NEWS where id_news= ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();

            try {
               if(result.next()) {
                   //System.out.println("je passe");
                   news.setId_news(result.getInt("id_news"));
                   news.setTitre(result.getString("titre"));
                   news.setContenu(result.getString("contenu"));
                   news.setId_news_reporter(result.getInt("id_news_journaliste"));
                   news.setDatePost(result.getDate("datePost"));
                   System.out.println(news);
                   result.close();
                   statement.close();
               }
               else{
                   System.out.println("ergfdg");
               }
            } catch (Exception ex) {
                throw new Exception("la news dont l'id = " + id + ", n'existe pas dans la BD");

            }
        } catch (SQLException ex) {
            throw new Exception("problème d'execution de la requete");
        }
        return news;
    }



    //find news by Reporter's Name
    public News findNewsByReportersName(String nameReporter) throws  Exception{
        System.out.println("************************FIND NEWS BY REPORTER'S NAME ****************");

        try {
            Connection connection = this.connectionFactory.getConnection();
             String sql = "select * from news join journaliste on news.id_news_journaliste=journaliste.id_journaliste where journaliste.id_journaliste in (select id_journaliste from journaliste where login = "+nameReporter+");";
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery();
                while(result.next()) {
                    news.setId_news(result.getInt("id_news"));
                    news.setTitre(result.getString("titre"));
                    news.setContenu(result.getString("contenu"));
                    news.setId_news_reporter(result.getInt("id_news_journaliste"));
                    news.setDatePost(result.getDate("datePost"));
                    System.out.println(news.toString());
                    System.out.println("news by name " + news.toString());
                }
                result.close();
                statement.close();
        } catch (SQLException e) {
            throw new Exception("prb d'éxecution de la requette"+e.getErrorCode());

        }
        return news;
    }


    public  ArrayList<News> selectAllNews() throws Exception{
        ArrayList<News> newsList = new ArrayList<News>();
        System.out.println("************************SELECT ALL NEWS ****************");
        try {
            Connection connection = this.connectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + tableName);
            ResultSet result = statement.executeQuery();
          //  newsList.add(new NewsDTO(result.getInt("id_news"),result.getString("titre"),result.getString("contenu"),result.getInt("id_news_journaliste"),result.getDate("datePost")));
            while (result.next()) {
                News news = new News();
                news.setId_news(result.getInt("id_news"));
                news.setTitre(result.getString("titre"));
                news.setContenu(result.getString("contenu"));
                news.setId_news_reporter(result.getInt("id_news_journaliste"));
                news.setDatePost(result.getDate("datePost"));
                newsList.add(news);
            }

            //affichage de la liste des news
            for(News newsDto: newsList)
                System.out.println(newsDto.toString());

            result.close();
            statement.close();
        } catch (SQLException ex) {
            throw new Exception("prb d'éxecution de la requette"+ex.getErrorCode());
        }
        return  newsList;
    }

//find tag's news
    public List<Tag> findTagsOfNews(int id) throws Exception{
        System.out.println("************************Find Tag4S list Of News ****************");
        Tag tag = null;
        List<Tag> tags = new ArrayList<Tag>();
        try{
            Connection connection = this.connectionFactory.getConnection();
            String sql = " select tag.id , tag.nom from tag " +
                    "join tag_news on tag_news.fk_id_tag = tag.id " +
                    "join news on news.id_news=tag_news.fk_id_news " +
                    "where news.id_news = "+id ;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery(sql);
                while(result.next()) {
                    tag = new Tag(result.getInt("id"), result.getString("nom"));
                    tags.add(tag);
                    System.out.println(tag.getId_tag() + ", " + tag.getNom());
                }
                 result.close();
                statement.close();
        }catch(SQLException ex){
            throw new Exception("prb d'éxecution de la requette"+ex.getErrorCode());
        }

        return tags;
    }

//insert news
public  void insertNews(News s) throws Exception {

    System.out.println("************************INSERT News ****************");
    try {
        Connection connection = this.connectionFactory.getConnection();
        String sql = "INSERT INTO news (id_news,titre,datepost,id_news_journaliste,contenu) VALUES ("+s.getId_news()+",'"+
        s.getTitre()+"','"+s.getDatePost()+"',"+s.getId_news_reporter()+",'"+s.getContenu()+"')";
        PreparedStatement statement = connection.prepareStatement(sql);
        System.out.println(sql);
        int rs = statement.executeUpdate(sql);//c'est une methode de l'interface statement on utilise executeUpdate pour update, insert, delete
        System.out.println("insertion ok : "+rs);
        statement.close();
    }catch(SQLException ex) {
        throw new Exception("prb d'éxecution de la requette"+ex.getErrorCode());
    }

}

public void insertNewsByTags(int id,List<Tag> tags) throws Exception, MyExceptions {
    System.out.println("************************INSERT News by Tags ****************");

    News news =  this.findNewsById(id);

    for (Tag t : tags) {
        try {
            Connection connection = this.connectionFactory.getConnection();
            String sql = "insert into tag_news (fk_id_news,fk_id_tag) values("+news.getId_news()+","+t.getId_tag()+ ")";
            PreparedStatement statement = connection.prepareStatement(sql);
           int result = statement.executeUpdate();
            System.out.println("insertion OK");
            statement.close();
        } catch (SQLException e) {
            throw new MyExceptions("problème d'insertion : " + e.getMessage());

        }

    }
}


}