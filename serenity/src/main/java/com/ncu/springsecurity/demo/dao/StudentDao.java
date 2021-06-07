package com.ncu.springsecurity.demo.dao;

import java.io.IOException;
import java.util.List;


import com.ncu.springsecurity.demo.Model.StudentModel;
import com.ncu.springsecurity.demo.Model.StudentModel1;

public interface StudentDao {
public void saveOrUpdate(StudentModel user) throws IOException;
    
    public void delete(String user);
     
    public StudentModel1 get(String user);
     
    public List<StudentModel> list();

	void Update(StudentModel user) throws IOException;

}
