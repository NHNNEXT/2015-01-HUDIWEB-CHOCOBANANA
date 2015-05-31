package ubuntudo.biz;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ubuntudo.dao.GuildDao;
import ubuntudo.dao.PartyDao;
import ubuntudo.model.GuildEntity;
import core.utils.DateUtil;

@Service
public class GuildBiz {
	private static final Logger logger = LoggerFactory.getLogger(GuildBiz.class);

	@Autowired
	private GuildDao gdao;

	@Autowired
	private PartyDao pdao;

	@Autowired
	private DataSourceTransactionManager transactionManager;

	public GuildBiz() {

	}

	public int insertNewGuildBiz(GuildEntity guild) {

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("example-transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transactionManager.getTransaction(def);

		int insertGuildResult = 0;
		long newGuildId = 0;
		int insertUserToGuildResult = 0;
		// 1. 요청한 데이터를 이용해 길드를 생성
		if ((insertGuildResult = gdao.insertNewGuildDao(guild)) != 1) {
			transactionManager.rollback(status);
			return -1;
		}
		logger.debug("insertGuildResult: " + insertGuildResult);

		// 2. 길드 생성이 완료되면 생성한 길드의 gid를 가져옴
		if ((newGuildId = gdao.getLastGuildId()) < 1) {
			transactionManager.rollback(status);
			return -1;
		}
		logger.debug("newGuildId: " + newGuildId);

		// 3. 생성한 길드의 gid로 현재 사용자를 길드에 가입시킴
		if ((insertUserToGuildResult = gdao.insertUserToGuildDao(newGuildId, guild.getLeaderId())) != 1) {
			transactionManager.rollback(status);
			return -1;
		}
		logger.debug("insertUserToGuildResult: " + insertUserToGuildResult);
		transactionManager.commit(status);
		return insertGuildResult + insertUserToGuildResult;
	}

	public int insertUserToGuildBiz(long guildId, long userId) {
		return gdao.insertUserToGuildDao(guildId, userId);
	}

	public List<GuildEntity> retrieveGuildListSearchBiz(String guildName) {
		return gdao.retrieveGuildListSearchDao(guildName);
	}

	public int updateGuildBiz(GuildEntity guildEntity) {
		return gdao.updateGuildDao(guildEntity);
	}

	public List<Map<String, Object>> retrieveMyGuildListBiz(long uid) {
		return gdao.retrieveMyGuildListDao(uid);
	}

	public String retrieveGuildDetailAndPartyListBiz(long uid, long gid) {
		String guildDetail = DateUtil.gson.toJson(gdao.retrieveGuildDetail(uid, gid));
		String partyListInGuild = DateUtil.gson.toJson(pdao.retrievePartyInGuildDao(uid, gid));
		return "{\"result\":{\"guildDetail\":" + guildDetail + ",\"parties\":" + partyListInGuild	+ "}}";
	}
}