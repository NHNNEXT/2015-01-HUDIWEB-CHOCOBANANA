package test.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ubuntudo.dao.UserDao;
import ubuntudo.model.UserEntity;

public class UserDaoTest {
	@Test
	public void insertUser(){
		UserDao uDao=new UserDao();
		boolean inserUserResult = uDao.insertUser("Mark", "a@a", "a");
		
		assertEquals(true, inserUserResult);
	}
	@Test
	public void retrieveUser(){
		UserEntity comparisonUser = new UserEntity(1L, "Mark", "a@a", "a");
		
		UserDao uDao=new UserDao();
		UserEntity retrievedUser = uDao.retrieveUser("a@a", "a");
		
		assertEquals(comparisonUser.getUid(), retrievedUser.getUid());
		assertEquals(comparisonUser.getName(), retrievedUser.getName());
		assertEquals(comparisonUser.getEmail(), retrievedUser.getEmail());
		assertEquals(comparisonUser.getPasswd(), retrievedUser.getPasswd());
	}
}
