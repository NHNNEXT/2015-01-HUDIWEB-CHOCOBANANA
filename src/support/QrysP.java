package support;

public class QrysP extends Qrys {
	// for creatiON of party
	public static String INSERT_PARTY = "insert into party (gid, party_leader_id, p_name, deleted) values (?, ?, ?, '0')";

	// for party update
	public static String UPDATE_PARTY = "update party set party_leader_id = ?, p_name = ?, deleted = ? WHERE pid = ?";

	// for searching party list
	public static String RETRIEVE_PARTY_LIST = "SELECT pid, gid, party_leader_id, p_name, deleted FROM party WHERE p_name like ?";

	// for retrieve party in guild
	public static String RETRIEVE_PARTY_IN_GUILD = "SELECT p.pid, p.p_name, p.party_leader_id, up.name leader_name, ifnull(upr.state, -1) status FROM guild g left outer JOIN party p ON g.gid = p.gid left outer JOIN (SELECT * FROM user_party_relation WHERE uid = ?) upr ON p.pid = upr.pid left outer JOIN user u ON upr.uid = u.uid left outer JOIN user up ON up.uid = p.party_leader_id WHERE g.gid = ?;";

	// for retrieving my all party list
	public static String RETRIEVE_PARTY_LIST_OF_MY_GUILDS = "SELECT ugr.gid, g.g_name, g.leader_id, ug.name, p.pid, p.p_name, p.party_leader_id, up.name, ifnull(upr.state, -1) status FROM user_guild_relation ugr JOIN guild g ON ugr.gid = g.gid JOIN party p ON g.gid = p.gid left outer JOIN user_party_relation upr ON upr.pid = p.pid AND upr.uid = ? JOIN user ug ON ug.uid = g.leader_id JOIN user up ON up.uid = p.party_leader_id WHERE ugr.uid = ?";

	// party info - guild name
	public static String GET_GNAME = "SELECT g.g_name guildName FROM party p JOIN guild g ON p.gid = g.gid WHERE p.pid = ?";
	
	// party info - party name
	public static String GET_PNAME = "SELECT p_name partyName FROM party WHERE pid = ?";	

	// party info - the number of party member
	public static String GET_PARTY_MEMBER_NUMBER = "SELECT COUNT(*) memberCount FROM user_party_relation WHERE pid = ?";

	// party info - the number of party todo
	public static String GET_PARTY_TODO_NUMBER = "SELECT COUNT(*) todoCount FROM  todo t WHERE t.pid = ?";

	// party info - the ratio of party todo completiON 
	public static String GET_RATIO = "select (select count(*) from todo_user_relation where tid IN (select tid from todo t where t.pid = ?) AND completed = 'trel01')/ (select count(*) from todo_user_relation where tid IN (select tid from todo t where t.pid = ?)) as completeRatio";
	
	// party info - the member list who completes party todo well.
	public static String GET_TOP3_MEMBERS = "SELECT u.name, u.email, COUNT(tur.tid) count FROM todo_user_relation tur JOIN user u ON tur.uid = u.uid WHERE tur.tid IN (SELECT tid FROM todo WHERE pid = ?) AND tur.completed = 'trel01' GROUP BY tur.uid ORDER BY COUNT(tur.tid) DESC LIMIT 3";
	
	//party info - isSignUp 
	public static String IS_USER_SIGNUP = "select count(*) count from user_party_relation where uid = ? AND pid = ?";
	
	// party todo list
	public static String GET_PARTY_TODO_LIST = "SELECT t.tid, t.pid, t.title, t.contents, t.duedate, t.status, t.editer_id, p.p_name FROM  todo t JOIN party p ON t.pid = p.pid WHERE t.pid = ? AND t.status <> 'todo03' AND t.status <> 'todo04' ORDER BY dueDate";
	
	// party list that user signs up
	public static String GET_PARTY_LIST = "SELECT * FROM  todo t WHERE t.pid = ?";
	
	// party list for user
	public static String GET_PARTY_LIST_OF_USER ="SELECT pid, p_name partyName FROM party WHERE pid IN (SELECT pid FROM user_party_relation WHERE uid = ?) AND deleted = 0";
	
	//party for new joined user
	public static String GET_PARTY_OF_NEW_USER ="SELECT pid, gid, party_leader_id, p_name , deleted FROM party WHERE pid =?";

	//파티에 가입시키기 전에, 길드에 가입했는지 확인하기 위한 query
	public static String IS_USER_SIGN_UP_TO_GUILD = "SELECT count(*) count FROM user_guild_relation WHERE uid = ? AND gid = (SELECT gid FROM party WHERE pid = ?)";
	
	public static String ASSIGN_PARTY_TODOS_TO_USER = "INSERT INTO todo_user_relation (SELECT tid, ?, 'trel00' FROM todo WHERE pid = ?)";
}
