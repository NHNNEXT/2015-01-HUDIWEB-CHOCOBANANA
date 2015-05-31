package ubuntudo.controller;

import java.util.ArrayList;
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

import ubuntudo.biz.PartyBiz;
import ubuntudo.model.PartyEntity;
import ubuntudo.model.TodoEntity;
import ubuntudo.model.UserEntity;

@Controller
@RequestMapping(value = "/party")
public class PartyController {
	private static final Logger logger = LoggerFactory.getLogger(PartyController.class);

	@Autowired
	PartyBiz pbiz;

	@RequestMapping(value = "/{pid}", method = RequestMethod.GET)
	public String getPartyPage(HttpSession session, @PathVariable("pid") Long pid, Model model) {
		UserEntity user = (UserEntity) session.getAttribute("user");
		model.addAttribute("partyInfo", pbiz.getPartyInfo(user.getUid(), pid));
		return "party";
	}
	
	@RequestMapping(value = "/todo/{pid}", method = RequestMethod.GET)
	public @ResponseBody ArrayList<TodoEntity> getPersonalTodos(@PathVariable("pid") Long pid) {
		logger.debug("/party/todo 에 대한 응답");
		return pbiz.getPartyTodos(pid);
	}
	
	//유저가 가입한 party list - personal, guildOverview, guild, party 페이지 모두에서 이 컨트롤러를 부른다. 
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getPartyListOfUserController(HttpSession session) {
		UserEntity user = (UserEntity) session.getAttribute("user");
		Long uid = user.getUid();
		return pbiz.getPartyListOfUser(uid);
	}

	// creates a party.
	// also add the creator to the party which is just created.
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelMap insertNewPartyController(@RequestParam("gid") long gid, HttpSession session, @RequestParam("partyName") String partyName) {
		UserEntity user = (UserEntity) session.getAttribute("user");
		Long uid = user.getUid();
		Long newPid = pbiz.insertNewPartyBiz(new PartyEntity(gid, uid, partyName));
		ModelMap model = new ModelMap();
		if(newPid > 0) {
			model.addAttribute("status", "success");
			model.addAttribute("newPid", newPid);
			return model;
		}
		model.addAttribute("status", "fail");
		return model;
	}
	
	// add a user to a party.
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public @ResponseBody ModelMap insertUserToPartyController(@RequestParam("pid") long pid, HttpSession session) {
		UserEntity user = (UserEntity) session.getAttribute("user");
		Long uid = user.getUid();
		ModelMap model = new ModelMap();
		//길드에 가입하지 않은 경우 
		if(pbiz.isUserSignUpToGuild(pid, uid) != 1) {
			model.addAttribute("status", "fail");
			model.addAttribute("errorMessage", "길드에 가입하셔야 파티에 가입하실 수 있습니다.");
			return model;
		}
		
		//파티에 가입시키다 오류가 나는 경우
		PartyEntity party;
		if((party = pbiz.insertUserToExistingPartyBiz(pid, uid)) == null) {
			model.addAttribute("status", "fail");
			model.addAttribute("errorMessage", "파티에 가입하는 중 서버에 에러가 발생했습니다.");
			return model;
		}
		
		//정상적인 경우 
		model.addAttribute("status", "success");
		model.addAttribute("party", party);
		return model;
	}

	//retrieves a list of parties containing the parameter in the name of the party.
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public List<PartyEntity> retrievePartyListSearchController(@RequestParam("partyName") String partyName) {
		return pbiz.retrievePartyListSearchBiz(partyName);
	}

	// update the information of a party.
	@RequestMapping(method = RequestMethod.PUT)
	public int updatePartyController(@RequestParam("pid") long pid, @RequestParam("leaderId") long leaderId, @RequestParam("partyName") String partyName,
			@RequestParam("status") String status) {
		return pbiz.updatePartyBiz(new PartyEntity(pid, leaderId, partyName, status));
	}

	// 나중에 다시 uri 생각해보기
	// retrieves a list of parties that are contained in a guild.
	@RequestMapping(value = "/guild", method = RequestMethod.GET)
	public List<Map<String, Object>> retrievePartyInGuildListController(@RequestParam("uid") long uid, @RequestParam("gid") long gid) {
		return pbiz.retrievePartyInGuildListBiz(uid, gid);
	}
	
	// retrieve a list of all the parties that are included in the every guild of particular user.
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> retrieveMyPartyListController(@RequestParam("uid") long uid) {
		return pbiz.retrievePartyListOfMyGuildsBiz(uid);
	}
}