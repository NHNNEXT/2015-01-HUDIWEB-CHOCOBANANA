package support;

public class QrysG extends Qrys {
	// for creation of guild
	public static String INSERT_GUILD = "insert into guild (g_name, leader_id, deleted) values (?, ?, '0')";
	
	// for party update
	public static String UPDATE_GUILD = "update guild set leader_id = ?, g_name = ?, deleted = ? where gid = ?";

	// for searching guild list
	public static String RETRIEVE_GUILD_LIST = "select gid, leader_id, g_name, deleted FROM guild where g_name like ?";
}