package model;

public class User {
	private Integer userID;
	private String username;
	private String userpassword;
	private Integer userage;
	private String userrole;
	
	public User(Integer userID, String username, String userpassword, Integer userage, String userrole) {
		super();
		this.userID = userID;
		this.username = username;
		this.userpassword = userpassword;
		this.userage = userage;
		this.userrole = userrole;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public Integer getUserage() {
		return userage;
	}

	public void setUserage(Integer userage) {
		this.userage = userage;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
}
