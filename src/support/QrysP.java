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


}
