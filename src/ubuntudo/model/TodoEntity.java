package ubuntudo.model;

public class TodoEntity {
	Long tid;
	Long assigner_id;
	Long pid;
	String title;
	String contents;
	String dueDate;
	String complete;
	String last_editer_id;
	
	// for todo creation
	public TodoEntity(Long assigner_id, Long pid, String title, String contents, String dueDate) {
		this.assigner_id = assigner_id;
		this.pid = pid;
		this.title = title;
		this.contents = contents;
		this.dueDate = dueDate;
	}

	// for exiting todo modification
	public TodoEntity(Long tid, Long assigner_id, Long pid, String title, String contents, String dueDate, String complete, String last_editer_id) {
		this.tid = tid;
		this.assigner_id = assigner_id;
		this.pid = pid;
		this.title = title;
		this.contents = contents;
		this.dueDate = dueDate;
		this.complete = complete;
		this.last_editer_id = last_editer_id;
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
	public String getDueDate() {
		return dueDate;
	}
	public String getComplete() {
		return complete;
	}
	public String getLast_editer_id() {
		return last_editer_id;
	}
	
	
	@Override
	public String toString() {
		return "TodoEntity [tid=" + tid + ", assigner_id=" + assigner_id + ", pid=" + pid + ", title=" + title + ", contents=" + contents + ", dueDate=" + dueDate
				+ ", complete=" + complete + ", last_editer_id=" + last_editer_id + "]";
	}
	
	
}
