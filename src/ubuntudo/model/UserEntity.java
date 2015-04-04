package ubuntudo.model;

public class UserEntity {

	private String uid;
	private String name;
	private String email;
	private String passwd;

	// use this constructor when system registering a user.
	public UserEntity(String name, String email, String passwd) {
		this.name = name;
		this.email = email;
		this.passwd = passwd;
	}

	// use this constructor for general purpose.
	public UserEntity(String uid, String name, String email, String passwd) {
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.passwd = passwd;
	}

	public String getUid() {
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
		return "UserDto [uid=" + uid + ", name=" + name + ", email=" + email
				+ ", passwd=" + passwd + "]";
	}

}
