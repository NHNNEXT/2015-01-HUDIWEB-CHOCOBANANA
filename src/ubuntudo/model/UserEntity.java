package ubuntudo.model;

public class UserEntity {

	private Long uid;
	private String name;
	private String email;
	private String passwd;

	// use this constructor when system registering a user.
	public UserEntity(String name, String email, String passwd) {
		this(null, name, email, passwd);
	}

	// use this constructor for general purpose.
	public UserEntity(Long uid, String name, String email, String passwd) {
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.passwd = passwd;
	}

	public Long getUid() {
		return uid;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPasswd() {
		return passwd;
	}

	@Override
	public String toString() {
		return "UserDto [uid=" + uid + ", name=" + name + ", email=" + email + ", passwd=" + passwd + "]";
	}
}