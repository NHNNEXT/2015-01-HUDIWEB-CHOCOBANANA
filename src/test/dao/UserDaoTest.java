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
	public void insertUser() {
		assertEquals(1, userDao.insertUser("MarkE", "f@f", "a"));
	}

	@Test
	public void retrieveUser() {
		UserEntity comparisonUser = new UserEntity(18L, "MarkE", "f@f", null);
		UserEntity retrievedUser = userDao.retrieveUser("f@f", "a");

		assertEquals(comparisonUser.getUid(), retrievedUser.getUid());
		assertEquals(comparisonUser.getName(), retrievedUser.getName());
		assertEquals(comparisonUser.getEmail(), retrievedUser.getEmail());
		assertEquals(comparisonUser.getPasswd(), retrievedUser.getPasswd());
	}
}