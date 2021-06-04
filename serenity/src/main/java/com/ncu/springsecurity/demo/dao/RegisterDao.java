package com.ncu.springsecurity.demo.dao;

import java.util.List;


import com.ncu.springsecurity.demo.Model.RegisterModel;

public interface RegisterDao {
    public void saveOrUpdate(RegisterModel user);
    
    public void delete(String email);
     
    public RegisterModel get(String email);
     
    public List<RegisterModel> list();

	void Update(RegisterModel user);

}
