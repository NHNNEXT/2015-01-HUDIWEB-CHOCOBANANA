package ubuntudo.model;

public class GuildEntity {
	long gid;
	String guildName;
	long leaderId;
	String status;

	// for guild creation
	public GuildEntity(String guildName, long leaderId) {
		this.guildName = guildName;
		this.leaderId = leaderId;
	}
	
	// for general purpose
	public GuildEntity(long gid, String guildName, long leaderId, String status) {
		this.gid = gid;
		this.guildName = guildName;
		this.leaderId = leaderId;
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
	public String getGuildName() {
		return guildName;
	}
	public long getLeaderId() {
		return leaderId;
	}
	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "\nGuildEntity [gid=" + gid + ", guildName=" + guildName + ", leaderId=" + leaderId + ", status=" + status + "]";
	}
}