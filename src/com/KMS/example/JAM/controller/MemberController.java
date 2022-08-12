package com.KMS.example.JAM.controller;

import com.KMS.example.JAM.util.DBUtil;
import com.KMS.example.JAM.util.SecSql;

public class MemberController extends Controller{
	public void doJoin() {
		String loginId;
		String loginPw;
		String checkLoginPw;
		String name;
		String nameNumbering;
		while (true) {
			System.out.printf("아이디 : ");
			loginId = sc.nextLine();

			SecSql idCheckSql = new SecSql();
			idCheckSql.append("SELECT COUNT(*) FROM");
			idCheckSql.append("`member` WHERE");
			idCheckSql.append("loginId= ?", loginId);

			int idCount = DBUtil.selectRowIntValue(conn, idCheckSql);

			if (idCount >= 1) {
				System.out.println("이미 사용하는 사람이 있는 ID입니다.");
				continue;
			} else if (idCount == 0) {
				break;
			}
		}
		while (true) {
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("비밀번호 확인 : ");
			checkLoginPw = sc.nextLine();
			if(!loginPw.equals(checkLoginPw)) {
				System.out.println("비밀번호가 틀렸습니다.");
				System.out.println("다시 입력해주세요");
				continue;
			}
			if (loginPw.equals(checkLoginPw)) {
				break;
			}
		}

		System.out.printf("이름 : ");
		name = sc.nextLine();
		String checkName = name +"%";
		nameNumbering = name;
		SecSql nameCheckSql = new SecSql();
		nameCheckSql.append("SELECT COUNT(*) FROM");
		nameCheckSql.append("`member` WHERE");
		nameCheckSql.append("`name` LIKE ?",checkName);
		
		int sameNameCount = DBUtil.selectRowIntValue(conn, nameCheckSql);
		
		if(sameNameCount>0) {
			nameNumbering = name += sameNameCount;
		}
		
		SecSql sql = new SecSql();

		sql.append("INSERT INTO `member` SET");
		sql.append("regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", `name` = ?", nameNumbering);

		DBUtil.insert(conn, sql);
		System.out.printf("%s 회원님 가입을 환영합니다.\n", name);
	}

}
