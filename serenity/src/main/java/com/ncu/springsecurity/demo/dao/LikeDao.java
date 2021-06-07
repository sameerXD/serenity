package com.ncu.springsecurity.demo.dao;


import java.util.List;

import com.ncu.springsecurity.demo.Model.LikeModel;


public interface LikeDao {
	 public void saveOrUpdate(LikeModel like) ;
	
    public void delete(int id);
    
    public LikeModel get(int post_id);
     
    public List<LikeModel> list();
    
    public int count(int post_id);

	void delete(LikeModel like);



}
