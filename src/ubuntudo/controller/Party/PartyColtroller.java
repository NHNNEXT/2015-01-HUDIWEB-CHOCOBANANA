package ubuntudo.controller.Party;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ubuntudo.biz.PartyBiz;
import ubuntudo.model.PartyEntity;

@Controller
@RequestMapping(value = "/party")
public class PartyColtroller {
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public int insertPartyController(@RequestParam("gid") long gid, @RequestParam("leaderId") long leaderId, @RequestParam("partyName") String partyName) {
		PartyBiz pbiz = new PartyBiz();
		return pbiz.insertPartyBiz(new PartyEntity(gid, leaderId, partyName));
	}
}
