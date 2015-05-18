package ubuntudo.controller;

import java.util.List;
import java.util.Map;

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

import ubuntudo.biz.GuildBiz;
import ubuntudo.biz.PartyBiz;
import ubuntudo.model.GuildEntity;

import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/guild")
public class GuildController {

	private static final Logger logger = LoggerFactory.getLogger(GuildController.class);
	Gson gson = new Gson();

	@Autowired
	GuildBiz gbiz;

	@Autowired
	PartyBiz pbiz;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String execute(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			logger.debug("/guild 요청에 대해 응답 - 세션이 정상적이지 않을때");
			return "redirect:/start";
		}
		logger.debug("/guild 요청에 대해 응답");

		return "guild";
	}

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

	// retrieve a list of all the guild of particular user
	@RequestMapping(value = "/retrieveMyGuild", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> retrieveMyGuildListController(@RequestParam("uid") long uid) {
		return gbiz.retrieveMyGuildListBiz(uid);
	}

	// retrieve a list of all the guild of particular user
	@RequestMapping(value = "/retrieveMyGuildAndParty", method = RequestMethod.POST)
	public @ResponseBody String retrieveMyGuildAndPartyListController(@RequestParam("uid") long uid) {
		return "{\"result\":{\"guilds\":" + gson.toJson(gbiz.retrieveMyGuildListBiz(uid)) + ",\"parties\":" + gson.toJson(pbiz.retrievePartyListOfMyGuildsBiz(uid))	+ "}}";
	}

	// @RequestMapping(value = "", method = RequestMethod.GET)

	// guild/overview GET - html
	// guild/overview/data GET - json

}