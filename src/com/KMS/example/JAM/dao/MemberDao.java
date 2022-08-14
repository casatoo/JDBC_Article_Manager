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
}
