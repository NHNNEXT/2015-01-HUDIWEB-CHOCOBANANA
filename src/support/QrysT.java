package support;

public class QrysT extends Qrys {
	// for retrieve todos personal view
	public static String RETRIEVE_TODO_FOR_PERSONAL_VIEW = "SELECT t.tid, t.pid, t.title, t.contents, t.duedate, t.status, t.editer_id, p.p_name FROM todo t INNER JOIN party p ON t.pid = p.pid WHERE tid IN (SELECT tid FROM todo_user_relation WHERE uid = ? AND completed = 'trel00') ORDER BY dueDate";

}
