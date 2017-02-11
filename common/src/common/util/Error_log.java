package common.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Error_log {
	
	public static void error_log(Connection conn,String threadName,String error_desc,String excetion_desc,String user){
		String sql = "{call APP_UTIL_PKG.insert_error(?,?,?,?)}";
		if(conn == null){
			conn = JDBCUtil.getConn();
		}
		try {
			CallableStatement  cs = conn.prepareCall(sql);
			cs.setString(1, threadName);
			cs.setString(2, error_desc);
			cs.setString(3, excetion_desc);
			cs.setString(4, user);
			cs.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

