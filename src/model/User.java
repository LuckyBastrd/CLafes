package model;

public class User {
	private String username;
	private String password;
	private Integer age;
	private String role;
	
	public User(String username, String password, Integer age, String role) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
