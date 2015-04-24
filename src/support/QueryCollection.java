package support;

public class QueryCollection {
	// for user registration
	protected String INSERT_USER = "insert into user (name, email, passwd) values (?, ?, ?)";
	
	// for login
	protected String RETRIEVE_USER = "select uid, name, email, passwd from user where email = ? and passwd = ?";
	
	// for creation of guild
	protected String INSERT_GUILD = "insert into guild (guild_name, leader_id, status) values (?, ?, '123123')";
	
	// for creation of party
	protected String INSERT_PARTY = "insert into party (gid, leader_id, party_name, status) values (?, ?, ?, '123123')";
}