package com.javed.spring.soap.web.service.svc;

import com.javed.spring.soap.web.service.dto.Articles;

import java.util.List;

// TODO add mongo db implementation here as mysql is not working on local machine
public interface ArticleService {

    List<Articles> getAllArticles();

    Articles getArticlesById(long articleId);

    Boolean addArticles (Articles articles);

    void updateArticle (Articles articles);

    void deleteArticle (Articles articles);

}
