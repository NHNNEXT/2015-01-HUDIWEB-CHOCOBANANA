package support;

public class QueryCollection {
	protected String INSERT_USER = "insert into user (name, email, passwd) values (?, ?, ?)";
	protected String RETRIEVE_USER = "select uid, name, email, passwd from user where email = ? and passwd = ?";
}
