package com.ncu.springsecurity.demo.Model;

public class ReportCountModel {
	private int count;
	private int post_id;
	public ReportCountModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReportCountModel(int count, int post_id) {
		super();
		this.count = count;
		this.post_id = post_id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	@Override
	public String toString() {
		return "LikeCountModel [count=" + count + ", post_id=" + post_id + "]";
	}
	
	

}
