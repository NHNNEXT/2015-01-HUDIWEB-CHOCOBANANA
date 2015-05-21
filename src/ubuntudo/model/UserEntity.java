package ubuntudo.model;

public class UserEntity {

	private Long uid;
	private String name;
	private String email;
	private String passwd;
	private Long todoCount;

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
	
	// use this constructor for validating a user.
	public UserEntity(String email) {
		this(null,null,email,null);
	}
	
	public UserEntity(String name, String email, Long todoCount) {
		this.name = name;
		this.email = email;
		this.todoCount = todoCount;
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

	public Long getTodoCount() {
		return todoCount;
	}

	@Override
	public String toString() {
		return "UserDto [uid=" + uid + ", name=" + name + ", email=" + email + ", passwd=" + passwd + "]";
	}
}