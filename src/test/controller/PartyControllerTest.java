package test.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ubuntudo.controller.Party.PartyController;
import ubuntudo.model.PartyEntity;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/ubuntudo-servlet.xml")
public class PartyControllerTest {

	@Autowired
	private PartyController partyController;

	@Test
	public void insertPartyControllerTest() {
		long gid = 50l;
		long leaderId = 50l;
		String partyName = "Mark Party50";

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