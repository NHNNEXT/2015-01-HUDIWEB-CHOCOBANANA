package ubuntudo.dao;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ubuntudo.JDBCManager;
import ubuntudo.model.UserEntity;

@Repository
public class UserDao extends JDBCManager {
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	public boolean insertUser(String name, String email, String passwd) {
		logger.info("---->UserDao.insertUser");

		conn = getConnection();
		boolean insertResult = false;

		/*
		 * 현재는 직접 만든 JDBCManager를 상속받아 사용하지만
		 * 
		 * Spring에서는 이를 Template-method pattern으로 추상화한 JDBCTemplate이 존재합니다.
		 * 학습하셔서 한번 적용해보시길 바랍니다. :)
		 * 
		 * 여유가 된다면 직접 만들어보는 것도 좋아요.
		 */
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
			
			// 그리고 항상 로거를 사용하시는 것이 좋습니다 
			System.out.println(email);
			System.out.println(passwd);
			
			pstmt.setString(1, email);
			pstmt.setString(2, passwd);
			System.out.println(pstmt.toString());
			resultSet = pstmt.executeQuery();
			System.out.println(resultSet.toString());
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
	
	
	public Boolean validateUser(String email) {
		logger.info("---->UserDao.validateUser");

		conn = getConnection();

		Boolean isExistinguser = false;
		try {
			pstmt = conn.prepareStatement("select email from user where email = ? ");
			pstmt.setString(1, email);
			resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				isExistinguser = true;
			}
		} catch (SQLException e) {
			System.out.println("DB validateUser Error: " + e.getMessage());
		} finally {
			close(resultSet, pstmt, conn);
		}
		logger.info("<----UserDao.validateUser");
		return isExistinguser;
	}
}
