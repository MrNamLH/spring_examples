package com.namlee.examples.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.namlee.examples.spring_examples.domain.Article;
import com.namlee.examples.spring_examples.repository.ArticleRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ArticleRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void whenFindAll_thenReturnAllArticle() {

        // given
        Article article1 = new Article(1, "title_1", "content_1");
        Article article2 = new Article(2, "title_2", "content_2");
        Article article3 = new Article(3, "title_3", "content_3");

        this.entityManager.merge(article1);
        this.entityManager.merge(article2);
        this.entityManager.merge(article3);
        this.entityManager.flush();

        // when
        List<Article> articles = this.articleRepository.findAll();

        // then
        assertEquals(articles.size(), 3);
    }

}
