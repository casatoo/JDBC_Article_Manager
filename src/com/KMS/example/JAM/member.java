package com.KMS.example.JAM;

import java.time.LocalDateTime;

public class member {
	public int id;
	public LocalDateTime regDate;
	public LocalDateTime updateDate;
	public String loginId;
	public String loginPw;
	public String name;
	
	public member(){
	}

	public member(int id, LocalDateTime regDate, LocalDateTime updateDate, String loginId, String loginPw,String name) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
	}
	
}
