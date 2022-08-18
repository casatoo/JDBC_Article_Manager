package com.KMS.example.JAM.controller;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.KMS.example.JAM.dto.Article;
import com.KMS.example.JAM.service.ArticleService;

public class ArticleController extends Controller {
	ArticleService articleService;

	public ArticleController(Connection conn, Scanner sc) {
		super(sc);
		articleService = new ArticleService(conn);
	}

	public void doWrite() {
		
		if(!Controller.logincheck()) {
			System.out.println("로그인이 필요한 서비스 입니다.");
			return;
		}
		int writer = loginedMember.id;
		
		System.out.println("== 게시물 작성 ==");
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		int id = articleService.doWrite(title, body, writer);

		System.out.printf("%d번 게시물이 생성되었습니다.\n", id);
	}

	public void doModify(String cmd) {
		if(!Controller.logincheck()) {
			System.out.println("로그인이 필요한 서비스 입니다.");
			return;
		}
		if (!noListNumber(cmd)) {
			return;
		}
		int id = Integer.parseInt(cmd.split(" ")[2]);
		
		Map<String, Object> articleMap = articleService.showDetail(id);
		
		if (articleMap.isEmpty()) {
			System.out.printf("%d번 글이 존재하지 않습니다.\n", id);
			return;
		}
		int matchLoginMember = articleService.matchLoginMember(id);
		
		if(matchLoginMember!=loginedMember.id) {
			System.out.println("글 작성자만 수정할 수 있습니다.");
			return;
		}

		System.out.printf("== %d번 게시물 수정 ==\n", id);
		System.out.printf("새 제목 : ");
		String title = sc.nextLine();
		System.out.printf("새 내용 : ");
		String body = sc.nextLine();

		articleService.doModify(title, body, id);

		System.out.printf("%d번 게시물이 수정 되었습니다\n", id);
	}

	public int showList() {
		System.out.println("== 게시물 리스트 ==");

		List<Article> articles = articleService.showList();
		
		if (articles.size() == 0) {
			System.out.println("게시물이 없습니다");
			return 0;
		}
		System.out.println("번호  /  제목   / 작성자  / 조회수");

		for (Article article : articles) {
			System.out.printf("%d  /  %s    / %s   / %d\n", article.id, article.title, article.name, article.hit);
		}
		return 1;
	}

	public void doDelete(String cmd) {

		if (!noListNumber(cmd)) {
			return;
		}

		int id = Integer.parseInt(cmd.split(" ")[2]);

		int count = articleService.searchArticle(id);

		if (count == 0) {
			System.out.printf("%d번 글이 존재하지 않습니다.\n", id);
		} else if (count == 1) {

			articleService.doDelete(id);

			System.out.printf("%d번 게시물이 삭제 되었습니다\n", id);
		}
	}

	public int showDetail(String cmd) {
		

		if (!noListNumber(cmd)) {
			return 0;
		}

		int id = Integer.parseInt(cmd.split(" ")[2]);
		
		articleService.incrementHit(id);

		Map<String, Object> articleMap = articleService.showDetail(id);

		if (articleMap.isEmpty()) {
			System.out.printf("%d번 글이 존재하지 않습니다.\n", id);
			return 0;
		}

		Article getArticle = new Article(articleMap);
		System.out.println(" 글번호: " + getArticle.id);
		System.out.println(" 글제목: " + getArticle.title);
		System.out.println(" 글내용: " + getArticle.body);
		System.out.println(" 작성자: " + getArticle.name);
		System.out.println(" 조회수: " + getArticle.hit);

		return 1;
	}

	public boolean noListNumber(String cmd) {
		String[] cmdBits = cmd.split(" ");
		if (cmdBits.length == 2) {
			System.out.println("글번호가 입력되지 않았습니다.");
			return false;
		}
		return true;
	}

}
