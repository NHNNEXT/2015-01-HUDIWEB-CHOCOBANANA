package ubuntudo.model;

public class GuildEntity {
	private long gid;
	private long leaderId;
	private String guildName;
	private String status;

	// for guild creation
	public GuildEntity(long leaderId, String guildName) {
		this.leaderId = leaderId;
		this.guildName = guildName;
	}
	
	// for general purpose
	public GuildEntity(long gid, long leaderId, String guildName, String status) {
		this.gid = gid;
		this.leaderId = leaderId;
		this.guildName = guildName;
		this.status = status;
	}
	
	// for guild search
	public GuildEntity(String guildName) {
		this.guildName = guildName;
	}

	public long getGid() {
		return gid;
	}
	public void setGid(long gid){
		this.gid = gid;
	}
	public long getLeaderId() {
		return leaderId;
	}
	public String getGuildName() {
		return guildName;
	}
	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "\nGuildEntity [gid=" + gid + ", leaderId=" + leaderId + ", guildName=" + guildName + ", status=" + status + "]";
	}
}