package com.javed.spring.soap.web.service.svc;

import com.javed.spring.soap.web.service.dto.Articles;

import java.util.List;

public interface ArticleService {

    List<Articles> getAllArticles();

    Articles getArticlesById(String articleId);

    Boolean addArticles (Articles articles);

    void updateArticle (Articles articles);

    void deleteArticle (Articles articles);

}
