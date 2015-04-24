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
	public int insertGuildController(@RequestParam("leaderId") long leaderId, @RequestParam("guildName") String guildName) {
		return gbiz.insertGuildBiz(new GuildEntity(leaderId, guildName));
	}
}