package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.repository.ArticleRepository;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	ArticleRepository repository;
	
	@RequestMapping("")
	public String test() {
		/*
		System.out.println(repository.findAll());
		
		
		Article testArticle = new Article();
		testArticle.setName("test");
		testArticle.setContent("test");
		
		repository.insert(testArticle);
		*/
		repository.deleteById(3);
		
		return "test";
	}
	
	

}
