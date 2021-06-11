package com.ncu.springsecurity.demo.Model;

import java.sql.Blob;

import org.springframework.web.multipart.MultipartFile;

public class RegisterModel {
	private String name;
	private String password;
	private String role;
	private Blob idCard;
	private MultipartFile idCard1;
	public RegisterModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RegisterModel(String name, String password, String role, Blob idCard, MultipartFile idCard1) {
		super();
		this.name = name;
		this.password = password;
		this.role = role;
		this.idCard = idCard;
		this.idCard1 = idCard1;
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
	public Blob getIdCard() {
		return idCard;
	}
	public void setIdCard(Blob idCard) {
		this.idCard = idCard;
	}
	public MultipartFile getIdCard1() {
		return idCard1;
	}
	public void setIdCard1(MultipartFile idCard1) {
		this.idCard1 = idCard1;
	}
	@Override
	public String toString() {
		return "RegisterModel [name=" + name + ", password=" + password + ", role=" + role + ", idCard=" + idCard
				+ ", idCard1=" + idCard1 + "]";
	}

	
	

}
