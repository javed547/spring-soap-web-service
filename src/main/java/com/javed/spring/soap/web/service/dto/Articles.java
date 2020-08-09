package com.javed.spring.soap.web.service.dto;

import java.io.Serializable;

public class Articles implements Serializable {

    private static final long serialVersionUID = 1L;

    private long articleId;

    private String title;

    private String category;

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
