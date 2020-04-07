package com.model;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class News {
    private  int id_news;
    private String titre;
    private String contenu;
    private int id_news_reporter;
    private Date datePost;
    private List<Tag> tags = new ArrayList<Tag>();

    //Constructeur
    public News()
    {

    }
    public News(int id_news, String coronoVirus, java.util.Date endDate, int id_news_journaliste, String contenu){}

    public News(int id_news, String titre,Date datePost,int id_news_journaliste,String contenu ) {
        this.id_news = id_news;
        this.titre = titre;
        this.contenu = contenu;
        this.id_news_reporter = id_news_journaliste;
        this.datePost = datePost;
    }

    public News(int id_news, String titre, int id_news_journaliste, Date datePost) {
        this.id_news = id_news;
        this.titre = titre;
        //this.contenu = contenu;
        this.id_news_reporter = id_news_journaliste;
        this.datePost = datePost;
    }

    public News(int id_news, List<Tag> tags) {
        this.id_news = id_news;
        this.tags = tags;
    }

    //GETTERS & SETTERS
    public int getId_news() {
        return id_news;
    }

    public void setId_news(int id_news) {
        this.id_news = id_news;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getId_news_reporter() {
        return id_news_reporter;
    }

    public void setId_news_reporter(int id_news_reporter) {
        this.id_news_reporter = id_news_reporter;
    }

    public Date getDatePost() {
        return datePost;
    }

    public void setDatePost(Date datePost) {
        this.datePost = datePost;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "News{" +
                "id_news=" + id_news +
                ", titre='" + titre + '\'' +
                ", contenu='" + contenu + '\'' +
                ", id_news_journaliste=" + id_news_reporter +
                ", datePost=" + datePost +
                '}';
    }
}
