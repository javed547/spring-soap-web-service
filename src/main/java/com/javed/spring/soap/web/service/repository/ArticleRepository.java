package com.javed.spring.soap.web.service.repository;

import com.javed.spring.soap.web.service.dto.Articles;

import java.util.List;

public interface ArticleRepository {

    List<Articles> getAllArticles();

    Articles getArticlesById(long articleId);

    Boolean addArticles (Articles articles);

    void updateArticle (Articles articles);

    void deleteArticle (Articles articles);

}
