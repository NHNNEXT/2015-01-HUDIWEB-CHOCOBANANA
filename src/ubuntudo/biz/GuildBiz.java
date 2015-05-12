package ubuntudo.biz;

import ubuntudo.model.GuildEntity;

public interface GuildBiz {

	void createGuild(GuildEntity guild);

	void joinLeader(long guildId, long leaderId);

	long lastGuildId();

}