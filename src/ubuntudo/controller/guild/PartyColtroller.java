package ubuntudo.controller.guild;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/party")
public class PartyColtroller {
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public int insertPartyController(@RequestParam("leader_id") String leader_id, @RequestParam("guild_name") String guild_name){
		
		return 0;
	}
}
