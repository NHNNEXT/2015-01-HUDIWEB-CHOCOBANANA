package support;

public class QueryCollection {
	// for user registration
	protected String INSERT_USER = "insert into user (name, email, passwd) values (?, ?, ?)";

	// for login
	protected String RETRIEVE_USER = "select uid, name, email, passwd from user where email = ? and passwd = ?";

	// for creation of guild
	protected String INSERT_GUILD = "insert into guild (guild_name, leader_id, status) values (?, ?, '123123')";

	// for creation of party
	protected String INSERT_PARTY = "insert into party (gid, leader_id, party_name, status) values (?, ?, ?, '123123')";

	// for user registration to party
	protected String INSERT_USER_TO_GUILD = "insert into user_guild_relation (uid, gid, status) values (?, ?, '123123')";

	// for user registration to party
	protected String GET_LAST_ID = "select LAST_INSERT_ID() as last_id";

	// for user registration to party
	protected String RETRIEVE_GUILD_LIST = "select gid, leader_id, guild_name, status FROM guild where guild_name like ?";
	
	// for user registration to party
	protected String RETRIEVE_PARTY_LIST = "select pid, gid, leader_id, party_name, status from party where party_name like ?";

	protected String makeLikeParam(String param) {
		return "%" + param + "%";
	}
}