package com.dao.test;

import com.Exception.MyExceptions;
import com.dao.NewsDao;
import com.model.News;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewsDaoTest {

    @Test
    void findNewsByIdPresentDansLaBase() {
        NewsDao newsDao = new NewsDao();
        News news = new News();

        try{
            news = new NewsDao().findNewsById(2);
        }catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(2,news.getId_news());

    }

    @Test
    void findNewsByIdNonPresentDansLaBase() {
        NewsDao newsDao = new NewsDao();
        News news = new News();

        try {
            news = new NewsDao().findNewsById(10);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertNotEquals(2, news.getId_news());
    }


    @Test
    void findNewsByReportersName() {
        NewsDao newsDao = new NewsDao();
        News news = new News();

        try {
            news = new NewsDao().findNewsByReportersName("Hanane");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(3, news.getId_news());
    }


    @Test
    void selectAllNews() {
    }

    @Test
    void findTagsOfNews() {
    }

    @Test
    void insertNews() {
    }

    @Test
    void insertNewsByTags() {
    }
}