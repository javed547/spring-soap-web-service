package com.javed.spring.soap.web.service.controller;

import com.javed.spring.soap.web.service.dto.ArticleInfo;
import com.javed.spring.soap.web.service.dto.GetAllArticlesRequest;
import com.javed.spring.soap.web.service.dto.GetArticleByIdRequest;
import com.javed.spring.soap.web.service.dto.GetArticleByIdResponse;
import com.javed.spring.soap.web.service.svc.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ArticleEndpoint {

    private static final String NAMESPACE_URI = "http://www.concretepage.com/article-ws";

    @Autowired
    private ArticleService articleService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getArticleByIdRequest")
    @ResponsePayload
    public GetArticleByIdResponse getArticle(@RequestPayload GetArticleByIdRequest getArticleByIdRequest) {
        GetArticleByIdResponse getArticleByIdResponse = new GetArticleByIdResponse();
        ArticleInfo articleInfo = new ArticleInfo();

        BeanUtils.copyProperties(articleService.getArticlesById(getArticleByIdRequest.getArticleId()), articleInfo);
        getArticleByIdResponse.setArticleInfo(articleInfo);

        return getArticleByIdResponse;
    }
}
