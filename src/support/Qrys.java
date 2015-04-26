package support;

public class Qrys {
	// for user registration
	public static String INSERT_USER = "insert into user (name, email, passwd) values (?, ?, ?)";

	// for login
	public static String RETRIEVE_USER = "select uid, name, email, passwd from user where email = ? and passwd = ?";

	// for creation of guild
	public static String INSERT_GUILD = "insert into guild (guild_name, leader_id, status) values (?, ?, '123123')";

	// for creation of party
	public static String INSERT_PARTY = "insert into party (gid, leader_id, party_name, status) values (?, ?, ?, '123123')";

	// for user registration to party
	public static String INSERT_USER_TO_GUILD = "insert into user_guild_relation (uid, gid, status) values (?, ?, '123123')";

	// for user registration to party
	public static String GET_LAST_ID = "select LAST_INSERT_ID() as last_id";

	// for user registration to party
	public static String RETRIEVE_GUILD_LIST = "select gid, leader_id, guild_name, status FROM guild where guild_name like ?";
	
	// for user registration to party
	public static String RETRIEVE_PARTY_LIST = "select pid, gid, leader_id, party_name, status from party where party_name like ?";
	
	// for user registration to party
	public static String UPDATE_PARTY = "update party set leader_id = ?, party_name = ?, status = ? where pid = ?";

	public static String makeLikeParam(String param) {
		return "%" + param + "%";
	}
}