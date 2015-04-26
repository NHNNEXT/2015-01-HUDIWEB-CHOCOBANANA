package ubuntudo.controller.Party;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ubuntudo.biz.PartyBiz;
import ubuntudo.model.PartyEntity;

@Controller
@RequestMapping(value = "/party")
public class PartyController {

	@Autowired
	PartyBiz pbiz;

	@RequestMapping(value = "/insertNewParty", method = RequestMethod.POST)
	public int insertPartyController(@RequestParam("gid") long gid, @RequestParam("leaderId") long leaderId, @RequestParam("partyName") String partyName) {
		return pbiz.insertPartyBiz(new PartyEntity(gid, leaderId, partyName));
	}

	@RequestMapping(value = "/retrievePartyListSearch", method = RequestMethod.POST)
	public List<PartyEntity> retrievePartyListSearchController(@RequestParam("partyName") String partyName) {
		return pbiz.retrievePartyListSearchBiz(partyName);
	}

	@RequestMapping(value = "/updateParty", method = RequestMethod.POST)
	public int updatePartyController(@RequestParam("pid") long pid, @RequestParam("leaderId") long leaderId, @RequestParam("partyName") String partyName,
			@RequestParam("status") String status) {
		return pbiz.updatePartyBiz(new PartyEntity(pid, leaderId, partyName, status));
	}
}