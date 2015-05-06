package ubuntudo.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import ubuntudo.dao.TodoDao;
import ubuntudo.model.TodoEntity;

@Service("tdao")
public class TodoBiz {

	@Autowired
	private TodoDao tdao;

	@Autowired
	private DataSourceTransactionManager transactionManager;

	public int updateTodoBiz(TodoEntity todo) {
		return tdao.updateTodoDao(todo);
	}
}