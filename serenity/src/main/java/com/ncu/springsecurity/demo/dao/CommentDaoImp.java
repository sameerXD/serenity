package com.ncu.springsecurity.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ncu.springsecurity.demo.Model.CommentModel;
import com.ncu.springsecurity.demo.Model.LikeModel;

@Repository
@Transactional
public class CommentDaoImp implements CommentDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void saveOrUpdate(CommentModel comment) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO comments(post_id,email,comment) VALUES (?,?,?)";
		 jdbcTemplate.update(sql, new Object[]
			        {  comment.getPost_id(),comment.getEmail(),comment.getComment()}
			        );
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM comments WHERE id = ?";
		jdbcTemplate.update(sql, new Object[]
		        { id}
		        );
		
	}

	@Override
	public CommentModel get(int post_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentModel> list() {
		String sql = "SELECT * FROM comments";
		List<CommentModel> list =jdbcTemplate.query(sql, new BeanPropertyRowMapper<CommentModel>(CommentModel.class));
		return list;
	}

	@Override
	public int count(int post_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(CommentModel comment) {
		// TODO Auto-generated method stub
		
	}

}
