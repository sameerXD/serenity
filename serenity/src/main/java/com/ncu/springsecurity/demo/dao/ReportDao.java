package com.ncu.springsecurity.demo.dao;

import java.util.List;

import com.ncu.springsecurity.demo.Model.LikeModel;
import com.ncu.springsecurity.demo.Model.ReportCountModel;
import com.ncu.springsecurity.demo.Model.ReportModel;

public interface ReportDao {
	 public void saveOrUpdate(ReportModel report) ;
		
	    public void delete(int id);
	    
	    public LikeModel get(int post_id);
	     
	    public List<ReportModel> list();
	    
	    public int count(int post_id);

		void delete(ReportModel report);

		List<ReportCountModel> countAll();


}
