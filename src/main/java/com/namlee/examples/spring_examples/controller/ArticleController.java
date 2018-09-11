package com.namlee.examples.spring_examples.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.namlee.examples.spring_examples.domain.Article;
import com.namlee.examples.spring_examples.service.ArticleService;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@GetMapping("/articles")
	public String getAllArticle(Model model) {
		List<Article> articles = this.articleService.findAll();
		model.addAttribute("articles", articles);

		return "/web/list_articles";
	}

	// @ResponseBody: return as JSON object
	@GetMapping("/articles/json")
	//@ResponseBody
	public List<Article> getAllArticles() {
		return this.articleService.findAll();
	}

	@GetMapping("/articles/search")
	public String search(@RequestParam("q") String title, Model model) {
		if (title.equals("")) {
			return "redirect:/articles";
		}

		List<Article> searchResult = this.articleService.search(title);
		model.addAttribute("articles", searchResult);

		return "/web/list_articles";
	}

	@GetMapping("/articles/create")
	public String createArticle(Model model) {
		model.addAttribute("article", new Article());

		return "/web/article_form";
	}

	@GetMapping("/articles/{id}/edit")
	public String editArticle(@PathVariable long id, Model model) {
		Article editArticle = this.articleService.findOneWs(id).getBody();
		model.addAttribute("article", editArticle);

		return "/web/article_form";
	}

	@PostMapping("/articles/save")
	public String saveArticle(@Valid Article article, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "/web/article_form";
		}

		this.articleService.save(article);
		redirect.addFlashAttribute("success", "Save article successfully !!!");

		return "redirect:/articles";
	}

	@GetMapping("/articles/{id}/delete")
	public String deleteArticle(@PathVariable long id, RedirectAttributes redirect) {
		this.articleService.delete(id);
		redirect.addFlashAttribute("success", "Delete article successfully !!!");

		return "redirect:/articles";
	}

}
