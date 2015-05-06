package ubuntudo.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ubuntudo.dao.TodoDao;
import ubuntudo.model.TodoEntity;

@Service("tdao")
public class TodoBiz {
	private static final Logger logger = LoggerFactory.getLogger(TodoBiz.class);

	@Autowired
	private TodoDao tdao;

	@Autowired
	private DataSourceTransactionManager transactionManager;

	public int updatePersonalTodoBiz(TodoEntity todo) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("example-transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transactionManager.getTransaction(def);

		int updatePersonalTodoResult = 0;
		int createPersonalTodoHistoryResult = 0;

		// 1. 요청한 todo를 update
		if ((updatePersonalTodoResult = tdao.updatePersonalTodoDao(todo)) != 1) {
			logger.debug("updatePersonalTodoResult rollback!!");
			transactionManager.rollback(status);
			return -1;
		}
		logger.debug("updatePersonalTodoResult: " + updatePersonalTodoResult);

		// 2. 방금의 update 작업에 대한 이력 생성
		if ((createPersonalTodoHistoryResult = tdao.createPersonalTodoHistoryDao(todo)) != 1) {
			logger.debug("createUpdatePersonalTodoHistoryResult rollback!!");
			transactionManager.rollback(status);
			return -1;
		}
		logger.debug("createPersonalTodoHistoryResult: " + createPersonalTodoHistoryResult);
		transactionManager.commit(status);
		return updatePersonalTodoResult + createPersonalTodoHistoryResult;
	}

	public int deletePersonalTodoBiz(TodoEntity todo) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("example-transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transactionManager.getTransaction(def);

		int deletePersonalTodoResult = 0;
		int createPersonalTodoHistoryResult = 0;

		// 1. 요청한 todo를 delete
		if ((deletePersonalTodoResult = tdao.deletePersonalTodoDao(todo)) != 1) {
			logger.debug("deletePersonalTodoResult rollback!!");
			transactionManager.rollback(status);
			return -1;
		}
		logger.debug("deletePersonalTodoResult: " + deletePersonalTodoResult);

		// 2. 방금의 delete 작업에 대한 이력 생성
		if ((createPersonalTodoHistoryResult = tdao.createPersonalTodoHistoryDao(todo)) != 1) {
			logger.debug("createUpdatePersonalTodoHistoryResult rollback!!");
			transactionManager.rollback(status);
			return -1;
		}
		logger.debug("createPersonalTodoHistoryResult: " + createPersonalTodoHistoryResult);
		transactionManager.commit(status);

		return deletePersonalTodoResult + createPersonalTodoHistoryResult;
	}
}