package ubuntudo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import support.QrysT;
import ubuntudo.JDBCManager;
import ubuntudo.model.TodoEntity;
import ubuntudo.model.TodoUserRelationEntity;

public class TodoDao extends JDBCManager {
	private static final Logger logger = LoggerFactory.getLogger(TodoDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		DatabasePopulatorUtils.execute(populator, jdbcTemplate.getDataSource());
	}

	public ArrayList<TodoEntity> getPersonalTodos(Long uid) {
		logger.debug("getPersonalTodos");
		ArrayList<TodoEntity> todos = new ArrayList<TodoEntity>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(QrysT.RETRIEVE_TODO_FOR_PERSONAL_VIEW);
		for (Map<String, Object> row : rows) {
			TodoEntity todo = new TodoEntity((Long)row.get("tid"), (Long)row.get("pid"), (String)row.get("title"), (String)row.get("contents"),
					(String)row.get("duedate"), (String)row.get("status"), (Long)row.get("editer_id"), (String)row.get("p_name"));
			todos.add(todo);
		}
		return todos;
	}

	public TodoEntity addPersonalTodo(TodoEntity todo) {
		TodoEntity result = null;
		Long tid = null;

		conn = getConnection();
		// 아래는 트랜잭션으로 이뤄져야할 부분
		try {
			logger.debug("transaction start");
			String insertTodoSql = "INSERT INTO todo VALUES (null,?,?,?,?,'todo01',?)";
			String lastTodoIdSql = "SELECT LAST_INSERT_ID() tid";
			String getLastTodoSql = "SELECT t.tid, t.pid, t.title, t.contents, t.duedate, t.status, t.editer_id, p.p_name FROM todo t INNER JOIN party p ON t.pid = p.pid WHERE tid = ?";
			String insertHistorySql = "INSERT INTO content_history VALUES(null,?,?,?,?,?, now(), ?,?)";
			String insertRelationSql = "INSERT INTO todo_user_relation VALUES(?,?,'trel00')";

			pstmt = conn.prepareStatement(insertTodoSql);
			pstmt.setLong(1, todo.getPid());
			pstmt.setString(2, todo.getTitle());
			pstmt.setString(3, todo.getContents());
			pstmt.setString(4, todo.getDueDate());
			pstmt.setLong(5, todo.getEditerId());
			pstmt.executeUpdate();
			logger.debug("todo add completed");

			pstmt = conn.prepareStatement(lastTodoIdSql);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				tid = resultSet.getLong("tid");
			}
			logger.debug("last todo id retreived: tid={}", tid);

			pstmt = conn.prepareStatement(getLastTodoSql);
			pstmt.setLong(1, tid);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				result = new TodoEntity(resultSet.getLong("tid"), resultSet.getLong("pid"), resultSet.getString("title"), resultSet.getString("contents"),
						resultSet.getString("duedate"), resultSet.getString("status"), resultSet.getLong("editer_id"), resultSet.getString("p_name"));
			}
			logger.debug("todo retreived.");

			pstmt = conn.prepareStatement(insertHistorySql);
			pstmt.setLong(1, tid);
			pstmt.setLong(2, result.getPid());
			pstmt.setString(3, result.getTitle());
			pstmt.setString(4, result.getContents());
			pstmt.setString(5, result.getDueDate());
			pstmt.setString(6, result.getStatus());
			pstmt.setLong(7, result.getEditerId());
			pstmt.executeUpdate();
			logger.debug("todo history added");

			pstmt = conn.prepareStatement(insertRelationSql);
			pstmt.setLong(1, tid);
			pstmt.setLong(2, result.getEditerId());
			pstmt.executeUpdate();
			logger.debug("todo user relation added");

		} catch (SQLException e) {
			logger.info("DB getPersonalTodos Error: " + e.getMessage());
		} finally {
			close(resultSet, pstmt, conn);
		}
		return result;
	}

	public boolean complete(TodoUserRelationEntity info) {
		logger.debug("complete()");		
		return (jdbcTemplate.update(QrysT.COMPLETE_TODO, info.getTid(), info.getUid()) == 1);
	}

	public int updatePersonalTodoDao(TodoEntity todo) {
		logger.debug("updating: " + todo.toString());
		logger.debug("executing: " + QrysT.UPDATE_PERSONAL_TODO);
		return jdbcTemplate.update(QrysT.UPDATE_PERSONAL_TODO, todo.getTitle(), todo.getContents(), todo.getDueDate(), todo.getEditerId(), todo.getTid());
	}

	public int createPersonalTodoHistoryDao(TodoEntity todo) {
		logger.debug("creating history: " + todo.getTid());
		logger.debug("executing: " + QrysT.CREATE_PERSONAL_TODO_HISTORY);
		return jdbcTemplate.update(QrysT.CREATE_PERSONAL_TODO_HISTORY, todo.getTid());
	}

	public int deletePersonalTodoDao(TodoEntity todo) {
		logger.debug("deleting: " + todo.toString());
		logger.debug("executing: " + QrysT.DELETE_PERSONAL_TODO);
		return jdbcTemplate.update(QrysT.DELETE_PERSONAL_TODO, todo.getTid());
	}

}