package com.namlee.examples.spring_examples.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.namlee.examples.spring_examples.domain.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {

	@Override
	List<Article> findAll();

	List<Article> findByTitleContaining(String title);
}
