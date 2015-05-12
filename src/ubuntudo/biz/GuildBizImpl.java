package ubuntudo.biz;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ubuntudo.dao.GuildDao;
import ubuntudo.model.GuildEntity;

@Service
public class GuildBizImpl implements GuildBiz {
	private static final Logger logger = LoggerFactory.getLogger(GuildBizImpl.class);

	@Autowired
	private GuildDao gdao;

	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	public GuildBizImpl() {
		
	}

	/*
	 * 
	 * insertNewGuildBiz 역할을 정리하면 아래와 같습니다. 
	 * 1. guild 생성
	 * 2. 생성시 마지막 길드 아이디 가져오기
	 * 3. 생성된 길드에 리더 가입
	 *  
	 * createGuild(GuildEntity guild)로 재구성해보겠습니다.
	 * @see ubuntudo.biz.GuildTransactionBiz
	 */
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

	@Override
	@Transactional
	public void createGuild(GuildEntity guild) {
		
		if (gdao.insertNewGuildDao(guild) != 1) {
			throw new IllegalStateException("guild 생성 오류");
		}
	}

	@Override
	@Transactional
	public void joinLeader(long guildId, long leaderId) {
		
		if (gdao.insertUserToGuildDao(guildId, leaderId) != 1) {
			throw new IllegalStateException("guild 리더 저장 오류");
		}
	}

	@Override
	@Transactional
	public long lastGuildId() {
		long lastGuildId = gdao.getLastGuildId();
		
		if (lastGuildId < 1) {
			throw new IllegalStateException("guild lastId 오류");
		}
		
		return lastGuildId;
	}
}