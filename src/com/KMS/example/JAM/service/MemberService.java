package com.KMS.example.JAM.service;

import java.sql.Connection;
import java.util.Map;

import com.KMS.example.JAM.dao.MemberDao;

public class MemberService {
	Connection conn;
	MemberDao memberDao;

	public MemberService(Connection conn) {
		this.conn = conn;
		memberDao = new MemberDao(conn);
	}
	
	public int idCheck(String loginId) {
		return memberDao.idCheck(loginId);
	}
	
	public int sameNameCount(String checkName) {
		return memberDao.sameNameCount(checkName);
	}
	public void doJoin(String loginId, String loginPw, String nameNumbering) {
		memberDao.doJoin(loginId,loginPw,nameNumbering);
	}
	public int doLogin(String loginId, String loginPw) {
		return memberDao.doLogin(loginId,loginPw);
	}
	public String getByName(String loginId) {
		return memberDao.getByName(loginId);
	}
	public int getById(String loginId) {
		return memberDao.getById(loginId);
	}
	
}
