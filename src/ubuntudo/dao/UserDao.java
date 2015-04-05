package ubuntudo.dao;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ubuntudo.JDBCManager;
import ubuntudo.model.UserEntity;

public class UserDao extends JDBCManager {
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	public boolean insertUser(String name, String email, String passwd) {
		logger.info("---->UserDao.insertUser");

		conn = getConnection();
		boolean insertResult = false;

		try {
			pstmt = conn.prepareStatement("insert into user (name, email, passwd) values (?, ?, ?)");
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, passwd);
			if (pstmt.executeUpdate() == 1) {
				insertResult = true;
			}

		} catch (SQLException e) {
			logger.error("DB insertUser Error: " + e.getMessage());
		} finally {
			close(resultSet, pstmt, conn);
		}
		logger.info("<----UserDao.insertUser");
		return insertResult;
	}

	public UserEntity retrieveUser(String email, String passwd) {
		logger.info("---->UserDao.retrieveUser");

		conn = getConnection();

		UserEntity currentUser = null;

		try {
			pstmt = conn.prepareStatement("select uid, name, email, passwd from user where email = ? and passwd = ?");
			pstmt.setString(1, email);
			pstmt.setString(2, passwd);
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				currentUser = new UserEntity(resultSet.getLong("uid"), resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("passwd"));
			}
		} catch (SQLException e) {
			System.out.println("DB retrieveUser Error: " + e.getMessage());
		} finally {
			close(resultSet, pstmt, conn);
		}
		logger.info("<----UserDao.retrieveUser");
		return currentUser;
	}
}
