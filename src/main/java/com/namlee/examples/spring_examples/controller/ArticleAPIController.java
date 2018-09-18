package com.namlee.examples.spring_examples.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.namlee.examples.spring_examples.domain.Article;
import com.namlee.examples.spring_examples.exceptionhandling.EntityNotFoundException;
import com.namlee.examples.spring_examples.service.ArticleService;

@RestController
@RequestMapping("/api/v1")
public class ArticleAPIController {

	@Autowired
	private ArticleService articleService;

	@GetMapping("/articles")
	public ResponseEntity<List<Article>> getAllArticles() {
		return this.articleService.findAllWs();
	}

	@GetMapping("/articles/search")
	public ResponseEntity<List<Article>> searchByTitle(@RequestParam("title") String title) {
		return this.articleService.searchWs(title);
	}

	@GetMapping("/articles/{id}")
	public ResponseEntity<Article> getArticle(@PathVariable("id") long id) {
		return this.articleService.findOneWs(id);
	}

	@GetMapping("/articles/exception/{id}")
	public Article getArticleWithException(@PathVariable("id") long id) throws EntityNotFoundException {
		return this.articleService.findOneWithException(id);
	}

	@PostMapping("/articles")
	public Article saveArticle(@Valid @RequestBody Article article) {
		return this.articleService.save(article);
	}

	@PutMapping("/articles/{id}")
	public ResponseEntity<Article> updateArticleById(@PathVariable(value = "id") long id,
			@Valid @RequestBody Article newArticle) {
		return this.articleService.updateArticleByIdWs(id, newArticle);
	}

	@DeleteMapping("/articles/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable("id") long id) {
		return this.articleService.deleteWs(id);
	}

}
