package com.namlee.examples.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.namlee.examples.spring_examples.config.WebMvcConfig;
import com.namlee.examples.spring_examples.config.WebSecurityConfig;
import com.namlee.examples.spring_examples.controller.ArticleAPIController;
import com.namlee.examples.spring_examples.service.ArticleService;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = { ArticleAPIController.class, WebMvcConfig.class, WebSecurityConfig.class
})
//@SpringBootTest(classes = ArticleController.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class ArticleAPIControllerTest_V2 {

    @Autowired
    WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    @Before
    public void setUp() {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void testGetAllArticles() throws Exception {

        this.mockMvc.perform(get("/api/v1/articles").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }

}
