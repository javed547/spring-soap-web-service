package com.javed.spring.soap.web.service.controller;

import com.javed.spring.soap.web.service.dto.*;
import com.javed.spring.soap.web.service.svc.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class ArticleEndpoint {

    private Logger logger = LoggerFactory.getLogger(ArticleEndpoint.class);

    private static final String NAMESPACE_URI = "http://www.concretepage.com/article-ws";

    @Autowired
    private ArticleService articleService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getArticleByIdRequest")
    @ResponsePayload
    public GetArticleByIdResponse getArticle(@RequestPayload GetArticleByIdRequest getArticleByIdRequest) {
        logger.debug("IN ArticleEndpoint:getArticle");
        GetArticleByIdResponse getArticleByIdResponse = new GetArticleByIdResponse();
        ArticleInfo articleInfo = new ArticleInfo();

        BeanUtils.copyProperties(articleService.getArticlesById(getArticleByIdRequest.getArticleId()), articleInfo);
        logger.info("adding article to response");
        getArticleByIdResponse.setArticleInfo(articleInfo);

        logger.debug("OUT ArticleEndpoint:getArticle");
        return getArticleByIdResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllArticlesRequest")
    @ResponsePayload
    public GetAllArticlesResponse getAllArticles() {
        logger.debug("IN ArticleEndpoint:getAllArticles");
        GetAllArticlesResponse getAllArticlesResponse = new GetAllArticlesResponse();
        List<ArticleInfo> articleInfoList = new ArrayList<>();

        List<Articles> articlesList = articleService.getAllArticles();
        for (int i = 0; i < articlesList.size(); i++) {
            ArticleInfo ob = new ArticleInfo();
            BeanUtils.copyProperties(articlesList.get(i), ob);
            articleInfoList.add(ob);
        }

        logger.info("adding article list to response");
        getAllArticlesResponse.getArticleInfo().addAll(articleInfoList);

        logger.debug("OUT ArticleEndpoint:getAllArticles");
        return getAllArticlesResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addArticleRequest")
    @ResponsePayload
    public AddArticleResponse addArticle(@RequestPayload AddArticleRequest addArticleRequest) {
        logger.debug("IN ArticleEndpoint:addArticle");
        AddArticleResponse addArticleResponse = new AddArticleResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        Articles articles = new Articles();
        articles.setTitle(addArticleRequest.getTitle());
        articles.setCategory(addArticleRequest.getCategory());

        Boolean flag = articleService.addArticles(articles);
        if (flag) {
            logger.info("article already exist, sending appropriate response");
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Content Already Available");

            addArticleResponse.setServiceStatus(serviceStatus);
        } else {
            logger.info("article added successfully");
            ArticleInfo articleInfo = new ArticleInfo();
            BeanUtils.copyProperties(articles, articleInfo);
            addArticleResponse.setArticleInfo(articleInfo);

            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");

            addArticleResponse.setServiceStatus(serviceStatus);
        }

        logger.debug("OUT ArticleEndpoint:addArticle");
        return addArticleResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateArticleRequest")
    @ResponsePayload
    public UpdateArticleResponse updateArticle(@RequestPayload UpdateArticleRequest updateArticleRequest) {
        logger.debug("IN ArticleEndpoint:updateArticle");
        UpdateArticleResponse updateArticleResponse = new UpdateArticleResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        Articles articles = new Articles();
        articles.setTitle(updateArticleRequest.getArticleInfo().getTitle());
        articles.setCategory(updateArticleRequest.getArticleInfo().getCategory());

        if (updateArticleRequest.getArticleInfo().getTitle() != null
                && updateArticleRequest.getArticleInfo().getCategory() != null) {
            articleService.updateArticle(articles);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Updated Successfully");
            logger.info("successfully updated article");
        } else {
            logger.info("failed to update, content not available");
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Content Not Available");
        }

        logger.debug("OUT ArticleEndpoint:updateArticle");
        updateArticleResponse.setServiceStatus(serviceStatus);
        return updateArticleResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteArticleRequest")
    @ResponsePayload
    public DeleteArticleResponse deleteArticle(@RequestPayload DeleteArticleRequest deleteArticleRequest) {
        logger.debug("IN ArticleEndpoint:deleteArticle");
        Articles articles = articleService.getArticlesById(deleteArticleRequest.getArticleId());
        ServiceStatus serviceStatus = new ServiceStatus();

        if (articles == null ) {
            logger.info("failed to delete the article");
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Content Not Available");
        } else {
            articleService.deleteArticle(articles);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Deleted Successfully");
            logger.info("successfully deleted the article");
        }

        DeleteArticleResponse deleteArticleResponse = new DeleteArticleResponse();
        deleteArticleResponse.setServiceStatus(serviceStatus);

        logger.debug("OUT ArticleEndpoint:deleteArticle");
        return deleteArticleResponse;
    }

}
