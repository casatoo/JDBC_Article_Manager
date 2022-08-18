package com.KMS.example.JAM.controller;

import java.sql.Connection;
import java.util.Scanner;

import com.KMS.example.JAM.dto.Member;

public abstract class Controller {
	public Connection conn;
	public Scanner sc;
	public static Member loginedMember = null;
	
	public Controller(Scanner sc) {
		this.sc = sc;
	}
	
	
	public static boolean logincheck() {
		if(loginedMember != null) {
		return true;
		}
		return false;
	}
	
}
