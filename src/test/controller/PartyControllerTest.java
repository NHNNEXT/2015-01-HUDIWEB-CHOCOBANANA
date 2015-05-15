package test.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ubuntudo.controller.PartyController;
import ubuntudo.model.PartyEntity;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/ubuntudo-servlet.xml")
public class PartyControllerTest {

	@Autowired
	private PartyController partyController;

	@Test
	public void insertPartyControllerTest() {
		long gid = 1l;
		long leaderId = 1l;
		String partyName = "1s party guild 1 party 1";
		assertEquals(2, partyController.insertNewPartyController(gid, leaderId, partyName));

		gid = 1l;
		leaderId = 1l;
		partyName = "1s party guild 1 party 2";
		assertEquals(2, partyController.insertNewPartyController(gid, leaderId, partyName));

		gid = 2l;
		leaderId = 1l;
		partyName = "1s party guild 2 party 3";
		assertEquals(2, partyController.insertNewPartyController(gid, leaderId, partyName));

		gid = 2l;
		leaderId = 1l;
		partyName = "1s party guild 2 party 4";
		assertEquals(2, partyController.insertNewPartyController(gid, leaderId, partyName));
		
		gid = 3l;
		leaderId = 1l;
		partyName = "1s party guild 3 party 5";
		assertEquals(2, partyController.insertNewPartyController(gid, leaderId, partyName));
		
		gid = 3l;
		leaderId = 1l;
		partyName = "1s party guild 3 party 6";
		assertEquals(2, partyController.insertNewPartyController(gid, leaderId, partyName));

		gid = 4l;
		leaderId = 2l;
		partyName = "2s party guild 4 party 7";
		assertEquals(2, partyController.insertNewPartyController(gid, leaderId, partyName));
		
		gid = 4l;
		leaderId = 2l;
		partyName = "2s party guild 4 party 8";
		assertEquals(2, partyController.insertNewPartyController(gid, leaderId, partyName));
		
		gid = 5l;
		leaderId = 2l;
		partyName = "2s party guild 5 party 9";
		assertEquals(2, partyController.insertNewPartyController(gid, leaderId, partyName));
		
		gid = 5l;
		leaderId = 2l;
		partyName = "2s party guild 5 party 10";
		assertEquals(2, partyController.insertNewPartyController(gid, leaderId, partyName));
		
		gid = 6l;
		leaderId = 2l;
		partyName = "2s party guild 6 party 11";
		assertEquals(2, partyController.insertNewPartyController(gid, leaderId, partyName));
		
		gid = 6l;
		leaderId = 2l;
		partyName = "2s party guild 6 party 12";
		assertEquals(2, partyController.insertNewPartyController(gid, leaderId, partyName));
	}

	@Test
	public void insertUserToPartyControllerTest() {
		long partyId = 1l;
		long userId = 1l;
		assertEquals(1, partyController.insertUserToPartyController(partyId, userId));
	}

	@Test
	public void retrievePartyListSearchTest() {
		String partyName = "";
		List<PartyEntity> partyList = partyController.retrievePartyListSearchController(partyName);
		assertNotNull(partyList);

		Gson gson = new Gson();
		String partyListJson = gson.toJson(partyList);
		System.out.println(partyListJson);
	}

	@Test
	public void updatePartyControllerTest() {
		long pid = 3l;
		long leaderId = 105l;
		String partyName = "Mark Party EDITED";
		String status = "1";

		assertEquals(1, partyController.updatePartyController(pid, leaderId, partyName, status));
	}

	@Test
	public void retrievePartyInGuildTest() {
		long gid = -1l;
		List<PartyEntity> partyList = partyController.retrievePartyInGuildListController(gid);
		assertNotNull(partyList);

		Gson gson = new Gson();
		String partyListJson = gson.toJson(partyList);
		System.out.println(partyListJson);
	}
}