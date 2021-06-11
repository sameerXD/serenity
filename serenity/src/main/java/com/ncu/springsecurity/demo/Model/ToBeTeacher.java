package com.ncu.springsecurity.demo.Model;

import java.sql.Blob;

public class ToBeTeacher {
	private int id;
	private String userName;
	private Blob idCard;
	public ToBeTeacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ToBeTeacher(int id, String userName, Blob idCard) {
		super();
		this.id = id;
		this.userName = userName;
		this.idCard = idCard;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Blob getIdCard() {
		return idCard;
	}
	public void setIdCard(Blob idCard) {
		this.idCard = idCard;
	}
	@Override
	public String toString() {
		return "ToBeTeacher [id=" + id + ", userName=" + userName + ", idCard=" + idCard + "]";
	}
	
	
}
