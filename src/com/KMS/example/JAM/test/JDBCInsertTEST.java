package com.KMS.example.JAM.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCInsertTEST {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String user = "root";
		String password = "";
		String url = "jdbc:mysql://127.0.0.1:3306/article_manager?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn.toString());
			System.out.println("연결성공");
			
			
			String sql = "INSERT INTO article SET regDate = NOW(),updateDate = NOW(), title = CONCAT('제목1',RAND()),`body`=CONCAT('내용',RAND());";
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);

			int affectedRows = pstmt.executeUpdate();

			System.out.println("affectedRows : " + affectedRows);
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("연결실패");
		} finally {
			 if (pstmt != null) {
	                try {
	                    pstmt.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public String sqlwrite(String title, String body){
		return "INSERT INTO article SET regDate = NOW(),updateDate = NOW(), title = CONCAT('제목1',RAND()),`body`=CONCAT('내용',RAND());";
	}
}