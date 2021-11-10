package com.example.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
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
public class ArticleRepository2 {
	
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
	
	private static final ResultSetExtractor<List<Article>> ARTICLE_EXTRACTOR
	=(rs) -> {
		List<Article> articleList = new ArrayList();
		Map<Integer,Article> map = new LinkedHashMap();
		Article article = null;
		while(rs.next()) {
			Integer articleId = rs.getInt("id");
			article = map.get(articleId);
			if(article == null){
				article = new Article();
				article.setId(rs.getInt("id"));
				article.setName(rs.getString("name"));
				article.setContent(rs.getString("content"));
				map.put(articleId, article);
				article.setCommentList(new ArrayList());
			}
			
			Integer commentId = rs.getInt("com_id");
			if(commentId != null) {
				Comment comment = new Comment();
				comment.setId(commentId);
				comment.setName(rs.getString("com_name"));
				comment.setContent(rs.getString("com_content"));
				comment.setArticleId(articleId);
				article.getCommentList().add(comment);
			}
			
		}
		
		if(map.size() == 0) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return new ArrayList<Article>(map.values());
		
	};
	
	
	
	/**
	 * 全件検索(Article,Comment)
	 * @return　記事オブジェクト群
	 */
	public List<Article> findAll() {
		
		String sql ="SELECT AR.id,AR.name,AR.content,CO.id AS com_id,"
				+ "CO.name AS com_name,CO.content AS com_content,CO.article_id"
				+ " FROM articles AS AR FULL OUTER JOIN comments AS CO"
				+ " ON AR.id = article_id ORDER BY AR.id DESC,CO.id DESC;";
		
		return template.query(sql, ARTICLE_EXTRACTOR);
		
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
		
		System.out.println("動作確認");
	}
	
	public void deleteAll() {
		
		String sql = "DELETE FROM (SELECT AR.id,AR.name,AR.content,CO.id AS com_id,"
				+ "CO.name AS com_name,CO.content AS com_content,CO.article_id"
				+ " FROM articles AS AR FULL OUTER JOIN comments AS CO"
				+ " ON AR.id = article_id ORDER BY AR.id DESC,CO.id DESC;)";
		
	}

}
