package com.namlee.examples.spring_examples.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.namlee.examples.spring_examples.domain.Article;
import com.namlee.examples.spring_examples.exceptionhandling.EntityNotFoundException;
import com.namlee.examples.spring_examples.repository.ArticleRepository;

@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceImplTest {

	@InjectMocks
	private ArticleServiceImpl articleServiceImpl;

	@Mock
	private ArticleRepository articleRepository;

	private List<Article> dbData = new ArrayList<>();

	private List<Article> createArticles(int lenght) {
		List<Article> articles = new ArrayList<>();

		for (int i = 0; i < lenght; i++) {
			articles.add(new Article(Long.parseLong(String.valueOf(i)), "Title_" + i, "Content_" + i));
		}

		return articles;
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		this.dbData = this.createArticles(3);

		Answer<List<Article>> dnAnswer = new Answer<List<Article>>() {

			@Override
			public List<Article> answer(InvocationOnMock invocation) throws Throwable {
				List<Article> articles = new ArrayList<>();

				for (int i = 0; i < dbData.size(); i++) {
					Article article = new Article(Long.parseLong(String.valueOf(i)), "Title_" + i, "Content_" + i);
					articles.add(article);
				}
				return articles;
			}
		};

		when(this.articleRepository.findAll()).thenAnswer(dnAnswer);
		when(this.articleRepository.findByTitleContaining(anyString())).thenAnswer(dnAnswer);
	}

	/**
	 * findAll() test case
	 */
	@Test
	public void testFindAll_returnNotNullOrEmptyList() {

		assertThat(this.articleServiceImpl.findAll(), is(notNullValue()));
		verify(this.articleRepository, times(1)).findAll();
	}

	@Test
	public void testFindAll_returnEmptyList() {

		when(this.articleRepository.findAll()).thenReturn(new ArrayList<Article>());
		List<Article> articles = this.articleServiceImpl.findAll();

		assertTrue(articles.size() == 0);
	}

	@Test(expected = RuntimeException.class)
	public void testFindAll_throwsException() {

		when(this.articleRepository.findAll()).thenThrow(RuntimeException.class);
		this.articleServiceImpl.findAll();
	}

	@Test
	public void testFindAll_returnNewArrayListSize3() {

		List<Article> articles = this.articleServiceImpl.findAll();
		assertTrue(articles.size() == 3);
	}

	/**
	 * search() test case
	 */
	@Test
	public void testSearch_returnEmptyList() {

		when(this.articleRepository.findByTitleContaining(anyString())).thenReturn(new ArrayList<Article>());
		assertTrue(this.articleServiceImpl.search("test search tittle").size() == 0);
	}

	@Test
	public void testSearch_returnListSize3() {

		assertTrue(this.articleServiceImpl.search("test title").size() == 3);
	}

	@Test(expected = RuntimeException.class)
	public void testSearch_throwsException() {

		when(this.articleRepository.findByTitleContaining(anyString())).thenThrow(RuntimeException.class);
		this.articleServiceImpl.search("test title");
	}

	/**
	 * save() test case
	 */
	@Test
	public void testSave_returnArticleWithId() {

//		when(this.articleRepository.save(any())).then(invocationOnMock -> invocationOnMock.getArgument(0));
		when(this.articleRepository.save(any(Article.class))).thenAnswer(new Answer<Article>() {

			@Override
			public Article answer(InvocationOnMock invocation) throws Throwable {
				Object[] arg = invocation.getArguments();

				if (arg != null && arg.length > 0 && arg[0] != null) {
					Article article = (Article) arg[0];
					article.setId(1);

					return article;
				}

				return null;
			}
		});

		Article article = new Article();
		this.articleServiceImpl.save(article);

		assertEquals(article.getId(), 1);
	}

	@Test(expected = RuntimeException.class)
	public void testSave_throwsException() {

		when(this.articleRepository.save(any(Article.class))).thenThrow(RuntimeException.class);
		this.articleServiceImpl.save(new Article());
	}

	/**
	 * delete() test case
	 */
	@Test(expected = RuntimeException.class)
	public void testDelete_throwsException() {
//		doAnswer(new Answer<Void>() {
//
//			@Override
//			public Void answer(InvocationOnMock invocation) throws Throwable {
//				return null;
//			}
//
//		}).when(this.articleRepository).deleteById(anyLong());

		doThrow(RuntimeException.class).when(this.articleRepository).deleteById(anyLong());

		this.articleServiceImpl.delete(3);
	}

	/**
	 * findOneWithException() test case
	 */
	@Test(expected = EntityNotFoundException.class)
	public void testFindOneWithException_returnException() throws EntityNotFoundException {

		this.articleServiceImpl.findOneWithException(3);
	}

}
