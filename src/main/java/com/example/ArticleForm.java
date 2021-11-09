package com.example;

import java.util.List;

import com.example.domain.Comment;

/**
 * 記事用フォームクラス
 * @author igayuki
 *
 */
public class ArticleForm {

	private String id;
	private String name;
	private String content;
	private List<Comment> commentList;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	@Override
	public String toString() {
		return "ArticleForm [id=" + id + ", name=" + name + ", content=" + content + ", commentList=" + commentList
				+ "]";
	}
	
	

}
