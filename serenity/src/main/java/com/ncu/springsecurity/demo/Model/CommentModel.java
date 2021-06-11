package com.ncu.springsecurity.demo.Model;

import java.util.Date;

public class CommentModel {
	private int id;
	private int post_id;
	private String email;
	private String Comment;
	private java.util.Date time;
	public CommentModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentModel(int id, int post_id, String email, String comment, Date time) {
		super();
		this.id = id;
		this.post_id = post_id;
		this.email = email;
		Comment = comment;
		this.time = time;
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
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	public java.util.Date getTime() {
		return time;
	}
	public void setTime(java.util.Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "CommentModel [id=" + id + ", post_id=" + post_id + ", email=" + email + ", Comment=" + Comment
				+ ", time=" + time + "]";
	}
	
	

}
