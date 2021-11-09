package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;

/**
 * 記事・コメント用コントローラー
 * @author igayuki
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	ArticleRepository repository;
	
	/**
	 * html紐づけを行う記事用フォーム
	 * @return
	 */
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		
		return new ArticleForm();
		
	}
	
	/**
	 * 全件検索を行う→記事を全て表示するよう
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		
		List<Article> articleList = repository.findAll();
		model.addAttribute("articleList",articleList);
		
		return "bbs";
		
	}
	
	/**
	 * 新しい投稿を受け、DBに登録するメソッド
	 * @param form
	 * @return
	 */
	@RequestMapping("/insert")
	public String insertArticle(ArticleForm form) {
		
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		
		repository.insert(article);
		
		return "bbs";
		
	}
	
	/**
	 * 削除ボタンを受けて、DBからデータを削除するメソッド
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public String deleteArticle(Integer id) {
		
		repository.deleteById(id);
		
		return "bbs";
		
	}

}
