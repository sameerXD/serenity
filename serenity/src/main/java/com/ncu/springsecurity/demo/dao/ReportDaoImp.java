/**
 * 
 */
package com.ncu.springsecurity.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ncu.springsecurity.demo.Model.LikeModel;
import com.ncu.springsecurity.demo.Model.ReportCountModel;
import com.ncu.springsecurity.demo.Model.ReportModel;

/**
 * @author SamxD
 *
 */

@Repository
@Transactional
public class ReportDaoImp implements ReportDao {

	/* (non-Javadoc)
	 * @see com.ncu.springsecurity.demo.dao.ReportDao#saveOrUpdate(com.ncu.springsecurity.demo.Model.ReportModel)
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public void saveOrUpdate(ReportModel report) {
		// TODO Auto-generated method stub
		String sql = "insert into report (email,post_id) values(?,?)";
        System.out.println("inside report saveOrUpdate" );
        jdbcTemplate.update(sql, new Object[]
        {  report.getEmail(), report.getPost_id()}
        );

	}

	/* (non-Javadoc)
	 * @see com.ncu.springsecurity.demo.dao.ReportDao#delete(int)
	 */
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.ncu.springsecurity.demo.dao.ReportDao#get(int)
	 */
	@Override
	public LikeModel get(int post_id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ncu.springsecurity.demo.dao.ReportDao#list()
	 */
	@Override
	public List<ReportModel> list() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.ncu.springsecurity.demo.dao.ReportDao#count(int)
	 */
	@Override
	public int count(int post_id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public List<ReportCountModel> countAll() {
		// TODO Auto-generated method stub
        String sql = "SELECT post_id,COUNT(post_id) AS count FROM report GROUP BY  post_id ORDER BY COUNT(post_id) DESC";
		
		List<ReportCountModel> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<ReportCountModel>(ReportCountModel.class));
		return list;
	}


	/* (non-Javadoc)
	 * @see com.ncu.springsecurity.demo.dao.ReportDao#delete(com.ncu.springsecurity.demo.Model.ReportModel)
	 */
	@Override
	public void delete(ReportModel report) {
		// TODO Auto-generated method stub

	}

}
