package ubuntudo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import core.utils.DateUtil;
import ubuntudo.biz.GuildBiz;
import ubuntudo.controller.PersonalController;
import ubuntudo.model.GuildEntity;

@Controller
@RequestMapping(value = "/guild")
public class GuildController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(GuildController.class);

	@Autowired
	GuildBiz gbiz;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String execute(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			logger.debug("/guild 요청에 대해 응답 - 세션이 정상적이지 않을때");
			return "redirect:/start";
		}
		logger.debug("/guild 요청에 대해 응답");
		
		return "guild";
	}

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