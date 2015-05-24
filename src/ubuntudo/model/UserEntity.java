package ubuntudo.model;

public class UserEntity {

	private Long uid;
	private String name;
	private String email;
	private String passwd;
	private Long todoCount;
	
	public UserEntity(Long uid, String name, String email, String passwd, Long todoCount) {
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.passwd = passwd;
		this.todoCount = todoCount;
	}
	
	// use this constructor when system registering a user.
	public UserEntity(String name, String email, String passwd) {
		this(null, name, email, passwd, null);
	}

	// use this constructor for general purpose.
	public UserEntity(Long uid, String name, String email, String passwd) {
		this(uid, name, email, passwd, null);
	}

	// use this constructor for validating a user.
	public UserEntity(String email) {
		this(null, null, email, null, null);
	}

	public UserEntity(String name, String email, Long todoCount) {
		this(null, name, email, null, todoCount);
	}

	public UserEntity(Long uid) {
		this(uid, null, null, null, null);
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