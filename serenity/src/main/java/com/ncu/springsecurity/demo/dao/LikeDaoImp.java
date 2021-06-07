package com.ncu.springsecurity.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ncu.springsecurity.demo.Model.LikeModel;
import com.ncu.springsecurity.demo.Model.PostsModel;

@Repository
@Transactional
public class LikeDaoImp implements LikeDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public void delete(LikeModel like) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM likes WHERE post_id=? AND email = ?";
		jdbcTemplate.update(sql, new Object[]
		        {  like.getPost_id(),like.getEmail()}
		        );
		System.out.println(" like deleted");
	}

	@Override
	public LikeModel get(int post_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LikeModel> list() {
		// TODO Auto-generated method stub
		String sql = "select * from likes";
		
		List<LikeModel> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<LikeModel>(LikeModel.class)); 
		
		return list;
	}

	@Override
	public void saveOrUpdate(LikeModel like) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO likes(post_id,email) VALUES (?,?)";
		 jdbcTemplate.update(sql, new Object[]
			        {  like.getPost_id(),like.getEmail()}
			        );
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int count(int post_id) {
		// TODO Auto-generated method stub
		
				String sql = "select count(*) from likes where post_id = '" + post_id +"'";
				return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}
