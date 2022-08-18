package com.KMS.example.JAM.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class Article extends Object {

	public int id;
	public LocalDateTime regDate;
	public LocalDateTime updateDate;
	public String title;
	public String body;
	public int writer;
	public int hit = 0;
	
	public String name;

	public Article(int id, LocalDateTime regDate, LocalDateTime updateDate, String title, String body, int writer, int hit, String name) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
		this.writer = writer;
		this.hit = hit;
		this.name = name;
	}

	public Article(int id, String title, String body, int writer) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.writer = writer;
	}
	

	public Article(Map<String, Object> articleMap) {
		this.id = (int) articleMap.get("id");
		this.title = (String) articleMap.get("title");
		this.body = (String) articleMap.get("body");
		this.regDate = (LocalDateTime) articleMap.get("regDate");
		this.updateDate = (LocalDateTime) articleMap.get("updateDate");
		this.writer = (int) articleMap.get("writer");
		this.hit = (int) articleMap.get("hit");
		this.name = (String) articleMap.get("name");
	}

}