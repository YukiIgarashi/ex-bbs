package com.example.domain;

public class Comment {
	
	private Integer id;
	private String name;
	private String content;
	private Integer artificleid;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Integer getArtificleid() {
		return artificleid;
	}
	public void setArtificleid(Integer artificleid) {
		this.artificleid = artificleid;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", content=" + content + ", artificleid=" + artificleid + "]";
	}
	
	

}
