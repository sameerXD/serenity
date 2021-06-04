package com.ncu.springsecurity.demo.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ncu.springsecurity.demo.Model.RegisterModel;
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
			         sql = "INSERT INTO `authorities` (username,authority) values(?,?)";
				     jdbcTemplate.update(sql, new Object[]
						        {  user.getName(), user.getRole()}
						        );		        	
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

	@Override
	public List<RegisterModel> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(RegisterModel user) {
		// TODO Auto-generated method stub
		
	}
	

}
