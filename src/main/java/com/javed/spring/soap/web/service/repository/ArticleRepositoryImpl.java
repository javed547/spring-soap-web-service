package com.javed.spring.soap.web.service.repository;

import com.javed.spring.soap.web.service.dto.Articles;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    @Override
    public List<Articles> getAllArticles() {
        List<Articles> articlesList = new ArrayList<>();

        Articles articles = new Articles();
        articles.setArticleId(1l);
        articles.setTitle("Percy Jackson");
        articles.setCategory("Comics");

        Articles harryPotter = new Articles();
        harryPotter.setArticleId(2l);
        harryPotter.setTitle("Harry Potter");
        harryPotter.setCategory("Novel");

        articlesList.add(articles);
        articlesList.add(harryPotter);
        return articlesList;
    }

    @Override
    public Articles getArticlesById(long articleId) {
        Articles harryPotter = new Articles();

        harryPotter.setArticleId(2l);
        harryPotter.setTitle("Harry Potter");
        harryPotter.setCategory("Novel");

        return harryPotter;
    }

    @Override
    public Boolean addArticles(Articles articles) {
        return false;
    }

    @Override
    public void updateArticle(Articles articles) {

    }

    @Override
    public void deleteArticle(Articles articles) {

    }
}
