package ubuntudo.controller.guild;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ubuntudo.biz.GuildBizImpl;
import ubuntudo.model.GuildEntity;

@Controller
@RequestMapping(value = "/guild")
public class GuildController {

	@Autowired
	GuildBizImpl gbiz;

	@RequestMapping(value = "/insertNewGuild", method = RequestMethod.POST)
	public int insertNewGuildController(@RequestParam("leaderId") long leaderId, @RequestParam("guildName") String guildName) {
		return gbiz.insertNewGuildBiz(new GuildEntity(leaderId, guildName));
	}

	@RequestMapping(value = "/insertUserToGuild", method = RequestMethod.POST)
	public int insertUserToGuildController(@RequestParam("guildId") long guildId, @RequestParam("userId") long userId) {
		return gbiz.insertUserToGuildBiz(guildId, userId);
	}

	@RequestMapping(value = "/retrieveGuildListSearch", method = RequestMethod.POST)
	public @ResponseBody List<GuildEntity> retrieveGuildListSearchController(@RequestParam("guildName") String guildName) {
		return gbiz.retrieveGuildListSearchBiz(guildName);
	}

	@RequestMapping(value = "/updateGuild", method = RequestMethod.POST)
	public int updateGuildController(@RequestParam("gid") long gid, @RequestParam("leaderId") long leaderId, @RequestParam("guildName") String guildName,
			@RequestParam("status") String status) {
		return gbiz.updateGuildBiz(new GuildEntity(gid, leaderId, guildName, status));
	}
}