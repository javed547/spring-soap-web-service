package com.javed.spring.soap.web.service.repository;

import com.javed.spring.soap.web.service.dto.Articles;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// here we have removed the implements from class implementation
//@Repository
public class ArticleRepositoryImpl {

    //@Override
    public List<Articles> getAllArticles() {
        List<Articles> articlesList = new ArrayList<>();

        Articles articles = new Articles();
        articles.setArticleId("1");
        articles.setTitle("Percy Jackson");
        articles.setCategory("Comics");

        Articles harryPotter = new Articles();
        harryPotter.setArticleId("2");
        harryPotter.setTitle("Harry Potter");
        harryPotter.setCategory("Novel");

        articlesList.add(articles);
        articlesList.add(harryPotter);
        return articlesList;
    }

    //@Override
    public Articles getArticlesById(long articleId) {
        Articles harryPotter = new Articles();

        harryPotter.setArticleId("2");
        harryPotter.setTitle("Harry Potter");
        harryPotter.setCategory("Novel");

        return harryPotter;
    }

    //@Override
    public Boolean addArticles(Articles articles) {
        return false;
    }

    //@Override
    public void updateArticle(Articles articles) {

    }

    //@Override
    public void deleteArticle(Articles articles) {

    }
}
