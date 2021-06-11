package com.ncu.springsecurity.demo.dao;

import java.io.IOException;
import java.util.List;


import com.ncu.springsecurity.demo.Model.RegisterModel;
import com.ncu.springsecurity.demo.Model.StudentModel;
import com.ncu.springsecurity.demo.Model.ToBeTeacher;

public interface RegisterDao {
    public void saveOrUpdate(RegisterModel user) throws IOException;
    
    public void delete(String email);
     
    public RegisterModel get(String email);
     
    public List<StudentModel> listOfAllUsers();

	void Update(RegisterModel user);

	List<ToBeTeacher> toBeTeacherList();

	ToBeTeacher getToBeTeacher(int id);

	void deleteToBeTeacher(int id);

	void approveToBeTeacher(String userName);

	void deleteUser(String email);

	

}
