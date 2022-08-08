package com.KMS.example.JAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Article> articles = new ArrayList<>();
		int id = 1;
		
		while (true) {
			System.out.printf("명령어 :");
			String cmd = sc.nextLine().trim();
			String cmdBit[] = cmd.split(" ");
			if(cmd.equals("exit")) {
				System.out.println("시스템 종료");
				break;
			}

			switch (cmd) {
				case "article write":
					System.out.println("== 게시물 작성 ==");
					System.out.printf("제목:");
					String title = sc.nextLine();
					System.out.printf("내용:");
					String body = sc.nextLine();
					Article article = new Article(id,title,body);
					articles.add(article);
					id++;
					System.out.println(article);
					continue;
					
				case "article list":
					if(articles.size()==0) {
						System.out.println("게시물이 없습니다.");
						continue;
					}
					System.out.println("번호 / 제목 ");
					for(Article articlelist:articles) {
						System.out.println(articlelist.id +"  "+ articlelist.title);
					}
					continue;
					
				
			}

			sc.close();
		}
	}
}
