package support;

public class QrysG extends Qrys {
	// for creation of guild
	public static String INSERT_GUILD = "insert into guild (g_name, leader_id, deleted) values (?, ?, '0')";
	
	// for party update
	public static String UPDATE_GUILD = "update guild set leader_id = ?, g_name = ?, deleted = ? where gid = ?";

	// for searching guild list
	public static String RETRIEVE_GUILD_LIST_SEARCH = "select gid, leader_id, g_name, deleted FROM guild where g_name like ?";
	
	// for retrieving my all guild list
	public static String RETRIEVE_MY_GUILD_LIST = "select ugr.gid, g.g_name, g.leader_id, ug.name leader_name  from user_guild_relation ugr join guild g on ugr.gid = g.gid join user u on ugr.uid = u.uid join user ug on g.leader_id = ug.uid where u.uid = ? and g.deleted = 0";

	// for retrieving detail info of a guild  
	public static final String RETRIEVE_GUILD_DETAIL = "select g.gid, g.g_name, g.leader_id, u.name, (select count(*) from user_guild_relation where gid = ?) members, (select count(*) from party where gid = ?) parties , (ifnull((select state from user_guild_relation where gid = ? and uid = ?), -1)) state from guild g join user u on g.leader_id = u.uid where g.gid = ?";
}