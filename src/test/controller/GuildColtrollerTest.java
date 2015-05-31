package test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ubuntudo.controller.GuildController;
import ubuntudo.model.GuildEntity;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/ubuntudo-servlet.xml")
public class GuildColtrollerTest {

	Gson gson = new Gson();

	@Autowired
	private GuildController guildController;

	@Test
	public void insertNewGuildControllerTest() {
		long leaderId = 0l;
		String newGuildName = "";

		leaderId = 1l;
		newGuildName = "1s guild 1";
		assertEquals(2, guildController.insertNewGuildController(leaderId, newGuildName));

		leaderId = 1l;
		newGuildName = "1s guild 2";
		assertEquals(2, guildController.insertNewGuildController(leaderId, newGuildName));

		leaderId = 1l;
		newGuildName = "1s guild 3";
		assertEquals(2, guildController.insertNewGuildController(leaderId, newGuildName));

		leaderId = 2l;
		newGuildName = "2s guild 1";
		assertEquals(2, guildController.insertNewGuildController(leaderId, newGuildName));

		leaderId = 2l;
		newGuildName = "2s guild 2";
		assertEquals(2, guildController.insertNewGuildController(leaderId, newGuildName));

		leaderId = 2l;
		newGuildName = "2s guild 3";
		assertEquals(2, guildController.insertNewGuildController(leaderId, newGuildName));
	}

	@Test
	public void insertUserToGuildControllerTest() {
		long guildId = 0l;
		long userId = 0l;

		guildId = 6l;
		userId = 1l;
//		assertEquals(1, guildController.insertUserToGuildController(guildId, userId));

		guildId = 3l;
		userId = 2l;
//		assertEquals(1, guildController.insertUserToGuildController(guildId, userId));
		
		guildId = 1l;
		userId = 2l;
//		assertEquals(1, guildController.insertUserToGuildController(guildId, userId));
	}

	@Test
	public void retrieveGuildSearchControllerTest() {
		String guildName = "1";
		List<GuildEntity> guildList = guildController.retrieveGuildListSearchController(guildName);
		System.out.println(guildList.toString());

		Gson gson = new Gson();
		String json = gson.toJson(guildList);
		System.out.println(json);

		assertNotNull(guildList);
	}

	@Test
	public void updateGuildControllerTest() {
		long gid = 3l;
		long leaderId = 105l;
		String guildName = "Guild EDITED";
		String status = "0";

		assertEquals(1, guildController.updateGuildController(gid, leaderId, guildName, status));
	}

	@Test
	public void retrieveMyGuildListControllerTest() {
		long uid = 1l;
		List<Map<String, Object>> map = guildController.retrieveMyGuildListController(uid);
		System.out.println(gson.toJson(map));
		assertNotNull(map);
	}

	@Test
	public void retrieveMyGuildAndPartyListControllerTest() {
		long uid = 1l;
//		String gp = guildController.retrieveMyGuildAndPartyListController(uid);
//		System.out.println(gp);
//		assertNotNull(gp);
	}

	@Test
	public void retrieveGuildDetailAndPartyListControllerTest() {
		long uid = 1l;
		long gid = 1l;
//		String guildDetailAndPartyList = guildController.retrieveGuildDetailAndPartyListController(uid, gid);
//		System.out.println(guildDetailAndPartyList);
//		assertNotNull(guildDetailAndPartyList);
	}
}