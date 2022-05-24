package com.epam.newsportal.resource;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.epam.newsportal.dto.Ids;
import com.epam.newsportal.entity.Article;
import com.epam.newsportal.service.ArticleService;

@Path("/articles")
public class ArticleResource {
	@Inject
	private ArticleService articleService;
	
	@Context
	UriInfo uriInfo;
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response getArticle(@PathParam("id") long id) {
		return Response.ok().entity(articleService.getById(id)).build();
	}
	
	@GET
	@Produces("application/json")
	public Response getArticles() {
		List<Article> articles = articleService.getAll();
		 return Response.ok().entity(articles).build();
	}
	
	@POST
	@Consumes("application/json")
	public Response createArticle(Article article) {
		long id = articleService.save(article);
		URI uri = uriInfo.getBaseUriBuilder()
				.path(ArticleResource.class)
				.path(ArticleResource.class, "getArticle")
				.build(id);
		return Response.created(uri).build();
	}
	
	@PUT
	@Consumes("application/json")
	public Response updateArticle(Article article) {
		articleService.update(article);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteArticle(@PathParam("id") long id) {
		articleService.deleteById(id);
		return Response.ok().build();
	}
	
	@POST
	@Path("ids")
	@Consumes("application/json")
	public Response deleteArticlesByIds(Ids ids) {
		List<String> idList = ids.getIds();
		articleService.deleteByIds(idList);
		return Response.ok().build();
	}
}
