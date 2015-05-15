package ubuntudo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ubuntudo.biz.GuildBiz;
import ubuntudo.model.GuildEntity;

@Controller
@RequestMapping(value = "/guild")
public class GuildController {

	@Autowired
	GuildBiz gbiz;

	@RequestMapping(method = RequestMethod.POST)
	public int insertNewGuildController(@RequestParam("leaderId") long leaderId, @RequestParam("guildName") String guildName) {
		return gbiz.insertNewGuildBiz(new GuildEntity(leaderId, guildName));
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public int insertUserToGuildController(@RequestParam("guildId") long guildId, @RequestParam("userId") long userId) {
		return gbiz.insertUserToGuildBiz(guildId, userId);
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody List<GuildEntity> retrieveGuildListSearchController(@RequestParam("guildName") String guildName) {
		return gbiz.retrieveGuildListSearchBiz(guildName);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public int updateGuildController(@RequestParam("gid") long gid, @RequestParam("leaderId") long leaderId, @RequestParam("guildName") String guildName,
			@RequestParam("status") String status) {
		return gbiz.updateGuildBiz(new GuildEntity(gid, leaderId, guildName, status));
	}
	
	//@RequestMapping(method = RequestMethod.GET)

}