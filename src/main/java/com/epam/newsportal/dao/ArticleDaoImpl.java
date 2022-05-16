package com.epam.newsportal.dao;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.epam.newsportal.entity.Article;

@Dependent
public class ArticleDaoImpl {
	@PersistenceContext
    private EntityManager manager;

    public Article findById(long id) {
        Article article = manager.find(Article.class, id);
        return article;
    }
    
    public void createArticle(Article article) {
        manager.persist(article);
        manager.flush();
    }
}
