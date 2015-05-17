package support;

public class QrysP extends Qrys {
	// for creation of party
	public static String INSERT_PARTY = "insert into party (gid, party_leader_id, p_name, deleted) values (?, ?, ?, '0')";

	// for party update
	public static String UPDATE_PARTY = "update party set party_leader_id = ?, p_name = ?, deleted = ? where pid = ?";

	// for searching party list
	public static String RETRIEVE_PARTY_LIST = "select pid, gid, party_leader_id, p_name, deleted from party where p_name like ?";

	// for retrieve party in guild
	public static String RETRIEVE_PARTY_IN_GUILD = "select pid, gid, party_leader_id, p_name, deleted from party where gid = ?";

	// for retrieving my all party list
	public static String RETRIEVE_MY_PARTY_LIST = "select ugr.gid, g.g_name, g.leader_id, ug.name, p.pid, p.p_name, p.party_leader_id, up.name, upr.state from user_guild_relation ugr join guild g on ugr.gid = g.gid join party p on g.gid = p.gid left outer join user_party_relation upr on upr.pid = p.pid and upr.uid = ? join user ug on ug.uid = g.leader_id join user up on up.uid = p.party_leader_id where ugr.uid = ?";

}
