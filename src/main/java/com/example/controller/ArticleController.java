package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.ArticleRepository2;
import com.example.repository.CommentRepository;

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
	
	@Autowired
	ArticleRepository2 repository2;
	
	@Autowired
	CommentRepository cmRepository;
	
	/**
	 * html紐づけを行う記事用フォーム
	 * @return
	 */
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		
		return new ArticleForm();
		
	}
	
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		
		return new CommentForm();
		
	}
	
	/**
	 * 全件検索を行う→記事を全て表示するよう
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		
		List<Article> articleList =  repository2.findAll();
		
		/*
		List<Article> articleList = repository.findAll();
		
		for(Article article : articleList) {
			article.setCommentList(cmRepository.findByArticleId(article.getId())) ;
		}
		*/
		model.addAttribute("articleList",articleList);
		
		return "bbs";
		
	}
	/**
	 * 新しい投稿を受け、DBに登録するメソッド
	 * @param form
	 * @return
	 */
	@RequestMapping("/insert")
	public String insertArticle(@Validated ArticleForm form,BindingResult result,Model model) {
		
		if(result.hasErrors()) {
			//return "redirect:/article/index";
			return index(model);
		}
		Article article = new Article();
		
		BeanUtils.copyProperties(form, article);
		
		repository.insert(article);
		
		return "redirect:/article/index";
		
	}
	
	
	/**
	 * 削除ボタンを受けて、DBからデータを削除するメソッド
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public String deleteArticle(Integer id,Model model) {
		
		repository.deleteById(id);
		cmRepository.deleteByArticleId(id);
		return "redirect:/article/index";
		
	}
	
	/**
	 * 新しいコメントを受け、DBに登録するメソッド
	 * @param form
	 * @return
	 */
	@RequestMapping("/insertComment")
	public String insertComment(@Validated CommentForm form,BindingResult result,Model model,Integer articleId) {
		
		if(result.hasErrors()) {
			//form.setArticleId(Integer.toString(articleId));
			
			model.addAttribute("checkId", articleId);
			//return "redirect:/article/index";
			return index(model);
		}
		Comment comment = new Comment();
		
		BeanUtils.copyProperties(form, comment);
		comment.setArticleId(articleId);
		
		cmRepository.insert(comment);
		
		return "redirect:/article/index";
		
	}
	
	/**
	 * 削除ボタンを受けて、DBからデータを削除するメソッド
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteComment")
	public String deleteComment(Integer articleId,Model model) {
		
		cmRepository.deleteByArticleId(articleId);
		
		return "redirect:/article/index";
		
	}
	

}
