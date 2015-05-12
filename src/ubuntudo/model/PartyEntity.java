package ubuntudo.model;

public class PartyEntity {
	// 되도록 접근자는 private으로 해주세요 ㅠ
	private long pid;
	private long gid;
	private long leaderId;
	private String partyName;
	private String status;

	public PartyEntity(long gid, long leaderId, String partyName) {
		this.gid = gid;
		this.leaderId = leaderId;
		this.partyName = partyName;
	}

	public PartyEntity(long pid, long leaderId, String partyName, String status) {
		this.pid = pid;
		this.leaderId = leaderId;
		this.partyName = partyName;
		this.status = status;
	}

	public PartyEntity(long pid, long gid, long leaderId, String partyName, String status) {
		this.pid = pid;
		this.gid = gid;
		this.leaderId = leaderId;
		this.partyName = partyName;
		this.status = status;
	}

	public long getPid() {
		return pid;
	}

	public long getGid() {
		return gid;
	}

	public long getLeaderId() {
		return leaderId;
	}

	public String getPartyName() {
		return partyName;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "\nPartyEntity [pid=" + pid + ", gid=" + gid + ", leaderId=" + leaderId + ", partyName=" + partyName + ", status=" + status + "]";
	}
}