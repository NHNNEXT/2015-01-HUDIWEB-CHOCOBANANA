package ubuntudo.biz;

import org.springframework.beans.factory.annotation.Autowired;

import ubuntudo.dao.GuildDao;
import ubuntudo.model.GuildEntity;

public class GuildBiz {
	
	@Autowired
	private GuildDao gdao;
	
	public int insertGuildBiz(GuildEntity guild) {
		return gdao.insertGuildDao(guild);
	}
}