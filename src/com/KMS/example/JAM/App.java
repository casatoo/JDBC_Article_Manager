package com.KMS.example.JAM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.KMS.example.JAM.util.DBUtil;
import com.KMS.example.JAM.util.SecSql;

public class App {

	public void run() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			// DB 연결
			Connection conn = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");

			} catch (ClassNotFoundException e) {
				System.out.println("예외 : 클래스가 없습니다.");
				System.out.println("프로그램을 종료합니다.");
				break;
			}

			String url = "jdbc:mysql://1.234.44.77:3306/article_manager?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

			try {
				conn = DriverManager.getConnection(url, "user1", "mkop9074!@");

				int actionResult = doAction(conn, sc, cmd);

				if (actionResult == -1) {
					break;
				}

			} catch (SQLException e) {
				System.out.println("@@@@에러@@@@: " + e);
				break;
			} finally {
				try {
					if (conn != null && !conn.isClosed()) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}

	private int doAction(Connection conn, Scanner sc, String cmd) {

		if (cmd.equals("article write")) {
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

		} else if (cmd.startsWith("article modify ")) {
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

		} else if (cmd.equals("article list")) {

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

		} else if (cmd.startsWith("article delete")) {
			int id = Integer.parseInt(cmd.split(" ")[2]);

			SecSql sql = new SecSql();
			sql.append("SELECT COUNT(*)");
			sql.append("FROM article");
			sql.append("WHERE id =?", id);
			
			int count = DBUtil.selectRowIntValue(conn, sql);
			
			if (count == 0) {
				System.out.printf("%d번 글이 존재하지 않습니다.\n",id);
			}else if(count == 1){
			SecSql sql2 = new SecSql();
			sql2.append("DELETE FROM article");
			sql2.append("WHERE id = ?", id);

			DBUtil.delete(conn, sql2);

			System.out.printf("%d번 게시물이 삭제 되었습니다\n", id);
			}

		} else if (cmd.startsWith("article detail")) {
			int id = Integer.parseInt(cmd.split(" ")[2]);

			SecSql sql = new SecSql();
			sql.append("SELECT *");
			sql.append("FROM article");
			sql.append("WHERE id =?", id);

			Map<String, Object> articleMap = DBUtil.selectRow(conn, sql);
			
			if (articleMap.isEmpty()) {
				System.out.printf("%d번 글이 존재하지 않습니다.\n",id);
				return 0;
			}
			
			Article getArticle = new Article(articleMap);
			System.out.println(" 글번호 / 제목 / 내용");
			System.out.println(" 글번호: "+getArticle.id);
			System.out.println(" 글제목: "+getArticle.title);
			System.out.println(" 글내용: "+getArticle.body);
			System.out.println(" 작성일: "+getArticle.regDate);
			System.out.println(" 수정일: "+getArticle.updateDate);
			
			

		}else if(cmd.equals("member join")) {
			
			System.out.printf("아이디 : ");
			String loginId = sc.nextLine();
			System.out.printf("비일번호 : ");
			String loginPw = sc.nextLine();
			System.out.printf("이름 : ");
			String name = sc.nextLine();
			
			SecSql sql = new SecSql();
			
			sql.append("INSERT INTO `member` SET");
			sql.append("regDate = NOW()");
			sql.append(", updateDate = NOW()");
			sql.append(", loginId = ?",loginId);
			sql.append(", loginPw = ?",loginPw);
			sql.append(", `name` = ?",name);

			
			int id = DBUtil.insert(conn, sql);
			System.out.printf("%d 번 회원님 가입을 환영합니다.\n",id);
		}
		if (cmd.equals("exit")) {
			System.out.println("프로그램을 종료합니다");
			return -1;
		}
		return 0;
	}

}
