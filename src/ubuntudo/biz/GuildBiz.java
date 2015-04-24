package ubuntudo.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ubuntudo.dao.GuildDao;
import ubuntudo.model.GuildEntity;

public class GuildBiz {
	private static final Logger logger = LoggerFactory.getLogger(GuildBiz.class);

	@Autowired
	private GuildDao gdao;

	public int insertNewGuildBiz(GuildEntity guild) {
		// 1. 요청한 데이터를 이용해 길드를 생성
		int insertGuildResult = gdao.insertNewGuildDao(guild);
		logger.debug("insertGuildResult: " + insertGuildResult);

		// 2. 길드 생성이 완료되면 생성한 길드의 gid를 가져옴
		long newGuildId = gdao.getLastGuildId();
		logger.debug("newGuildId: " + newGuildId);

		// 3. 생성한 길드의 gid로 현재 사용자를 길드에 가입시킴
		// int insertGuildUserResult = insertGuildUserRelationDao()
		int insertUserToGuildResult = gdao.insertUserToGuildDao(newGuildId, guild.getLeaderId());

		return insertUserToGuildResult;
	}

	public int insertUserToGuildBiz(long guildId, long userId) {
		return gdao.insertUserToGuildDao(guildId, userId);
	}

	// public String retrieveGuildAndPartyBiz(long demanderIdSearch, String
	// guildNameSearch) {
	// return gdao.retrieveGuildAndPartyDao(demanderIdSearch, guildNameSearch);
	// }
}