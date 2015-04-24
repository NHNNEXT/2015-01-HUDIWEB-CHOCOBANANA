package test.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ubuntudo.controller.guild.GuildController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/ubuntudo-servlet.xml")
public class GuildColtrollerTest {

	@Autowired
	private GuildController guildController;
	
	@Test
	public void insertNewGuildControllerTest() {
		long leaderId = 101l;
		String newGuildName = "The Guild101";
		assertEquals(1, guildController.insertNewGuildController(leaderId, newGuildName));
	}

	@Test
	public void insertUserToGuildControllerTest() {
		long guildId = 1l;
		long userId = 1l;
		assertEquals(1, guildController.insertUserToGuildController(guildId, userId));
	}
	

	
	
	
//	@Test
//	public void retrieveGuildAndPartyControllerTest() {
//		long demanderIdSearch = 1l;
//		String guildNameSearch = "";
//		guildController.retrieveGuildAndPartyController(demanderIdSearch, guildNameSearch);
//	}
}