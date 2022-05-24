package com.epam.newsportal.dao;

import com.epam.newsportal.entity.Article;

import java.util.List;

public interface ArticleDao {
    List<Article> getAll();
    Article getById(long id);
    long save(Article article);
    void update(Article article);
    void deleteById(long id);
    void deleteByIds(List<String> ids);
}