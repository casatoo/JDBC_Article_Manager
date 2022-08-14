package com.KMS.example.JAM.controller;

import java.sql.Connection;
import java.util.Scanner;

import com.KMS.example.JAM.dto.Member;
import com.KMS.example.JAM.service.MemberService;
import com.KMS.example.JAM.util.DBUtil;
import com.KMS.example.JAM.util.SecSql;

public class MemberController extends Controller{
	MemberService memberService;
	
	public MemberController(Connection conn, Scanner sc) {
		super(sc);
		memberService = new MemberService(conn);
	}
	
	public void doJoin() {
		
		Member member = new Member();
		
		String checkLoginPw;
		String nameNumbering;
		
		while (true) {
			System.out.printf("아이디 : ");
			member.loginId = sc.nextLine();
			
			memberService.idCheck(member.loginId);

			int idCount = memberService.idCheck(member.loginId);

			if (idCount >= 1) {
				System.out.println("이미 사용하는 사람이 있는 ID입니다.");
				continue;
			} else if (idCount == 0) {
				break;
			}
		}
		while (true) {
			System.out.printf("비밀번호 : ");
			member.loginPw = sc.nextLine();
			System.out.printf("비밀번호 확인 : ");
			checkLoginPw = sc.nextLine();
			if(!member.loginPw.equals(checkLoginPw)) {
				System.out.println("비밀번호가 틀렸습니다.");
				System.out.println("다시 입력해주세요");
				continue;
			}
			if (member.loginPw.equals(checkLoginPw)) {
				break;
			}
		}

		System.out.printf("이름 : ");
		member.name = sc.nextLine();
		String checkName = member.name +"%";
		nameNumbering = member.name;
		
		int sameNameCount = memberService.sameNameCount(checkName);
		
		if(sameNameCount>0) {
			nameNumbering = member.name+= sameNameCount;
		}
		
		memberService.doJoin(member.loginId,member.loginPw, nameNumbering);
		System.out.printf("%s 회원님 가입을 환영합니다.\n", member.name);
	}

}
