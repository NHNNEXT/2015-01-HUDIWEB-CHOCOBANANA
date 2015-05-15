package support;

public class QrysU extends Qrys {//
// for user validation
	public static String VALIDATE_USER = "SELECT uid, email FROM user WHERE email = ?";
	
	// for user registration
	public static String INSERT_USER = "insert into user (name, email, passwd) values (?, ?, ?)";

	// for login
	public static String RETRIEVE_USER = "SELECT uid, name, email, passwd FROM user WHERE email = ? AND passwd = ?";

	// for user registration to guild
	public static String INSERT_USER_TO_GUILD = "insert into user_guild_relation (uid, gid, state) values (?, ?, '0')";

	// for user registration to party
	public static String INSERT_USER_TO_PARTY = "insert into user_party_relation (uid, pid, state) values (?, ?, '01')";
}