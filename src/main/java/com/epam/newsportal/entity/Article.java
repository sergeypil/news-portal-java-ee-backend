package com.epam.newsportal.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.Objects;

@Entity
@Table
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
    @NotNull(message = "Title cannot be null")
    @Size(min = 1,max = 100,message = "Title size must be between 1 and 100")
    private String title;
    
    @NotNull(message = "Brief cannot be null")
    @Size(min = 1,max = 500,message = "Brief size must be between 1 and 500")
    private String brief;
    
    @NotNull(message = "Content cannot be null")
    @Size(min = 1,max = 2048,message = "Content size must be between 1 and 2048")
    private String content;
    
    private Date created;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", content='" + content + '\'' +
                ", created=" + created +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id == article.id && Objects.equals(title, article.title) && Objects.equals(brief, article.brief) && Objects.equals(content, article.content) && Objects.equals(created, article.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, brief, content, created);
    }
}
