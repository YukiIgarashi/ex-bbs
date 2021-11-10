package com.example.repository;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * コメント用DBアクセスクラス
 * @author igayuki
 *
 */
@Repository
public class CommentRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Comment> COMMENT_ROW_MAPPER
	=(rs,i) -> {
		Comment comment = new Comment();
		
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleId(rs.getInt("article_id"));
		
		return comment;
	};
	
	/**
	 * 特定の記事につけられているコメント群の検索
	 * @return　特定の記事につけられているコメント群
	 */
	public List<Comment> findByArticleId(int articleId) {
		
		String sql ="SELECT * FROM comments WHERE article_id=:articleId ORDER BY id DESC;";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId",articleId);
		
		return template.query(sql,param,COMMENT_ROW_MAPPER);
		
	}
	
	/**
	 * 新しいコメント投稿のメソッド
	 * @param article
	 */
	public void insert(Comment comment) {
		
		String sql ="INSERT INTO comments(name,content,article_id) VALUES(:name,:content,:articleId);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		template.update(sql, param);
		System.out.println("動作確認");
		
	}
	
	/**
	 * 指定IDのコメント群を削除するメソッド
	 * @param id
	 */
	public void deleteByArticleId(int articleId) {
		
		String sql ="DELETE FROM comments WHERE article_id=:articleId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		template.update(sql, param);
		
		System.out.println("動作確認");
	}

}
