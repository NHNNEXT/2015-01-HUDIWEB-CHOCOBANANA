package test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ubuntudo.controller.Party.PartyController;

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
}