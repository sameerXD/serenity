package com.ncu.springsecurity.demo.Model;

public class TotalMarks {
	private String email;
	private int marks;
	public TotalMarks() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TotalMarks(String email, int marks) {
		super();
		this.email = email;
		this.marks = marks;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "TotalMarks [email=" + email + ", marks=" + marks + "]";
	}
	

}
