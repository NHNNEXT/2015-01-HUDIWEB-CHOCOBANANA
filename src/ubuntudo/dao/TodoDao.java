package ubuntudo.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import ubuntudo.JDBCManager;
import ubuntudo.model.TodoEntity;

public class TodoDao extends JDBCManager{

	public ArrayList<TodoEntity> getPersonalTodos(Long uid) {
		conn = getConnection();
		ArrayList<TodoEntity> todos = new ArrayList<TodoEntity>();
		
		try {
			pstmt = conn.prepareStatement("SELECT ALL FROM todo WHERE tid=(SELECT tid FROM todo-user-relation WHERE uid = ? AND completed = 0) ORDER BY dueDate");
			pstmt.setLong(1, uid);
			resultSet = pstmt.executeQuery();
			System.out.println(resultSet.toString());
			if (resultSet.next()) {
				TodoEntity todo = new TodoEntity(resultSet.getLong("tid"),
						resultSet.getLong("assigner_id"),
						resultSet.getLong("pid"),
						resultSet.getString("title"),
						resultSet.getString("contents"),
						resultSet.getDate("dueDate"),
						resultSet.getInt("compeleted"),
						resultSet.getLong("last_editer_id"));
				todos.add(todo);
		
			}
		} catch (SQLException e) {
			System.out.println("DB getPersonalTodos Error: " + e.getMessage());
		} finally {
			close(resultSet, pstmt, conn);
		}
		return todos;
	}

}
