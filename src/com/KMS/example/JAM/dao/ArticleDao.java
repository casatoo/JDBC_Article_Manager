package com.KMS.example.JAM.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.KMS.example.JAM.dto.Article;
import com.KMS.example.JAM.util.DBUtil;
import com.KMS.example.JAM.util.SecSql;

public class ArticleDao {
	Connection conn;
	
	public ArticleDao(Connection conn) {
		this.conn = conn;
	}
	
	public int doWrite(String title,String body, int writer) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO article");
		sql.append(" SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);
		sql.append(", writer = ?", writer);

		int id = DBUtil.insert(conn, sql);

		return id;
	}
	public void doModify(String title, String body, int id) {
		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append(" SET updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);
		sql.append(" WHERE id = ?", id);

		DBUtil.update(conn, sql);
	}
	
	public List<Article> showList() {
		SecSql sql = new SecSql();

		sql.append("SELECT article.*,member.`name`");
		sql.append("FROM article INNER JOIN");
		sql.append("`member` ON");
		sql.append("article.writer = member.id");
		sql.append("ORDER BY id DESC");

		List<Map<String, Object>> articlesListMap = DBUtil.selectRows(conn, sql);
		
		List<Article> articles = new ArrayList<>();

		for (Map<String, Object> articleMap : articlesListMap) {
			articles.add(new Article(articleMap));
		}
		return articles;

	}
	
	public int searchArticle(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT COUNT(*)");
		sql.append("FROM article");
		sql.append("WHERE id =?", id);

		int count = DBUtil.selectRowIntValue(conn, sql);
		
		return count;
	}
	
	public void doDelete(int id) {
		SecSql sql = new SecSql();
		sql.append("DELETE FROM article");
		sql.append("WHERE id = ?", id);

		DBUtil.delete(conn, sql);
	}
	
	public Map showDetail(int id) {
		
		SecSql sql = new SecSql();
		sql.append("SELECT article.*,member.`name`");
		sql.append("FROM article INNER JOIN");
		sql.append("`member` ON article.writer = member.id");
		sql.append("WHERE article.id = ?", id);
		

		Map<String, Object> articleMap = DBUtil.selectRow(conn, sql);
		
		return articleMap;
	}
	
	public int matchLoginMember(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT writer");
		sql.append("FROM article");
		sql.append("WHERE id =?", id);
		
		return DBUtil.selectRowIntValue(conn, sql);
	}
	
	public void incrementHit(int id) {
		SecSql sql = new SecSql();
		sql.append("UPDATE article");
		sql.append("SET hit = hit+1");
		sql.append("WHERE id = ?", id);
		
		DBUtil.update(conn, sql);
	}
	
}
