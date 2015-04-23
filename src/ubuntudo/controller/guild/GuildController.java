package ubuntudo.controller.guild;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/guild")
public class GuildController {
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public int insertGuildController(@RequestParam("leaderId") String leaderId, @RequestParam("guildName") String guildName) {

		return 0;
	}
}
