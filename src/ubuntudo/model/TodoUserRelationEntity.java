package ubuntudo.model;

public class TodoUserRelationEntity {
	private Long tid;
	private Long uid;
	private String completed;
	
	public TodoUserRelationEntity (Long tid, Long uid) {
		this(tid, uid, null);
	}
	
	public TodoUserRelationEntity (Long tid, Long uid, String completed) {
		this.tid = tid;
		this.uid = uid;
		this.completed = completed;
	}
	
	public Long getTid() {
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getCompleted() {
		return completed;
	}
	public void setCompleted(String completed) {
		this.completed = completed;
	}
	
	@Override
	public String toString() {
		return "TodoUserRelationEntity [tid=" + tid + ", uid=" + uid
				+ ", completed=" + completed + "]";
	}	
}
