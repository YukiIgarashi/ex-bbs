package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.repository.ArticleRepository;
import com.example.repository.ArticleRepository2;
import com.example.repository.CommentRepository;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	ArticleRepository repository;
	
	@Autowired
	 CommentRepository cmRepository;
	
	@Autowired
	ArticleRepository2 repository2;
	
	@RequestMapping("")
	public String test() {
		/*
		System.out.println(repository.findAll());
		
		
		Article testArticle = new Article();
		testArticle.setName("test");
		testArticle.setContent("test");
		
		repository.insert(testArticle);
		
		repository.deleteById(3);
		*/
		System.out.println(repository2.findAll());
		return "test";
	}
	
	@RequestMapping("/cmTest")
	public String testCM() {
		/*
		System.out.println(cmRepository.findByArticleId(1));
		
		Comment comment = new Comment();
		comment.setName("test");
		comment.setContent("test");
		comment.setArticleId(1);
		
		cmRepository.insert(comment);
		
		cmRepository.deleteByArticleId(1);
		*/
		
		
		
		
		return "test";
		
	}

}
