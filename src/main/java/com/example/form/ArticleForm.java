package com.example.form;

import java.util.List;

import com.example.domain.Comment;

/**
 * 記事用フォームクラス
 * @author igayuki
 *
 */
public class ArticleForm {

	private String name;
	private String content;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "ArticleForm [name=" + name + ", content=" + content + "]";
	}
	
}
