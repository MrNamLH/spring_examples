package com.namlee.examples.spring_examples.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.namlee.examples.spring_examples.domain.Article;
import com.namlee.examples.spring_examples.repository.ArticleRepository;

@Service
@Transactional(readOnly = true)
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public List<Article> findAll() {
		return articleRepository.findAll();
	}

	@Override
	public List<Article> search(String title) {
		return articleRepository.findByTitleContaining(title);
	}

	@Override
	public ResponseEntity<Article> findOne(long id) {
		return articleRepository.findById(id).map(article -> ResponseEntity.ok(article))
				.orElse(ResponseEntity.notFound().build());
	}

	@Override
	@Transactional
	public ResponseEntity<Article> updateArticleById(long id, Article newArticle) {
		return articleRepository.findById(id).map(existingArticle -> {
			existingArticle.setTitle(newArticle.getTitle());
			existingArticle.setContent(newArticle.getContent());

			return ResponseEntity.ok().body(articleRepository.save(existingArticle));
		}).orElse(ResponseEntity.notFound().build());
	}

	@Override
	@Transactional
	public Article save(Article article) {
		return articleRepository.save(article);
	}

	@Override
	@Transactional
	public ResponseEntity<Void> delete(long id) {
		return articleRepository.findById(id).map(existingArtical -> {
			articleRepository.delete(existingArtical);

			return new ResponseEntity<Void>(HttpStatus.OK);
		}).orElse(ResponseEntity.notFound().build());
	}

}
