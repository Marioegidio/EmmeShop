package model;

import java.io.Serializable;

public abstract class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String username;
	String password;
	
	public UserBean()
	{
		this.username="";
		this.password="";
	}
	public UserBean(String username, String password) {
		this.username = username;
		this.password= password;
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
	



}
