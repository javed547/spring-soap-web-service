package com.javed.spring.soap.web.service.svc;

import com.javed.spring.soap.web.service.dto.Articles;
import com.javed.spring.soap.web.service.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Articles> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Articles getArticlesById(String articleId) {
        return articleRepository.findById(articleId).get();
    }

    @Override
    public Boolean addArticles(Articles articles) {
        articleRepository.save(articles);
        return true;
    }

    @Override
    public void updateArticle(Articles articles) {
        Query query = new Query();
        query.addCriteria(Criteria.where("articleId").is(articles.getArticleId()));

        Articles searchedArticle = mongoTemplate.findOne(query, Articles.class);

        //modify and update with save()
        searchedArticle.setTitle(articles.getTitle());
        searchedArticle.setCategory(articles.getCategory());
        mongoTemplate.save(searchedArticle);
    }

    @Override
    public void deleteArticle(Articles articles) {
        articleRepository.delete(articles);
    }
}
