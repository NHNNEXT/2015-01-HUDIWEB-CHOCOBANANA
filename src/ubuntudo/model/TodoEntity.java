package ubuntudo.model;

import java.sql.Date;

public class TodoEntity {
	Long tid;
	Long assigner_id;
	Long pid;
	String title;
	String contents;
	Date dueDate;
	int complete;
	Long lastEditerId;
	
	public TodoEntity(Long assigner_id, Long pid, String title, String contents, Date dueDate) {
		this(null, assigner_id, pid, title, contents, dueDate, 0, assigner_id);
	}

	public TodoEntity(Long tid, Long assigner_id, Long pid, String title, String contents, Date dueDate, int completed, Long lastEditerId) {
		this.tid = tid;
		this.assigner_id = assigner_id;
		this.pid = pid;
		this.title = title;
		this.contents = contents;
		this.dueDate = dueDate;
		this.complete = completed;
		this.lastEditerId = lastEditerId;
	}

	public Long getTid() {
		return tid;
	}
	public Long getAssigner_id() {
		return assigner_id;
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
		return dueDate;
	}
	public int getComplete() {
		return complete;
	}
	public Long getLastEditerId() {
		return lastEditerId;
	}
	
	
	@Override
	public String toString() {
		return "TodoEntity [tid=" + tid + ", assigner_id=" + assigner_id + ", pid=" + pid + ", title=" + title + ", contents=" + contents + ", dueDate=" + dueDate
				+ ", complete=" + complete + ", last_editer_id=" + lastEditerId + "]";
	}
	
	
}
