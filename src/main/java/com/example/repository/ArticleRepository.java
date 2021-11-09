package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

/**
 * 記事用DBアクセスクラス
 * @author igayuki
 *
 */
@Repository
public class ArticleRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Article> ARTICLE_ROW_MAPPER
	=(rs,i) -> {
		Article article = new Article();
		
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		
		return article;
	};
	
	/**
	 * 全件検索
	 * @return　記事オブジェクト群
	 */
	public List<Article> findAll() {
		
		String sql ="SELECT * FROM articles;";
		
		return template.query(sql, ARTICLE_ROW_MAPPER);
		
	}
	
	/**
	 * 新しい記事投稿のメソッド
	 * @param article
	 */
	public void insert(Article article) {
		
		String sql ="INSERT INTO articles(name,content) VALUES(:name,:content);";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", article.getName()).addValue("content", article.getContent());
		template.update(sql, param);
		System.out.println("動作確認");
		
	}
	
	/**
	 * 指定IDの投稿を削除するメソッド
	 * @param id
	 */
	public void deleteById(int id) {
		
		String sql ="DELETE FROM articles WHERE id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}

}
