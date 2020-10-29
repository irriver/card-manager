package org.edwith.webbe.cardmanager.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionTest {
	public static void main(String[] args) {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dburl = "jdbc:mysql://localhost:3306/carddb?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=true";
			conn = DriverManager.getConnection(dburl, "manager", "card123");
			System.out.println("Connect DB Success!");
		
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load Driver");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}