package com.namlee.examples.spring_examples.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.namlee.examples.spring_examples.domain.Article;
import com.namlee.examples.spring_examples.repository.ArticleRepository;

@RunWith(SpringRunner.class)
public class ArticleServiceTest {

    @TestConfiguration
    static class ArticleServiceTestContextConfiguration {

        @Bean
        public ArticleService articleService() {

            return new ArticleServiceImpl();
        }
    }

    @Autowired
    private ArticleService articleService;

    @MockBean
    private ArticleRepository articleRepository;

    @Before
    public void setUp() {

        Article article1 = new Article(1, "title_1", "content_1");
        Article article2 = new Article(2, "title_2", "content_2");
        Article article3 = new Article(3, "title_3", "content_3");
        List<Article> articles = new ArrayList<>();
        articles.add(article1);
        articles.add(article2);
        articles.add(article3);

        when(this.articleRepository.findByTitleContaining(anyString())).thenReturn(articles);
    }

    @Test
    public void givenTitle_whenSearchWithTitle_thenReturnListOfArticle() {

        // given
        String titleSearch = "title";

        // when
        List<Article> articles = this.articleService.search(titleSearch);

        // then
        assertEquals(articles.size(), 3);
    }
}
