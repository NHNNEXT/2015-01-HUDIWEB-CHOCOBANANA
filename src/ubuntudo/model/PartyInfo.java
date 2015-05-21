package ubuntudo.model;

import java.util.ArrayList;

public class PartyInfo {
	private String partyName;
	private String guildName;
	private Integer memberNum;
	private Integer todoNum;
	private Float completeRatio;
	private ArrayList<UserEntity> topUserList;
	private Integer isSignUp;
	
	public PartyInfo (String partyName, String guildName, Integer memberNum, Integer todoNum, Float completeRatio, ArrayList<UserEntity> userList, Integer isSignUp) {
		this.partyName = partyName;
		this.guildName = guildName;
		this.memberNum = memberNum;
		this.todoNum = todoNum;
		this.completeRatio = completeRatio;
		this.topUserList = userList;
		this.isSignUp = isSignUp;
	}

	public Integer getIsSignUp() {
		return isSignUp;
	}

	public String getPartyName() {
		return partyName;
	}

	public String getGuildName() {
		return guildName;
	}

	public Integer getMemberNum() {
		return memberNum;
	}

	public Integer getTodoNum() {
		return todoNum;
	}

	public Float getCompleteRatio() {
		return completeRatio;
	}

	public ArrayList<UserEntity> getTopUserList() {
		return topUserList;
	}
}
