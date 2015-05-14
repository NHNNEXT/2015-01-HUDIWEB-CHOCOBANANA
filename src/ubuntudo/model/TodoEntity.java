package ubuntudo.model;

public class TodoEntity {
	Long tid;
	Long pid;
	String title;
	String contents;
	String dueDate;
	String status;
	Long editerId;
	String partyName;

	// for update todo. currently only contents are can be edited.
	// if changed to be able to edit other columns, edit this constructor.
	public TodoEntity(long tid, String title, String newNote, String dueDate, long uid) {
		this.tid = tid;
		this.title = title;
		this.contents = newNote;
		this.dueDate = dueDate;
		this.editerId = uid;
	}

	public TodoEntity(Long pid, String title, String contents, String dueDate, Long editerId) {
		this(null, pid, title, contents, dueDate, null, editerId, null);
	}

	public TodoEntity(Long tid, Long pid, String title, String contents, String dueDate, String status, Long editerId, String pName) {
		this.tid = tid;
		this.pid = pid;
		this.title = title;
		this.contents = contents;
		this.dueDate = dueDate;
		this.status = status;
		this.editerId = editerId;
		this.partyName = pName;
	}

	public Long getTid() {
		return tid;
	}

	public Long getPid() {
		return pid;
	}

	public String getTitle() {
		return title;
	}

	public String getContents() {
		return contents;
	}

	public String getDueDate() {
		return dueDate;
	}

	public String getStatus() {
		return status;
	}

	public Long getEditerId() {
		return editerId;
	}

	public String getPartyName() {
		return partyName;
	}

	@Override
	public String toString() {
		return "\nTodoEntity [tid=" + tid + ", pid=" + pid + ", title=" + title + ", contents=" + contents + ", duedate=" + dueDate + ", status=" + status
				+ ", editerId=" + editerId + ", pName=" + partyName + "]";
	}
}