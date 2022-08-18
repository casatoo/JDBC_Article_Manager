package com.KMS.example.JAM.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.KMS.example.JAM.dao.ArticleDao;
import com.KMS.example.JAM.dto.Article;

public class ArticleService {
	Connection conn;
	ArticleDao articleDao;

	public ArticleService(Connection conn) {
		this.conn = conn;
		articleDao = new ArticleDao(conn);
	}

	public int doWrite(String title, String body, int writer) {
		return articleDao.doWrite(title,body, writer);
	}

	public void doModify(String title, String body, int id) {
		articleDao.doModify(title,body,id);
	}
	
	public List<Article> showList() {	
		return articleDao.showList();
	}
	
	public int searchArticle(int id) {
		return articleDao.searchArticle(id);
	}
	public void doDelete(int id) {
		articleDao.doDelete(id);
	}
	
	public Map showDetail(int id) {
		return articleDao.showDetail(id);
	}
	
	public int matchLoginMember(int id) {
		return articleDao.matchLoginMember(id);
	}
	
	public void incrementHit(int id) {
		articleDao.incrementHit(id);
	}
}
