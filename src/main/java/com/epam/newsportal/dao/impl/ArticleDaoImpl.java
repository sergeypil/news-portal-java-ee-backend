package com.epam.newsportal.dao.impl;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.NamedNativeQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.epam.newsportal.dao.ArticleDao;
import com.epam.newsportal.entity.Article;

@Dependent
public class ArticleDaoImpl implements ArticleDao{
	@PersistenceContext
    private EntityManager manager;

	/*
	 * public Article findById(long id) { Article article =
	 * manager.find(Article.class, id); return article; }
	 */
    
	/*
	 * public void createArticle(Article article) { manager.persist(article);
	 * manager.flush(); }
	 */

	@Override
	public List<Article> getAll() {
        TypedQuery<Article> query = manager.createQuery("from Article", Article.class);
        return query.getResultList();
		/*
		 * CriteriaBuilder cb = manager.getCriteriaBuilder(); CriteriaQuery<Article> cq
		 * = cb.createQuery(Article.class); Root<Article> rootEntry =
		 * cq.from(Article.class); CriteriaQuery<Article> all = cq.select(rootEntry);
		 * TypedQuery<Article> allQuery = manager.createQuery(all); return
		 * allQuery.getResultList();
		 */
	}

	@Override
	public Article getById(long id) {
        Article article = manager.find(Article.class, id);
        return article;
	}

	@Override
	public long save(Article article) {
		manager.persist(article);
		manager.flush();
		return article.getId();
	}

	@Override
	public void update(Article article) {
		manager.merge(article);
		manager.flush();
	}

	@Override
	public void deleteById(long id) {
		Article article = manager.find(Article.class, id);
		manager.remove(article);
	}

	@Override
	public void deleteByIds(List<String> ids) {
		String ids_str = String.join(",", ids);
		Query query = manager.createNativeQuery("DELETE FROM ARTICLE WHERE id IN(" + ids_str + ")", Article.class);
        query.executeUpdate();
	}
}
