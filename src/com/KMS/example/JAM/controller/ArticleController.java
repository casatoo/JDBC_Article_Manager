package com.KMS.example.JAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.KMS.example.JAM.Article;
import com.KMS.example.JAM.util.DBUtil;
import com.KMS.example.JAM.util.SecSql;

public class ArticleController extends Controller{

	public void doWrite() {
		System.out.println("== 게시물 작성 ==");
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		SecSql sql = new SecSql();

		sql.append("INSERT INTO article");
		sql.append(" SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);

		int id = DBUtil.insert(conn, sql);

		System.out.printf("%d번 게시물이 생성되었습니다.\n", id);
	}

	public void doModify(String cmd) {
		int id = Integer.parseInt(cmd.split(" ")[2]);
		
		System.out.printf("== %d번 게시물 수정 ==\n", id);
		System.out.printf("새 제목 : ");
		String title = sc.nextLine();
		System.out.printf("새 내용 : ");
		String body = sc.nextLine();

		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append(" SET updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);
		sql.append(" WHERE id = ?", id);

		DBUtil.update(conn, sql);

		System.out.printf("%d번 게시물이 수정 되었습니다\n", id);
	}

	public int showList() {
		System.out.println("== 게시물 리스트 ==");

		List<Article> articles = new ArrayList<>();

		SecSql sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("ORDER BY id DESC");

		List<Map<String, Object>> articlesListMap = DBUtil.selectRows(conn, sql);

		for (Map<String, Object> articleMap : articlesListMap) {
			articles.add(new Article(articleMap));
		}

		if (articles.size() == 0) {
			System.out.println("게시물이 없습니다");
			return 0;
		}

		System.out.println("번호  /  제목");

		for (Article article : articles) {
			System.out.printf("%d  /  %s\n", article.id, article.title);
		}
		return 1;
	}

	public void doDelete(String cmd) {
		int id = Integer.parseInt(cmd.split(" ")[2]);

		SecSql sql = new SecSql();
		sql.append("SELECT COUNT(*)");
		sql.append("FROM article");
		sql.append("WHERE id =?", id);

		int count = DBUtil.selectRowIntValue(conn, sql);

		if (count == 0) {
			System.out.printf("%d번 글이 존재하지 않습니다.\n", id);
		} else if (count == 1) {
			SecSql sql2 = new SecSql();
			sql2.append("DELETE FROM article");
			sql2.append("WHERE id = ?", id);

			DBUtil.delete(conn, sql2);

			System.out.printf("%d번 게시물이 삭제 되었습니다\n", id);
		}
	}

	public int showDetail(String cmd) {
		int id = Integer.parseInt(cmd.split(" ")[2]);

		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM article");
		sql.append("WHERE id =?", id);

		Map<String, Object> articleMap = DBUtil.selectRow(conn, sql);

		if (articleMap.isEmpty()) {
			System.out.printf("%d번 글이 존재하지 않습니다.\n", id);
			return 0;
		}

		Article getArticle = new Article(articleMap);
		System.out.println(" 글번호 / 제목 / 내용");
		System.out.println(" 글번호: " + getArticle.id);
		System.out.println(" 글제목: " + getArticle.title);
		System.out.println(" 글내용: " + getArticle.body);
		System.out.println(" 작성일: " + getArticle.regDate);
		System.out.println(" 수정일: " + getArticle.updateDate);
		
		return 1;
	}


}
