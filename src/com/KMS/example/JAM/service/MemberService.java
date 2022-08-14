package com.KMS.example.JAM.service;

import java.sql.Connection;

import com.KMS.example.JAM.dao.MemberDao;
import com.KMS.example.JAM.util.DBUtil;
import com.KMS.example.JAM.util.SecSql;

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
	
	
}
