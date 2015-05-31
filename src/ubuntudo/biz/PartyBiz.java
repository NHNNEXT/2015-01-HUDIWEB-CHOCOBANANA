package ubuntudo.biz;

import java.util.ArrayList;
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
import ubuntudo.model.PartyInfo;
import ubuntudo.model.TodoEntity;
import ubuntudo.model.UserEntity;

@Service
public class PartyBiz {
	private static final Logger logger = LoggerFactory.getLogger(PartyEntity.class);

	@Autowired
	private PartyDao pdao;

	@Autowired
	private DataSourceTransactionManager transactionManager;


	public Long insertNewPartyBiz(PartyEntity party) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("example-transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transactionManager.getTransaction(def);

		int insertPartyResult = 0;
		int insertUserToPartyResult = 0;
		Long newPartyId;
		// 1. 요청한 데이터를 이용해 파티를 생성
		if ((insertPartyResult = pdao.insertPartyDao(party)) != 1) {
			transactionManager.rollback(status);
			return new Long(-1);
		}
		logger.debug("insertPartyResult: " + insertPartyResult);

		// 2. 파티 생성이 완료되면 생성한 길드의 pid를 가져옴
		if ((newPartyId = pdao.getLastPartyId()) < 1) {
			transactionManager.rollback(status);
			return new Long(-1);
		}
		logger.debug("newPartyId: " + newPartyId);

		// 3. 생성한 파티의 pid로 현재 사용자를 파티에 가입시킴
		if ((insertUserToPartyResult = pdao.insertUserToNewPartyDao(party.getLeaderId())) != 1) {

			transactionManager.rollback(status);
			return new Long(-1);
		}
		logger.debug("insertUserToPartyResult: " + insertUserToPartyResult);
		transactionManager.commit(status);
		
		return newPartyId;
	}

	public PartyEntity insertUserToExistingPartyBiz(long partyId, long userId) {
		int result = pdao.insertUserToExistingPartyDao(partyId, userId);
		if(result < 1){
			return null;
		}
		//user 추가가 정상완료되었을 때 파티의 투두를 새로가입한 user에게 할당한다
		pdao.assignPartyTodosToUser(userId, partyId);
		
		//가입한 파티의 정보를 반환한다.
		return pdao.getParty(partyId);
	}
	
	public int isUserSignUpToGuild (long partyId, long userId) {
		return pdao.isUserSignUpToGuild(userId, partyId);
	}

	public List<PartyEntity> retrievePartyListSearchBiz(String partyName) {
		return pdao.retrievePartyListSearchDao(partyName);
	}

	public int updatePartyBiz(PartyEntity partyEntity) {
		return pdao.updatePartyDao(partyEntity);
	}

	public List<Map<String, Object>> retrievePartyInGuildListBiz(long uid, long gid) {
		return pdao.retrievePartyInGuildDao(uid, gid);
	}

	public List<Map<String, Object>> retrievePartyListOfMyGuildsBiz(long uid) {
		return pdao.retrievePartyListOfMyGuildsDao(uid);
	}

	public PartyInfo getPartyInfo(Long uid, Long pid) {
		logger.debug("uid: {}", uid);
		String partyName = pdao.getPartyName(pid);
		String guildName = pdao.getGuildName(pid);
		Integer memberNum = pdao.getMemberNum(pid);
		Integer todoNum = pdao.getTodoNum(pid);
		Float completeRatio = getCompleteRatio(pid);
		ArrayList<UserEntity> topUserList = pdao.getTopUserList(pid);
		Integer isSignUp = pdao.isUserSignUp(uid, pid);
		logger.debug("isSignUp: {}", isSignUp);
		return new PartyInfo(partyName, guildName, memberNum, todoNum, completeRatio, topUserList, isSignUp);
	}

	private Float getCompleteRatio(Long pid) {
		Float ratio = pdao.getComplteRatio(pid);
		return ratio;
	}

	public ArrayList<TodoEntity> getPartyTodos(Long pid) {
		return pdao.getPartyTodos(pid);
	}

	public List<Map<String, Object>> getPartyListOfUser(Long uid) {
		return pdao.getPartyListOfUser(uid);
	}
}