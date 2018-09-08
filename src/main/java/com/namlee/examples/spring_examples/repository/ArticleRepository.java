package com.namlee.examples.spring_examples.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.namlee.examples.spring_examples.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	List<Article> findByTitleContaining(String title);
}
