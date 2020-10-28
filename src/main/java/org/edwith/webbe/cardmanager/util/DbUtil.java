package org.edwith.webbe.cardmanager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String server = "jdbc:mysql:localhost/3306/carddb?autoReconnect=true"
			+ "&characterEncoding=UTF-8&ServerTimeZone=Asia/Seoul&"
			+ "useSSL=false&useUnicode=true";
	private static String user = "manager";
	private static String pass = "card123";
	
	public static Connection getConnection() {
		return getConnection(server, user, pass);
	}

	private static Connection getConnection(String server, String user, String pass) {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(server, user, pass);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
