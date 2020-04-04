package com.program;

import com.dao.NewsDao;
import com.dao.ReporterDao;
import com.model.News;
import com.model.Tag;

import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) throws SQLException{

        ReporterDao reporter = new ReporterDao();
        NewsDao news = new NewsDao();
        Date date = new Date(2020-03-01);
        Tag tag1 = new Tag(1,"santé");
        Tag tag2 = new Tag(2,"informatique");
        Tag tag3 = new Tag(3,"alimentation");
        ArrayList<Tag> tags = new ArrayList<Tag>();
        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);


        //reporter.findReporter(1)
        //news.findNewsById(1);
        //news.findNewsByReportersName("Hanane");
       // news.findTagsOfNews(2);
        //news.insertNews(new News(7,"coronaVirus",date,1,"la pandémie qui a boulversé le monde" ));
        news.insertNewsByTags(2,tags);
    }
}


