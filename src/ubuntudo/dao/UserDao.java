package ubuntudo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Repository;

import support.Qrys;
import support.QrysU;
import ubuntudo.model.UserEntity;

@Repository
public class UserDao extends Qrys {
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		DatabasePopulatorUtils.execute(populator, jdbcTemplate.getDataSource());
	}

	public int insertUserDao(String name, String email, String passwd) {
		logger.debug("insertUserDao()");
		return jdbcTemplate.update(QrysU.INSERT_USER, name, email, passwd);
	}

	public UserEntity retrieveUserDao(String email, String passwd) {
		logger.info("retrieveUserDao()");
		logger.debug("email:{}, passwd: {}", email, passwd);
		
		RowMapper<UserEntity> rowMapper = new RowMapper<UserEntity>() {
			public UserEntity mapRow(ResultSet rs, int rowNum) {
				try {
					return new UserEntity(rs.getLong("uid"), rs.getString("name"), rs.getString("email"), null);
				} catch (SQLException e) {
					throw new BeanInstantiationException(UserEntity.class, e.getMessage(), e);
				}
			}
		};
		logger.debug("jdbcTemplate:{}", jdbcTemplate);
		return jdbcTemplate.queryForObject(QrysU.RETRIEVE_USER, rowMapper, email, passwd);
	}

	public String validateUser(String email) {
		logger.info("---->UserDao.validateUser()");
		logger.debug("email:{}", email);
		
		RowMapper<UserEntity> rowMapper = new RowMapper<UserEntity>() {
			public UserEntity mapRow(ResultSet rs, int rowNum) {
				try {
					return new UserEntity(rs.getLong("uid"), null, rs.getString("email"), null);
				} catch (SQLException e) {
					throw new BeanInstantiationException(UserEntity.class, e.getMessage(), e);
				}
			}
		};
		logger.debug("jdbcTemplate:{}", jdbcTemplate);
		logger.info("<----UserDao.validateUser");
		String result = "";
		try {
				// 일단 null이 떨어지면
			   result =  jdbcTemplate.queryForObject(QrysU.VALIDATE_USER, rowMapper, email).toString();
			  } catch (DataAccessException e) {
			   if (e instanceof IncorrectResultSizeDataAccessException) {
				   // Exception 의 이유가 queryforobjcet의 리턴값이 없어서 이면
				   result="";
				   logger.debug( e.getMessage());
			   }
			  }
		return result;
	}
}