package ubuntudo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ubuntudo.biz.GuildBiz;
import ubuntudo.biz.PartyBiz;
import ubuntudo.model.GuildEntity;
import ubuntudo.model.UserEntity;

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
	
	@RequestMapping(value = "/overview", method = RequestMethod.GET)
	public String executeOverview(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			logger.debug("/guildOverview 요청에 대해 응답 - 세션이 정상적이지 않을때");
			return "redirect:/start";
		}
		logger.debug("/guild Overview 요청에 대해 응답");

		return "guildOverview";
	}

	// creates a guild.
	// also add the creator to the guild which is just created.
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelMap insertNewGuildController(HttpSession session, @RequestParam("guildName") String guildName) {
		logger.debug("/guild Create 요청에 대해 응답");
		UserEntity user = (UserEntity) session.getAttribute("user");
		long leaderId = user.getUid();
		ModelMap model = new ModelMap();
		if(gbiz.insertNewGuildBiz(new GuildEntity(leaderId, guildName)) == 2){
			model.addAttribute("status", "success");
			return model;
		}
		model.addAttribute("status", "fail");
		return model;
	}

	// add a user to a guild.
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public  @ResponseBody ModelMap insertUserToGuildController( @RequestParam("guildId") long guildId, HttpSession session) {
		UserEntity user = (UserEntity) session.getAttribute("user");
		Long userId = user.getUid();
		ModelMap model = new ModelMap();
		if(gbiz.insertUserToGuildBiz(guildId, userId) == 1){
			model.addAttribute("status", "success");
			return model;
		}
		model.addAttribute("status", "fail");
		return model;
	}

	// 테스트용
	//@RequestMapping(value = "/user", method = RequestMethod.POST)
	public @ResponseBody int insertUserToGuild( @RequestParam("guildId") long guildId, @RequestParam("userId") long userId) {
		return 1;
	}

	// retrieve a list of guilds containing the parameter in the name of the guild.
	@RequestMapping(value = "/search/{gname}", method = RequestMethod.GET)
	public @ResponseBody List<GuildEntity> retrieveGuildListSearchController( @PathVariable("gname") String guildName) {
		return gbiz.retrieveGuildListSearchBiz(guildName);
	}

	// update the information of a guild.
	@RequestMapping(method = RequestMethod.PUT)
	public int updateGuildController(@RequestParam("gid") long gid, @RequestParam("leaderId") long leaderId,	@RequestParam("guildName") String guildName,	@RequestParam("status") String status) {
		return gbiz.updateGuildBiz(new GuildEntity(gid, leaderId, guildName, status));
	}

	// 길드의 첫페이지
	@RequestMapping(value = "/{gid}", method = RequestMethod.GET)
	public String guild(@PathVariable("gid") Long gid) {
		return "guild";
	}

	// retrieve a list of all the guild of particular user
	@RequestMapping(value = "/retrieveMyGuild", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> retrieveMyGuildListController( @RequestParam("uid") long uid) {
		return gbiz.retrieveMyGuildListBiz(uid);
	}

	// retrieve a list of all the guild of particular user
	// also retrieves a list of all the parties which are contained in the guilds.
	@RequestMapping(value = "/overview/info", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	public @ResponseBody String retrieveMyGuildAndPartyListController(HttpSession session) {
		UserEntity user = (UserEntity)session.getAttribute("user");
		Long uid = user.getUid();
		return "{\"result\":{\"guilds\":" 
				+ gson.toJson(gbiz.retrieveMyGuildListBiz(uid))
				+ ",\"parties\":"
				+ gson.toJson(pbiz.retrievePartyListOfMyGuildsBiz(uid)) + "}}";
	}
	
	// retrieve detail info of a guild and a list of all the party in a particular guild
	@RequestMapping(value = "/info/{gid}", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	public @ResponseBody String retrieveGuildDetailAndPartyListController( HttpSession session, @PathVariable("gid") Long gid) {
		UserEntity user = (UserEntity) session.getAttribute("user");
		Long uid = user.getUid();
		String guildDetailAndPartyList = gbiz.retrieveGuildDetailAndPartyListBiz(uid, gid);
		return guildDetailAndPartyList;
	}

	// @RequestMapping(value = "", method = RequestMethod.GET)

	// guild/overview GET - html
	// guild/overview/data GET - json

}