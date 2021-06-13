package com.ncu.springsecurity.demo.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ncu.springsecurity.demo.Model.PostsModel;
import com.ncu.springsecurity.demo.Model.StudentModel1;
import com.ncu.springsecurity.demo.Model.TotalMarks;

@Repository
@Transactional
public class PostDaoImp implements PostsDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void saveOrUpdate(PostsModel post)  {
		// TODO Auto-generated method stub
		try {
			byte[] photoBytes = post.getImage1().getBytes();
			byte[] videoBytes = post.getVideo1().getBytes();
			
			String sql = "INSERT INTO posts(email,title ,body,image,video) VALUES (?,?,?,?,?)";
			 jdbcTemplate.update(sql, new Object[]
				        {  post.getEmail(), post.getTitle(),post.getBody(),photoBytes,videoBytes}
				        );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM posts WHERE id='" + id + "'";
		jdbcTemplate.update(sql);
		System.out.println(id+" post deleted");
		
	}

	@Override
	public PostsModel get(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from posts where id = '" + id +"'";
		PostsModel post = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(PostsModel.class));
		System.out.println("phone nmber:-------------------------------------------------------------"+ post);
		return post;
	}

	
	@Override
	public List<PostsModel> userPostlist(String email) {
		String sql = "select * from posts where email = '" + email +"'";
		
		List<PostsModel> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<PostsModel>(PostsModel.class)); 
		
		return list;

	}

	@Override
	public void Update(PostsModel post) {
		// TODO Auto-generated method stub
		try {
			if(post.getImage1()==null && post.getVideo1() == null) {
				String sql = "UPDATE posts SET email=?,title=?,body=? WHERE id = ?";
				 jdbcTemplate.update(sql, new Object[]
					        {  post.getEmail(), post.getTitle(),post.getBody(), post.getId()}
					        );
			}else {
			byte[] photoBytes = post.getImage1().getBytes();
			byte[] videoBytes = post.getVideo1().getBytes();
		String sql = "UPDATE posts SET email=?,title=?,body=?,image=?,video=? WHERE id = ?";
		
		 jdbcTemplate.update(sql, new Object[]
			        {  post.getEmail(), post.getTitle(),post.getBody(),photoBytes,videoBytes, post.getId()}
			        );
			}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	@Override
	public void Mark(PostsModel post) {
		// TODO Auto-generated method stub
		String sql = "UPDATE posts SET points = ? WHERE id = ?";
		 jdbcTemplate.update(sql, new Object[]
			        {  post.getPoints(),post.getId()}
			        );
	}

	@Override
	public List<PostsModel> list() {
		String sql = "select * from posts";
		
		List<PostsModel> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<PostsModel>(PostsModel.class)); 
		
		return list;

	}
	
	@Override
	public List<TotalMarks> highestScorers() {
		String sql = "SELECT email,SUM(points) AS marks FROM posts GROUP BY  email ORDER BY SUM(points) DESC";
		
		List<TotalMarks> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<TotalMarks>(TotalMarks.class)); 
		
		return list;

	}

	@Override
	public void delete(String email) {
		// TODO Auto-generated method stub
		
	}

}
