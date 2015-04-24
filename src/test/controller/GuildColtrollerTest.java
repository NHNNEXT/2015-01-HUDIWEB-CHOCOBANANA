package test.controller;

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
	public void insertGuildControllerTest() {
		guildController.insertGuildController(1l, "The Guild1");
	}
}