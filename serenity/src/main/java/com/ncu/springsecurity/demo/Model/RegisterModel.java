package com.ncu.springsecurity.demo.Model;

public class RegisterModel {
	private String name;
	private String password;
	private String role;
	
	public RegisterModel(String name, String password, String role,String email) {
		super();
		this.name = name;
		this.password = password;
		this.role = role;
		
	}
	public RegisterModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	

	@Override
	public String toString() {
		return "RegisterModel [name=" + name + ", password=" + password + ", role=" + role + " ]";
	}

	
	

}
