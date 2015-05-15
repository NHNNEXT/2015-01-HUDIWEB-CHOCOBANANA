package support;

public class QrysG extends Qrys {
	// for creation of guild
	public static String INSERT_GUILD = "insert into guild (g_name, leader_id, deleted) values (?, ?, '0')";
	
	// for party update
	public static String UPDATE_GUILD = "update guild set leader_id = ?, g_name = ?, deleted = ? where gid = ?";

	// for searching guild list
	public static String RETRIEVE_GUILD_LIST_SEARCH = "select gid, leader_id, g_name, deleted FROM guild where g_name like ?";
	
	// for retrieving my all guild list
	public static String RETRIEVE_MY_GUILD_LIST = "select ugr.gid, g.g_name, g.leader_id, ul.name leader_name  from user_guild_relation ugr join guild g on ugr.gid = g.gid join user u on ugr.uid = u.uid join user ul on g.leader_id = ul.uid where u.uid = ? and g.deleted = 0";
}