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

import ubuntudo.dao.PartyDao;
import ubuntudo.model.PartyEntity;

@Service
public class PartyBiz {
	private static final Logger logger = LoggerFactory.getLogger(PartyEntity.class);

	@Autowired
	private PartyDao pdao;

	@Autowired
	private DataSourceTransactionManager transactionManager;

	public int insertPartyBiz(PartyEntity party) {

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("example-transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transactionManager.getTransaction(def);

		int insertPartyResult = 0;
		long newPartyId = 0;
		int insertUserToPartyResult = 0;

		// 1. 요청한 데이터를 이용해 파티를 생성
		if ((insertPartyResult = pdao.insertPartyDao(party)) != 1) {
			transactionManager.rollback(status);
			return -1;
		}
		logger.debug("insertPartyResult: " + insertPartyResult);

		// 2. 파티 생성이 완료되면 생성한 길드의 pid를 가져옴
		if ((newPartyId = pdao.getLastPartyId()) < 1) {
			transactionManager.rollback(status);
			return -1;
		}
		logger.debug("newPartyId: " + newPartyId);

		// 3. 생성한 파티의 pid로 현재 사용자를 파티에 가입시킴
		if ((insertUserToPartyResult = pdao.insertUserToPartyDao(newPartyId, party.getLeaderId())) != 1) {
			transactionManager.rollback(status);
			return -1;
		}
		logger.debug("insertUserToPartyResult: " + insertUserToPartyResult);
		transactionManager.commit(status);
		
		return insertPartyResult + insertUserToPartyResult;
	}

	public int insertUserToPartyBiz(long partyId, long userId) {
		return pdao.insertUserToPartyDao(partyId, userId);
	}

	public List<PartyEntity> retrievePartyListSearchBiz(String partyName) {
		return pdao.retrievePartyListSearchDao(partyName);
	}

	public int updatePartyBiz(PartyEntity partyEntity) {
		return pdao.updatePartyDao(partyEntity);
	}

	public List<PartyEntity> retrievePartyInGuildListBiz(long gid) {
		return pdao.retrievePartyInGuildListDao(gid);
	}

	public List<Map<String, Object>> retrievePartyListOfMyGuildsBiz(long uid) {
		return pdao.retrievePartyListOfMyGuildsDao(uid);
	}
}