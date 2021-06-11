 package com.ncu.springsecurity.demo.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



import com.ncu.springsecurity.demo.Model.StudentModel;
import com.ncu.springsecurity.demo.Model.StudentModel1;


@Repository
@Transactional
public class StudentDaoImp implements StudentDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void saveOrUpdate(StudentModel user) throws IOException {
		// TODO Auto-generated method stub
		byte[] photoBytes = user.getImage().getBytes();
		String sql = "INSERT INTO students(userName,email ,course,specialisation,image,gender) VALUES (?,?,?,?,?,?)";
		 jdbcTemplate.update(sql, new Object[]
			        {  user.getUserName(), user.getEmail(),user.getCourse(),user.getSpecialisation(),photoBytes,user.getGender()}
			        );
			        
	}

	@Override
	public void delete(String user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StudentModel1 get(String user) {
		// TODO Auto-generated method stub
	        try {
	        	String sql = "SELECT * FROM students where email='" + user + "'";
			    StudentModel1 student = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(StudentModel1.class));
				System.out.println("phone nmber:-------------------------------------------------------------"+ student.getUserName());
			    return student;
		
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		
	}

	@Override
	public List<StudentModel> list() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public void Update(StudentModel user){
		// TODO Auto-generated method stub
		System.out.println("inside update profile ++++++++++++++++++++");
		byte[] photoBytes;
		try {
			photoBytes = user.getImage().getBytes();
			String sql = "UPDATE students SET userName=?,course=?,specialisation=?,image=? ,gender = ? WHERE email = ?";
			 jdbcTemplate.update(sql, new Object[]
				        {  user.getUserName(),user.getCourse(),user.getSpecialisation(),photoBytes,user.getGender() , user.getEmail()}
				        );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
