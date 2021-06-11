package com.ncu.springsecurity.demo.dao;

import java.util.List;

import com.ncu.springsecurity.demo.Model.CommentModel;


public interface CommentDao {
	 public void saveOrUpdate(CommentModel comment) ;
		
	    public void delete(int id);
	    
	    public CommentModel get(int post_id);
	     
	    public List<CommentModel> list();
	    
	    public int count(int post_id);

		void delete(CommentModel comment);

}
