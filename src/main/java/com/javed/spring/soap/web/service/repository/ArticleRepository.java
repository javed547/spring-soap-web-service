package com.javed.spring.soap.web.service.repository;

import com.javed.spring.soap.web.service.dto.Articles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends MongoRepository<Articles, String> {

    List<Articles> getAllArticles();

    /*Articles getArticlesById(long articleId);

    Boolean addArticles (Articles articles);

    void updateArticle (Articles articles);

    void deleteArticle (Articles articles);*/

}
