package test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ubuntudo.controller.guild.GuildController;
import ubuntudo.model.GuildEntity;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/ubuntudo-servlet.xml")
public class GuildColtrollerTest {

	@Autowired
	private GuildController guildController;

	@Test
	public void insertNewGuildControllerTest() {
		long leaderId = 106l;
		String newGuildName = "The Guild106";
		assertEquals(2, guildController.insertNewGuildController(leaderId, newGuildName));
	}

	@Test
	public void insertUserToGuildControllerTest() {
		long guildId = 1l;
		long userId = 1l;
		assertEquals(1, guildController.insertUserToGuildController(guildId, userId));
	}

	
	@Test
	public void retrieveGuildSearchControllerTest() {
		String guildName = "uild10";
		List<GuildEntity> guildList =guildController.retrieveGuildSearchController(guildName);
		System.out.println(guildList.toString());

		Gson gson =new Gson();
    String json = gson.toJson(guildList);
    System.out.println(json);
    
		assertNotNull(guildList);
	}
	
	
	// @Test
	// public void retrieveGuildAndPartyControllerTest() {
	// long demanderIdSearch = 1l;
	// String guildNameSearch = "";
	// guildController.retrieveGuildAndPartyController(demanderIdSearch,
	// guildNameSearch);
	// }
}