package com.KMS.example.JAM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.printf("명령어 :");
			String cmd = sc.nextLine().trim();
			String cmdBit[] = cmd.split(" ");
			if(cmd.equals("exit")) {
				System.out.println("시스템 종료");
				break;
			}

			switch (cmdBit[1]) {
				case "write":
				
					continue;
				case "list":
					
					continue;
				
			}

			sc.close();
		}
	}
}
