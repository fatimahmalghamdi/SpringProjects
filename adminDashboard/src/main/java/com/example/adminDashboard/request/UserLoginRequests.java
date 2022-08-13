package com.example.adminDashboard.request;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserLoginRequests {
	
	@Email(message = "pls enter the correct email syntax")
	private String email;
	
	@NotEmpty
	private String password;
	
	//constructor + getters and setters:
	public UserLoginRequests() {
		//
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
