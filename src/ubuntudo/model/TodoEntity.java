package ubuntudo.model;

import java.sql.Date;

public class TodoEntity {
	Long tid;
	Long pid;
	String title;
	String contents;
	Date duedate;
	String status;
	Long editerId;
	String pName;
	
	public TodoEntity(Long pid, String title, String contents, Date dueDate, Long editerId) {
		this(null, pid, title, contents, dueDate, null, editerId, null);
	}

	public TodoEntity(Long tid, Long pid, String title, String contents, Date dueDate, String status, Long editerId, String pName) {
		this.tid = tid;
		this.pid = pid;
		this.title = title;
		this.contents = contents;
		this.duedate = dueDate;
		this.status = status;
		this.editerId = editerId;
		this.pName = pName;
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
	public Date getDueDate() {
		return duedate;
	}
	public String getStatus() {
		return status;
	}
	public Long getEditerId() {
		return editerId;
	}
	public String getpName() {
		return pName;
	}
	
	@Override
	public String toString() {
		return "TodoEntity [tid=" + tid + ", pid=" + pid + ", title=" + title + ", contents=" + contents + ", duedate=" + duedate
				+ ", status=" + status + ", editerId=" + editerId + ", pName=" + pName+"]";
	}
	
	
}
