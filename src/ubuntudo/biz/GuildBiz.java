package ubuntudo.biz;

import ubuntudo.dao.GuildDao;
import ubuntudo.model.GuildEntity;

public class GuildBiz {
	public int insertGuildBiz(GuildEntity guildEntity) {
		GuildDao gdao = new GuildDao();
		return gdao.insertGuildDao(guildEntity);
	}
}