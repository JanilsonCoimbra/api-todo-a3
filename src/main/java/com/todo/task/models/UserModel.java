package com.todo.task.models;

public class UserModel {
	
	private String email;
	private String password;
	
	public UserModel() {
		super();
	}
	
	public UserModel(String email, String password) {
		super();
		this.email = email;
		this.password = password;
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
