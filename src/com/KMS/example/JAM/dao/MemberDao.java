package com.KMS.example.JAM.dao;

import java.sql.Connection;

import com.KMS.example.JAM.util.DBUtil;
import com.KMS.example.JAM.util.SecSql;

public class MemberDao {
	Connection conn;

	public MemberDao(Connection conn) {
		this.conn = conn;
	}

	public int idCheck(String loginId) {
		SecSql idCheckSql = new SecSql();
		idCheckSql.append("SELECT COUNT(*) FROM");
		idCheckSql.append("`member` WHERE");
		idCheckSql.append("loginId= ?", loginId);

		return DBUtil.selectRowIntValue(conn, idCheckSql);
	}

	public int sameNameCount(String checkName) {

		SecSql nameCheckSql = new SecSql();
		nameCheckSql.append("SELECT COUNT(*) FROM");
		nameCheckSql.append("`member` WHERE");
		nameCheckSql.append("`name` LIKE ?", checkName);

		return DBUtil.selectRowIntValue(conn, nameCheckSql);
	}
	
	public void doJoin(String loginId, String loginPw, String nameNumbering) {
		SecSql sql = new SecSql();

		sql.append("INSERT INTO `member` SET");
		sql.append("regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", `name` = ?", nameNumbering);

		DBUtil.insert(conn, sql);
	}
	public int doLogin(String loginId, String loginPw) {
		SecSql sql = new SecSql();
		sql.append("SELECT COUNT(*) FROM");
		sql.append("`member` WHERE");
		sql.append("loginId = ?",loginId);
		sql.append("AND loginPw = ?",loginPw);
		return DBUtil.selectRowIntValue(conn,sql);
	}
	public String getByName(String loginId) {
		SecSql sql = new SecSql();
		sql.append("SELECT `name` FROM `member`");
		sql.append("WHERE loginId = ?",loginId);
		return DBUtil.selectRowStringValue(conn,sql);
	}
	public int getById(String loginId) {
		SecSql sql = new SecSql();
		sql.append("SELECT id FROM");
		sql.append("`member` WHERE");
		sql.append("loginId = ?",loginId);
		
		return DBUtil.selectRowIntValue(conn,sql);
	}
}
