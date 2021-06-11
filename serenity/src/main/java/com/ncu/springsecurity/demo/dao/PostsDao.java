package com.ncu.springsecurity.demo.dao;

import java.io.IOException;
import java.util.List;

import com.ncu.springsecurity.demo.Model.PostsModel;

public interface PostsDao {
    public void saveOrUpdate(PostsModel post) ;
    
    public void delete(String email);
     
     
    public List<PostsModel> list();

	void Update(PostsModel post);

	List<PostsModel> userPostlist(String email);

	PostsModel get(int id);

	void delete(int id);

	

}
