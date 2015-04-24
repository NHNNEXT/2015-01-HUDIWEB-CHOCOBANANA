package test.controller;

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
		long pid = 3l;
		long leaderId = 1l;
		String partyName = "Mark Party100-3";

		partyController.insertPartyController(pid, leaderId, partyName);
	}

	@Test
	public void retrievePartySearchTest() {
		String partyName = "y P";
		List<PartyEntity> partyList = partyController.retrievePartySearchController(partyName);

		System.out.println(partyList.toString());

		Gson gson = new Gson();
		String json = gson.toJson(partyList);
		System.out.println(json);

	}

	@Test
	public void updatePartyControllerTest() {
		long pid = 3l;
		long leaderId = 105l;
		String partyName = "Mark Party EDITED";
		String status = "830408";

		partyController.updatePartyController(pid, leaderId, partyName, status);
	}
}