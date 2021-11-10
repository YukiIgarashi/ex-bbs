package com.example.form;

/**
 * コメント用フォームクラス
 * @author igayuki
 *
 */
public class CommentForm {
	
	private String name;
	private String content;
	private String artificleId;
	
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
	public String getArtificleId() {
		return artificleId;
	}
	public void setArtificleId(String artificleId) {
		this.artificleId = artificleId;
	}
	@Override
	public String toString() {
		return "CommentForm [name=" + name + ", content=" + content + ", artificleId=" + artificleId + "]";
	}
	
	

}
