package support;

public class Qrys {
	// for user registration
	public static String INSERT_USER = "insert into user (name, email, passwd) values (?, ?, ?)";

	// for login
	public static String RETRIEVE_USER = "SELECT uid, name, email, passwd FROM user WHERE email = ? AND passwd = ?";

	// for creation of guild
	public static String INSERT_GUILD = "insert into guild (g_name, leader_id, deleted) values (?, ?, '0')";

	// for creation of party
	public static String INSERT_PARTY = "insert into party (gid, party_leader_id, p_name, deleted) values (?, ?, ?, '0')";

	// for user registration to guild
	public static String INSERT_USER_TO_GUILD = "insert into user_guild_relation (uid, gid, state) values (?, ?, '0')";

	// for getting last auto increment id
	public static String GET_LAST_ID = "select LAST_INSERT_ID() as last_id";

	// for searching guild list
	public static String RETRIEVE_GUILD_LIST = "select gid, leader_id, g_name, deleted FROM guild where g_name like ?";

	// for searching party list
	public static String RETRIEVE_PARTY_LIST = "select pid, gid, party_leader_id, p_name, deleted from party where p_name like ?";

	// for party update
	public static String UPDATE_PARTY = "update party set party_leader_id = ?, p_name = ?, deleted = ? where pid = ?";

	// for user registration to party
	public static String INSERT_USER_TO_PARTY = "insert into user_party_relation (uid, pid, state) values (?, ?, '0')";

	// for party update
	public static String UPDATE_GUILD = "update guild set leader_id = ?, g_name = ?, deleted = ? where gid = ?";

	// for retrieve party in guild
	public static String RETRIEVE_PARTY_IN_GUILD = "select pid, gid, party_leader_id, p_name, deleted from party where gid = ?";

	// for retrieve party in guild
	public static String RETRIEVE_TODO_FOR_PERSONAL_VIEW = "SELECT t.tid, t.pid, t.title, t.contents, t.duedate, t.status, t.editer_id, p.p_name FROM todo t INNER JOIN party p ON t.pid = p.pid WHERE tid IN (SELECT tid FROM todo_user_relation WHERE uid = ? AND completed = 'trel00') ORDER BY dueDate";

	public static String makeLikeParam(String param) {
		return "%" + param + "%";
	}
}