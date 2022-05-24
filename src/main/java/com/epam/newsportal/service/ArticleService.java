package com.epam.newsportal.service;

import java.util.List;

import com.epam.newsportal.entity.Article;

public interface ArticleService {
    List<Article> getAll();
    Article getById(long id);
    long save(Article article);
    void update(Article article);
    void deleteById(long id);
    void deleteByIds(List<String> ids);
}
