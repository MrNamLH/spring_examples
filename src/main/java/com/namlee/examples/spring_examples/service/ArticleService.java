package com.namlee.examples.spring_examples.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.namlee.examples.spring_examples.domain.Article;

public interface ArticleService {
	List<Article> findAll();

	List<Article> search(String q);

	ResponseEntity<Article> findOne(long id);

	ResponseEntity<Article> updateArticleById(long id, Article newArticle);

	Article save(Article article);

	ResponseEntity<Void> delete(long id);
}
