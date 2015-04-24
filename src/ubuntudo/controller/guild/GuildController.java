package ubuntudo.controller.guild;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ubuntudo.biz.GuildBiz;
import ubuntudo.model.GuildEntity;

@Controller
@RequestMapping(value = "/guild")
public class GuildController {
	
	@Autowired
	GuildBiz gbiz;
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public int insertNewGuildController(@RequestParam("leaderId") long leaderId, @RequestParam("guildName") String guildName) {
		return gbiz.insertNewGuildBiz(new GuildEntity(leaderId, guildName));
	}

	public int insertUserToGuildController(long guildId, long userId) {
		return gbiz.insertUserToGuildBiz(guildId, userId);
	}

//	public String retrieveGuildAndPartyController(long demanderIdSearch, String guildNameSearch) {
//		return gbiz.retrieveGuildAndPartyBiz(demanderIdSearch, guildNameSearch);
//	}
}