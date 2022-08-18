package com.KMS.example.JAM.controller;

import java.sql.Connection;
import java.util.Map;
import java.util.Scanner;

import com.KMS.example.JAM.dto.Member;
import com.KMS.example.JAM.service.MemberService;

public class MemberController extends Controller {
	MemberService memberService;
	Member member;

	public MemberController(Connection conn, Scanner sc) {
		super(sc);
		memberService = new MemberService(conn);
	}

	public void doJoin() {

		member = new Member();

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
			if (!member.loginPw.equals(checkLoginPw)) {
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
		String checkName = member.name + "%";
		nameNumbering = member.name;

		int sameNameCount = memberService.sameNameCount(checkName);

		if (sameNameCount > 0) {
			nameNumbering = member.name += sameNameCount;
		}

		memberService.doJoin(member.loginId, member.loginPw, nameNumbering);
		System.out.printf("%s 회원님 가입을 환영합니다.\n", member.name);
	}

	public void doLogin() {
		
		member = new Member();
		if(Controller.logincheck()) {
			System.out.println("이미 로그인 하였습니다.");
			return;
		}

		while (true) {
			System.out.printf("아이디 : ");
			member.loginId = sc.nextLine();
			System.out.printf("비밀번호 : ");
			member.loginPw = sc.nextLine();
			
			Map<String, Object> memberMap = memberService.getMember(member.loginId, member.loginPw);

			if (memberMap.isEmpty()) {
				System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
				continue;
			}else {
				Member getMember = new Member(memberMap);
				System.out.printf("%s 님 접속을 환영합니다\n",getMember.name);
				loginedMember = getMember;
				break;
			}
		}
	}
	public void showProfile() {
		if(Controller.logincheck()) {
			System.out.println("회원 번호: "+loginedMember.id);
			System.out.println("회원 이름: "+loginedMember.name);
			System.out.println("회원 아이디: "+loginedMember.loginId);
			System.out.println("회원 비밀번호: "+loginedMember.loginPw);
			System.out.println("회원 가입일: "+loginedMember.regDate);
		}else {
			System.out.println("로그인 해주세요");
		}
	}
	public void doLogout() {
		if(!Controller.logincheck()) {
			System.out.println("로그인이 되지 않았습니다.");
			return;
		}
		loginedMember =null;
		System.out.println("로그아웃 되었습니다.");
	}

}
