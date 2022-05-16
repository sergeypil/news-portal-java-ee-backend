package com.epam.newsportal.service;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.epam.newsportal.dao.ArticleDaoImpl;
import com.epam.newsportal.entity.Article;

@Dependent
public class ArticleServiceImpl {
	@Inject
	ArticleDaoImpl articleDao;
	
	public Article getArticles(long id) {
		return articleDao.findById(id);
	}
	
	public void createArticle(Article article) {
		articleDao.createArticle(article);
	}
}
