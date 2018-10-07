package com.namlee.examples.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.namlee.examples.spring_examples.controller.ArticleAPIController;
import com.namlee.examples.spring_examples.domain.Article;
import com.namlee.examples.spring_examples.service.ArticleService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ArticleAPIController.class, secure = false)
public class ArticleAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    @Before
    public void setUp() {

        List<Article> dbArticles = new ArrayList<>();
        dbArticles.add(new Article(1, "title_1", "content_1"));
        dbArticles.add(new Article(2, "title_2", "content_2"));

        ResponseEntity<List<Article>> rsResult = new ResponseEntity<>(dbArticles, HttpStatus.OK);
        when(this.articleService.findAllWs()).thenReturn(rsResult);

        Article savedArticle = new Article(1, "title_1", "content_1");
        when(this.articleService.save(any(Article.class))).thenReturn(savedArticle);
    }

    @Test
    public void testGetAllArticles() throws Exception {

        RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/api/v1/articles").accept(MediaType.APPLICATION_JSON);
        MvcResult result = this.mockMvc.perform(reqBuilder).andDo(print()).andReturn();

        String expected = "[{id:1,title:title_1,content:content_1},{id:2,title:title_2,content:content_2}]";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testSaveArticle() throws Exception {

        String articleJson = "{\"id\":1,\"title\":\"title_1\",\"content\":\"content_1\"}";
        RequestBuilder reqBuilder = MockMvcRequestBuilders.post("/api/v1/articles").accept(MediaType.APPLICATION_JSON)
                .content(articleJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc.perform(reqBuilder).andDo(print()).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(articleJson, response.getContentAsString());
    }
}
