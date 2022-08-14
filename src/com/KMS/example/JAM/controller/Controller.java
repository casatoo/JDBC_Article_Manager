package com.KMS.example.JAM.controller;

import java.sql.Connection;
import java.util.Scanner;

public abstract class Controller {
	
	public Controller(Scanner sc) {
		this.sc = sc;
	}
	public Connection conn;
	public Scanner sc;
	
}
