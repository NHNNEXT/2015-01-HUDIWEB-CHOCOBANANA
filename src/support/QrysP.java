package support;

public class QrysP extends Qrys {
	// for creation of party
	public static String INSERT_PARTY = "insert into party (gid, party_leader_id, p_name, deleted) values (?, ?, ?, '0')";

	// for party update
	public static String UPDATE_PARTY = "update party set party_leader_id = ?, p_name = ?, deleted = ? where pid = ?";

	// for searching party list
	public static String RETRIEVE_PARTY_LIST = "select pid, gid, party_leader_id, p_name, deleted from party where p_name like ?";

	// for retrieve party in guild
	public static String RETRIEVE_PARTY_IN_GUILD = "select p.pid, p.p_name, p.party_leader_id, up.name leader_name, ifnull(upr.state, -1) status from guild g left outer join party p on g.gid = p.gid left outer join (select * from user_party_relation where uid = ?) upr on p.pid = upr.pid left outer join user u on upr.uid = u.uid left outer join user up on up.uid = p.party_leader_id where g.gid = ?;";

	// for retrieving my all party list
	public static String RETRIEVE_PARTY_LIST_OF_MY_GUILDS = "select ugr.gid, g.g_name, g.leader_id, ug.name, p.pid, p.p_name, p.party_leader_id, up.name, ifnull(upr.state, -1) status from user_guild_relation ugr join guild g on ugr.gid = g.gid join party p on g.gid = p.gid left outer join user_party_relation upr on upr.pid = p.pid and upr.uid = ? join user ug on ug.uid = g.leader_id join user up on up.uid = p.party_leader_id where ugr.uid = ?";

}
