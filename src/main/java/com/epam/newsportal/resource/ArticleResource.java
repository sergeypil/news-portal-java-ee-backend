package com.epam.newsportal.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.epam.newsportal.entity.Article;
import com.epam.newsportal.service.ArticleServiceImpl;

@Path("/article")
public class ArticleResource {
	@Inject
	private ArticleServiceImpl articleService;
	
	
	@GET
	@Path("/get/")
	@Produces("text/plain")
	public Response getArticle() {
		System.out.print(articleService.getArticles(1));
		 return Response.ok().entity(articleService.getArticles(5).getTitle()).build();
	}
	
	@GET
	@Path("/create/")
	@Produces("text/plain")
	public Response createArticle() {
		Article article = new Article();
		article.setTitle("tiiitle");
		articleService.createArticle(article);
		 return Response.ok().entity("created").build();
	}
}
