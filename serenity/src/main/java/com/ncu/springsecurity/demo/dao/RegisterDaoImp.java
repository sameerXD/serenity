package com.ncu.springsecurity.demo.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ncu.springsecurity.demo.Model.CommentModel;
import com.ncu.springsecurity.demo.Model.LikeModel;
import com.ncu.springsecurity.demo.Model.PostsModel;
import com.ncu.springsecurity.demo.Model.RegisterModel;
import com.ncu.springsecurity.demo.Model.StudentModel;
import com.ncu.springsecurity.demo.Model.ToBeTeacher;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


class UserMapper implements RowMapper<RegisterModel>{

	@Override
	public RegisterModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		RegisterModel customer = new RegisterModel();
		//customer.setEmail(rs.getString(1));
		customer.setName(rs.getString(2));
		//customer.setAddress(rs.getString(3));
		//customer.setGender(rs.getString(4));
		//customer.setIncome(rs.getString(5));
		customer.setPassword(rs.getString(6));
		//customer.setPhnNum(rs.getString(7));
		return customer;
	}
	
}
@Repository
@Transactional
public class RegisterDaoImp implements RegisterDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void saveOrUpdate(RegisterModel user) {
		// TODO Auto-generated method stu
				String sql = "insert into users (username,password,enabled) values(?,?,?)";
                System.out.println("inside saveOrUpdate" );
		        jdbcTemplate.update(sql, new Object[]
		        {  user.getName(), "{noop}"+user.getPassword(),1}
		        );
		        
		         sql = "INSERT INTO `authorities` (username,authority) values(?,?)";
		        jdbcTemplate.update(sql, new Object[]
				        {  user.getName(), "ROLE_Student"}
				        );
		        
		        if(user.getRole().equals("ROLE_Teacher")) {
		        	byte[] photoBytes;
					try {
						photoBytes = user.getIdCard1().getBytes();
				         sql = "INSERT INTO `tobeteachers` (username,idCard) values(?,?)";
					     jdbcTemplate.update(sql, new Object[]
							        {  user.getName(), photoBytes}
							        );	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	
		        }
		
	}

	@Override
	public void delete(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RegisterModel get(String email) {
		// TODO Auto-generated method stub
		return null;
	}
//list of all users
	@Override
	public List<StudentModel> listOfAllUsers() {
		// TODO Auto-generated method stub
		String sql = "SELECT username  FROM users";
		List<StudentModel> list =jdbcTemplate.query(sql, new BeanPropertyRowMapper<StudentModel>(StudentModel.class));
		return list;
		
	}
	
	//delete a particular user
	
	@Override
	public void deleteUser(String email) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String sql = "DELETE FROM users WHERE username ='" + email + "'";
		jdbcTemplate.update(sql);
		System.out.println(email+" user deleted ");
		
	}

	@Override
	public void Update(RegisterModel user) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<ToBeTeacher> toBeTeacherList() {
		String sql = "select * from tobeteachers";
		
		List<ToBeTeacher> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ToBeTeacher>(ToBeTeacher.class)); 
		
		return list;
	}
	
	@Override
	public ToBeTeacher getToBeTeacher(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from tobeteachers where id = '" + id +"'";
		ToBeTeacher teacher = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(ToBeTeacher.class));
		System.out.println("phone nmber:-------------------------------------------------------------"+ teacher);
		return teacher;
	}
	
	@Override
	public void deleteToBeTeacher(int id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tobeteachers WHERE id='" + id + "'";
		jdbcTemplate.update(sql);
		System.out.println(id+" toBeTeacher deleted");
		
	}
	
	@Override
	public void approveToBeTeacher(String userName) {
        String sql = "INSERT INTO `authorities` (username,authority) values(?,?)";
       jdbcTemplate.update(sql, new Object[]
		        {  userName, "ROLE_Teacher"}
		        );
	}
	

}
