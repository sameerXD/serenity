package com.ncu.springsecurity.demo.Model;

import java.sql.Blob;

public class StudentModel1 {
	private String userName;
	private String email;
	private String Course;
	private String specialisation;
	private Blob image;
	private String gender;
	public StudentModel1() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentModel1(String userName, String email, String course, String specialisation, Blob image,
			String gender) {
		super();
		this.userName = userName;
		this.email = email;
		Course = course;
		this.specialisation = specialisation;
		this.image = image;
		this.gender = gender;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCourse() {
		return Course;
	}
	public void setCourse(String course) {
		Course = course;
	}
	public String getSpecialisation() {
		return specialisation;
	}
	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "StudentModel1 [userName=" + userName + ", email=" + email + ", Course=" + Course + ", specialisation="
				+ specialisation + ", image=" + image + ", gender=" + gender + "]";
	}
	
	

}
