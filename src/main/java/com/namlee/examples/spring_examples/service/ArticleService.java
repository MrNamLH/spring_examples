package com.namlee.examples.spring_examples.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.namlee.examples.spring_examples.domain.Article;
import com.namlee.examples.spring_examples.exceptionhandling.EntityNotFoundException;

public interface ArticleService {

    List<Article> findAll();

    ResponseEntity<List<Article>> findAllWs();

    List<Article> search(String title);

    ResponseEntity<List<Article>> searchWs(String title);

    Article findOne(long id);

    Article findOneWithException(long id) throws EntityNotFoundException;

    ResponseEntity<Article> findOneWs(long id);

    Article save(Article article);

    ResponseEntity<Article> updateArticleByIdWs(long id, Article newArticle);

    void delete(long id);

    ResponseEntity<Void> deleteWs(long id);

}
