package com.ncu.springsecurity.demo.Model;

import java.sql.Blob;

import org.springframework.web.multipart.MultipartFile;

public class StudentModel {
	private String userName;
	private String email;
	private String course;
	private String specialisation;
	private String gender;
	private MultipartFile image;
	private Blob photo;
	public StudentModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentModel(String userName, String email, String course, String specialisation, String gender,
			MultipartFile image) {
		super();
		this.userName = userName;
		this.email = email;
		this.course = course;
		this.specialisation = specialisation;
		this.gender = gender;
		this.image = image;
	}
	
	public Blob getPhoto() {
		return photo;
	}
	public void setPhoto(Blob photo) {
		this.photo = photo;
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
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getSpecialisation() {
		return specialisation;
	}
	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "StudentModel [userName=" + userName + ", email=" + email + ", course=" + course + ", specialisation="
				+ specialisation + ", gender=" + gender + ", image=" + image + "]";
	}
	
	

}
