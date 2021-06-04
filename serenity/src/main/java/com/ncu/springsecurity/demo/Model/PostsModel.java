package com.ncu.springsecurity.demo.Model;

import java.sql.Blob;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class PostsModel {
	private int id;
	private String email;
	private String title;
	private String body;
	private Blob image;
	private Blob video;
	private int points;
	private MultipartFile image1;
	private MultipartFile video1;
	private java.util.Date time;
	public PostsModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PostsModel(int id, String email, String title, String body, Blob image, Blob video, int points, Date time) {
		super();
		this.id = id;
		this.email = email;
		this.title = title;
		this.body = body;
		this.image = image;
		this.video = video;
		this.points = points;
		this.time = time;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	public Blob getVideo() {
		return video;
	}
	public void setVideo(Blob video) {
		this.video = video;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public java.util.Date getTime() {
		return time;
	}
	public void setTime(java.util.Date time) {
		this.time = time;
	}
	


	public MultipartFile getImage1() {
		return image1;
	}
	public void setImage1(MultipartFile image1) {
		this.image1 = image1;
	}
	public MultipartFile getVideo1() {
		return video1;
	}
	public void setVideo1(MultipartFile video1) {
		this.video1 = video1;
	}
	@Override
	public String toString() {
		return "PostsModel [id=" + id + ", email=" + email + ", title=" + title + ", body=" + body + ", image=" + image
				+ ", video=" + video + ", points=" + points + ", image1=" + image1 + ", video1=" + video1 + ", time="
				+ time + "]";
	}
	
	
	

}
