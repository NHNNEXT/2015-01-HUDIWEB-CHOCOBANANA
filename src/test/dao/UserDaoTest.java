package test.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ubuntudo.dao.UserDao;
import ubuntudo.model.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/ubuntudo-servlet.xml")
public class UserDaoTest {

	@Autowired
	private UserDao userDao;

	@Test
	public void insertUserTest() {
		assertEquals(1, userDao.insertUserDao("MarkG", "a", "a"));
	}

	@Test
	public void retrieveUserTest() {
		UserEntity comparisonUser = new UserEntity(19L, "MarkG", "a", null);
		UserEntity retrievedUser = userDao.retrieveUserDao("a", "a");

		assertEquals(comparisonUser.getUid(), retrievedUser.getUid());
		assertEquals(comparisonUser.getName(), retrievedUser.getName());
		assertEquals(comparisonUser.getEmail(), retrievedUser.getEmail());
		assertEquals(comparisonUser.getPasswd(), retrievedUser.getPasswd());
	}
}