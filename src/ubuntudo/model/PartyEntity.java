package ubuntudo.model;


public class PartyEntity {
	private Long pid;
	private Long gid;
	private Long leaderId;
	private String partyName;
	private String status;

	public PartyEntity(Long gid, Long leaderId, String partyName) {
		this(null, gid, leaderId, partyName, null);
	}

	public PartyEntity(Long pid, Long leaderId, String partyName, String status) {
		this(pid, null, leaderId, partyName, status);
	}

	public PartyEntity(Long pid, Long gid, Long leaderId, String partyName, String status) {
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