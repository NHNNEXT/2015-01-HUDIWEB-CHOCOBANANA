package ubuntudo.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ubuntudo.JDBCManager;
import ubuntudo.model.TodoEntity;
import ubuntudo.model.TodoUserRelationEntity;

public class TodoDao extends JDBCManager{
	private static final Logger logger = LoggerFactory.getLogger(TodoDao.class);

	public ArrayList<TodoEntity> getPersonalTodos(Long uid) {
		conn = getConnection();
		ArrayList<TodoEntity> todos = new ArrayList<TodoEntity>();
		
		try {
			String sql = "SELECT t.tid, t.pid, t.title, t.contents, t.duedate, t.status, t.editer_id, p.p_name FROM todo t INNER JOIN party p ON t.pid = p.pid WHERE tid IN (SELECT tid FROM todo_user_relation WHERE uid = ? AND completed = 'trel00') ORDER BY dueDate";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, uid);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				TodoEntity todo = new TodoEntity(resultSet.getLong("tid"),
						resultSet.getLong("pid"),
						resultSet.getString("title"),
						resultSet.getString("contents"),
						resultSet.getDate("duedate"),
						resultSet.getString("status"),
						resultSet.getLong("editer_id"),
						resultSet.getString("p_name"));
				todos.add(todo);
			}
		} catch (SQLException e) {
			logger.info("DB getPersonalTodos Error: " + e.getMessage());
		} finally {
			close(resultSet, pstmt, conn);
		}
		return todos;
	}
	
	public TodoEntity addPersonalTodo(TodoEntity todo) {
		TodoEntity result = null;
		Long tid = null;

		conn = getConnection();
		//아래는 트랜잭션으로 이뤄져야할 부분
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
			pstmt.setDate(4, todo.getDueDate());
			pstmt.setLong(5, todo.getEditerId());
			pstmt.executeUpdate();
			logger.debug("todo add completed");

			pstmt = conn.prepareStatement(lastTodoIdSql);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
					tid = resultSet.getLong("tid");		
			}
			logger.debug("last todo id retreived. tid: {}", tid);

			pstmt = conn.prepareStatement(getLastTodoSql);
			pstmt.setLong(1, tid);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				result = new TodoEntity(resultSet.getLong("tid"),
						resultSet.getLong("pid"),
						resultSet.getString("title"),
						resultSet.getString("contents"),
						resultSet.getDate("duedate"),
						resultSet.getString("status"),
						resultSet.getLong("editer_id"),
						resultSet.getString("p_name"));
			}
			logger.debug("todo retreived.");

			pstmt = conn.prepareStatement(insertHistorySql);
			pstmt.setLong(1, tid);
			pstmt.setLong(2, result.getPid());
			pstmt.setString(3, result.getTitle());
			pstmt.setString(4, result.getContents());
			pstmt.setDate(5, result.getDueDate());
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
		conn = getConnection();
		boolean result = false;
		
		try {
			String sql = "update todo_user_relation SET completed = 'trel01' WHERE tid = ? AND uid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, info.getTid());
			pstmt.setLong(2, info.getUid());
			pstmt.executeUpdate();
			result = true;
		} catch (SQLException e) {
			logger.info("DB getPersonalTodos Error: " + e.getMessage());
		} finally {
			close(resultSet, pstmt, conn);
		}
		return result;
	}

}
