package ubuntudo.controller.guild;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ubuntudo.biz.GuildBiz;
import ubuntudo.model.GuildEntity;

@Controller
@RequestMapping(value = "/guild")
public class GuildController {
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public int insertGuildController(@RequestParam("leaderId") long leaderId, @RequestParam("guildName") String guildName) {
		GuildBiz pbiz = new GuildBiz();
		return pbiz.insertGuildBiz(new GuildEntity(leaderId, guildName));
	}
}