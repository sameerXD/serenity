package com.ncu.springsecurity.demo.Model;

public class ReportModel {
	private int id;
	private int post_id;
	private String email;
	public ReportModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReportModel(int id, int post_id, String email) {
		super();
		this.id = id;
		this.post_id = post_id;
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "ReportModel [id=" + id + ", post_id=" + post_id + ", email=" + email + "]";
	}
	
	

}
