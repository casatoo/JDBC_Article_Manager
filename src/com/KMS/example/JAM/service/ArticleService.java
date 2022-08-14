package com.KMS.example.JAM.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import com.KMS.example.JAM.dao.ArticleDao;

public class ArticleService {
	Connection conn;
	ArticleDao articleDao;

	public ArticleService(Connection conn) {
		this.conn = conn;
		articleDao = new ArticleDao(conn);
	}

	public int doWrite(String title, String body) {
		return articleDao.doWrite(title,body);
	}

	public void doModify(String title, String body, int id) {
		articleDao.doModify(title,body,id);
	}
	
	public ArrayList showList() {	
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
	
	

}