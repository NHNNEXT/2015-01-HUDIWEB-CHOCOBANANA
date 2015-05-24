package support;

public class Qrys {
//<<<<<<< HEAD
//	// for user registration
//	public static String INSERT_USER = "insert into user (name, email, passwd) values (?, ?, ?)";
//	
//	// for user validation
//	public static String VALIDATE_USER = "SELECT uid, email FROM user WHERE email = ?";
//
//	// for login
//	public static String RETRIEVE_USER = "SELECT uid, name, email, passwd FROM user WHERE email = ? AND passwd = ?";
//
//	// for creation of guild
//	public static String INSERT_GUILD = "insert into guild (guild_name, leader_id, status) values (?, ?, '123123')";
//
//	// for creation of party
//	public static String INSERT_PARTY = "insert into party (gid, leader_id, party_name, status) values (?, ?, ?, '123123')";
//
//	// for user registration to guild
//	public static String INSERT_USER_TO_GUILD = "insert into user_guild_relation (uid, gid, status) values (?, ?, '123123')";
//
//=======
//>>>>>>> todoCompletion
	// for getting last auto increment id
	public static String GET_LAST_ID = "select LAST_INSERT_ID() as last_id";

	public static String makeLikeParamBoth(String param) {
		return "%" + param + "%";
	}
	
	public static String makeLikeParamLeft(String param) {
		return "%" + param;
	}
	
	public static String makeLikeParamRight(String param) {
		return param + "%";
	}
}