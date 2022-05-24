package com.epam.newsportal.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.epam.newsportal.dao.impl.ArticleDaoImpl;
import com.epam.newsportal.entity.Article;
import com.epam.newsportal.service.ArticleService;

@Dependent
public class ArticleServiceImpl implements ArticleService {
	@Inject
	ArticleDaoImpl articleDao;

	@Override
	public List<Article> getAll() {
		return articleDao.getAll();
	}

	@Override
	public Article getById(long id) {
		return articleDao.getById(id);
	}

	@Transactional
	@Override
	public long save(Article article) {
		article.setCreated(Timestamp.valueOf(LocalDateTime.now()));
		long id = articleDao.save(article);
		return id;
	}

	@Transactional
	@Override
	public void update(Article article) {
		articleDao.update(article);
		
	}

	@Transactional
	@Override
	public void deleteById(long id) {
		articleDao.deleteById(id);
		
	}

	@Transactional
	@Override
	public void deleteByIds(List<String> ids) {
		articleDao.deleteByIds(ids);
		
	}
}
