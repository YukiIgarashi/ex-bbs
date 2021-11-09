package com.example.form;

/**
 * コメント用フォームクラス
 * @author igayuki
 *
 */
public class CommentForm {
	
	private String name;
	private String content;
	private String artificleid;
	
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
	public String getArtificleid() {
		return artificleid;
	}
	public void setArtificleid(String artificleid) {
		this.artificleid = artificleid;
	}
	@Override
	public String toString() {
		return "CommentForm [name=" + name + ", content=" + content + ", artificleid=" + artificleid + "]";
	}
	
	

}
