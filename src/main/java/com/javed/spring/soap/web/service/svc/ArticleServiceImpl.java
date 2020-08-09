package com.javed.spring.soap.web.service.svc;

import com.javed.spring.soap.web.service.dto.Articles;
import com.javed.spring.soap.web.service.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Articles> getAllArticles() {
        return articleRepository.getAllArticles();
    }

    @Override
    public Articles getArticlesById(long articleId) {
        return articleRepository.getArticlesById(articleId);
    }

    @Override
    public Boolean addArticles(Articles articles) {
        return articleRepository.addArticles(articles);
    }

    @Override
    public void updateArticle(Articles articles) {
        articleRepository.updateArticle(articles);
    }

    @Override
    public void deleteArticle(Articles articles) {
        articleRepository.deleteArticle(articles);
    }
}
