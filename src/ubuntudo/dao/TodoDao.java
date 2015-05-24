package ubuntudo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import support.QrysT;
import ubuntudo.model.TodoEntity;
import ubuntudo.model.TodoUserRelationEntity;
import ubuntudo.model.UserEntity;
import java.sql.PreparedStatement;

public class TodoDao {
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
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(QrysT.RETRIEVE_TODO_FOR_PERSONAL_VIEW,
				uid);
		for (Map<String, Object> row : rows) {
			TodoEntity todo = new TodoEntity(Long.valueOf((int) row.get("tid")),
					Long.valueOf((int) row.get("pid")), (String) row.get("title"),
					(String) row.get("contents"), ((Timestamp) row.get("duedate")).toString(),
					(String) row.get("status"), Long.valueOf((int) row.get("editer_id")),
					(String) row.get("p_name"));
			todos.add(todo);
		}
		return todos;
	}

	// add_todo 1st step
	public int insertTodo(TodoEntity todo) {
		logger.debug("insertTodo");
		return jdbcTemplate.update(QrysT.INSERT_TODO, todo.getPid(), todo.getTitle(), todo.getContents(),
				todo.getDueDate(), todo.getEditerId());
	}

	// add_todo 2nd step
	public Long getLastInsertedTodoId() {
		logger.debug("getLastInsertedTodoId");
		RowMapper<Long> rowMapper = new RowMapper<Long>() {
			public Long mapRow(ResultSet rs, int rowNum) {
				try {
					return rs.getLong("tid");
				} catch (SQLException e) {
					logger.info(e.getMessage());
				}
				return null;
			}
		};
		return jdbcTemplate.queryForObject(QrysT.GET_LAST_INSERTED_TODO_ID, rowMapper);
	}

	// add_todo 3rd step
	public TodoEntity getLastTodo(Long tid) {
		logger.debug("getLastTodo");
		RowMapper<TodoEntity> rowMapper = new RowMapper<TodoEntity>() {
			public TodoEntity mapRow(ResultSet resultSet, int rowNum) {
				try {
					return new TodoEntity(resultSet.getLong("tid"), resultSet.getLong("pid"),
							resultSet.getString("title"),
							resultSet.getString("contents"),
							resultSet.getString("duedate"),
							resultSet.getString("status"),
							resultSet.getLong("editer_id"),
							resultSet.getString("p_name"));
				} catch (SQLException e) {
					throw new BeanInstantiationException(TodoEntity.class, e.getMessage(), e);
				}
			}
		};
		return jdbcTemplate.queryForObject(QrysT.GET_LAST_TODO, rowMapper, tid);
	}

	// add_todo 4th step
	public int insertHistory(Long tid, TodoEntity result) {
		logger.debug("insertHistory");
		return jdbcTemplate.update(QrysT.INSERT_HISTORY, tid, result.getPid(), result.getTitle(),
				result.getContents(), result.getDueDate(), result.getStatus(),
				result.getEditerId());
	}

	// add_todo 5th step (personal todo)
	public int insertRelation(Long tid, TodoEntity result) {
		logger.debug("insertRelation");
		return jdbcTemplate.update(QrysT.INSERT_RELATION, tid, result.getEditerId());
	}

	// add_todo 5th step (party_todo)
	public void insertRelation(Long tid, List<UserEntity> users) {
		logger.debug("insert party relation");
		jdbcTemplate.batchUpdate(QrysT.INSERT_RELATION, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				UserEntity user = users.get(i);
				ps.setLong(1, tid);
				ps.setLong(2, user.getUid());
			}

			@Override
			public int getBatchSize() {
				return users.size();
			}
		});
	}
	
	public List<UserEntity> getUserListFromPid(Long pid) {
		ArrayList<UserEntity> users = new ArrayList<UserEntity>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(QrysT.GET_UID_LIST_FROM_PID, pid);
		for (Map<String, Object> row : rows) {
			UserEntity user = new UserEntity(Long.valueOf((int) row.get("uid")));
			users.add(user);
		}
		return users;
	}

	public boolean complete(TodoUserRelationEntity info) {
		logger.debug("complete()");
		return (jdbcTemplate.update(QrysT.COMPLETE_TODO, info.getTid(), info.getUid()) == 1);
	}

	public int updatePersonalTodoDao(TodoEntity todo) {
		logger.debug("updating: " + todo.toString());
		logger.debug("executing: " + QrysT.UPDATE_PERSONAL_TODO);
		return jdbcTemplate.update(QrysT.UPDATE_PERSONAL_TODO, todo.getTitle(), todo.getContents(),
				todo.getDueDate(), todo.getEditerId(), todo.getTid());
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