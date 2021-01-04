package com.wave.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    
	public static final String URL = "jdbc:mysql://localhost:3306/market?characterEncoding=utf8";
	public static final String USER = "root";
	public static final String PASS = "gt1010992686";
	
	private static Connection conn = null;
	static {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn = DriverManager.getConnection(URL,USER,PASS);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		
		return conn;
		
	}
	
}