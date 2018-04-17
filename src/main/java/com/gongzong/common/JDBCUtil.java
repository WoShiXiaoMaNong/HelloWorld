package com.gongzong.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtil {
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER_NAME = "scott";
	private static final String USER_PASSWORD = "qkhhje";
	private static final String JDBC_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final List<Connection> conns = new ArrayList<Connection>();
	private static final int MIN_SIZE = 5;
	private static final int MAX_SIZE = 10;
	static {
		try {
			Class.forName(JDBC_CLASS_NAME);
			for(int i=0;i<MAX_SIZE;i++){
				conns.add(DriverManager.getConnection(JDBC_URL,USER_NAME,USER_PASSWORD));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConn(){
		Connection conn = null;
		if(conns!= null && conns.size()>0){
			conn = conns.remove(0);
		}
		
		if(conns.size()<5){
			try {
				fullConns();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	private static void fullConns() throws SQLException{
		while(conns.size()<MAX_SIZE){
			conns.add(DriverManager.getConnection(JDBC_URL,USER_NAME,USER_PASSWORD));
		}
	}
	

	public static void closeConn(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
