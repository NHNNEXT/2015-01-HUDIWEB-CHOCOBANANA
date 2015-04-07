package ubuntudo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCManager {
	private static final Logger logger = LoggerFactory.getLogger(JDBCManager.class);
	
	protected ResultSet resultSet = null;
	protected PreparedStatement pstmt = null;
	protected Connection conn = null;
	
	protected Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			logger.info("Driver Loaded.");
		} catch (ClassNotFoundException e) {
			logger.info("Driver Error" + e.getMessage());
		}
		try {
			logger.info("Getting Connection.");
			String addr = WebServerLauncher.dbInfo.get("address");
			String user = WebServerLauncher.dbInfo.get("connectionId");
			String pw = WebServerLauncher.dbInfo.get("connectionPassWd");
			conn = DriverManager.getConnection(addr, user, pw);
			logger.info("Connection Successed.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
  protected void close(ResultSet rs, PreparedStatement pm, Connection cn) {
    try {
      if (rs != null) {
        rs.close();
      }
      if (pm != null) {
      	pm.close();
      }
      if (cn != null) {
      	cn.close();
      }
    } catch (Exception e) {
    	e.toString();
    }
  }
}