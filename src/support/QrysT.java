package support;

public class QrysT extends Qrys {
	// for retrieve todos personal view
	public static String RETRIEVE_TODO_FOR_PERSONAL_VIEW = "SELECT t.tid, t.pid, t.title, t.contents, t.duedate, t.status, t.editer_id, p.p_name FROM todo t INNER JOIN party p ON t.pid = p.pid WHERE tid IN (SELECT tid FROM todo_user_relation WHERE uid = ? AND completed = 'trel00') AND t.status <> 'todo03' AND t.status <> 'todo04' ORDER BY dueDate";

	// for update personal todo
	public static String UPDATE_PERSONAL_TODO = "update todo set title = ?, contents = ?, duedate = ?, status = 'todo02', editer_id = ? where tid = ?";
	
	// for create update history for personal todo
	public static String CREATE_PERSONAL_TODO_HISTORY = "insert into content_history (tid, pid, title, contents, duedate, status, editer_id) select tid, pid, title, contents, duedate, status, editer_id from todo where tid = ?";
	
	// for delete personal todo
	public static String DELETE_PERSONAL_TODO = "update todo set status = 'todo03' where tid = ?";
	
	public static String COMPLETE_TODO = "update todo_user_relation SET completed = 'trel01' WHERE tid = ? AND uid = ?";

	public static String INSERT_TODO = "INSERT INTO todo VALUES (null,?,?,?,?,'todo01',?)";
	public static String GET_LAST_INSERTED_TODO_ID = "SELECT LAST_INSERT_ID() tid";
	public static String GET_LAST_TODO = "SELECT t.tid, t.pid, t.title, t.contents, t.duedate, t.status, t.editer_id, p.p_name FROM todo t INNER JOIN party p ON t.pid = p.pid WHERE tid = ?";
	public static String INSERT_HISTORY = "INSERT INTO content_history VALUES(null,?,?,?,?,?, now(), ?,?)";
	public static String INSERT_RELATION = "INSERT INTO todo_user_relation VALUES(?,?,'trel00')";
}
