package com.ncu.springsecurity.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ncu.springsecurity.demo.Model.CommentModel;
import com.ncu.springsecurity.demo.Model.LikeModel;
import com.ncu.springsecurity.demo.Model.PostsModel;
import com.ncu.springsecurity.demo.Model.StudentModel1;
import com.ncu.springsecurity.demo.dao.CommentDao;
import com.ncu.springsecurity.demo.dao.LikeDao;
import com.ncu.springsecurity.demo.dao.PostDaoImp;
import com.ncu.springsecurity.demo.dao.StudentDaoImp;

@Controller
public class universalController {
	String userName = "";
	String role1 = "";
	String role2 = " ";
	Collection<? extends GrantedAuthority> currentUserRole = null;
    List<GrantedAuthority> authorities
      = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(DemoController.class);
    
	@Autowired
	private LikeDao likeDao;
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private StudentDaoImp studentDao;
	
	@Autowired PostDaoImp postDao;
	
	@RequestMapping(value = "/like/{id}")
	private ModelAndView like(@PathVariable("id") String id) {
		userDetails();
		Integer i = Integer.parseInt(id);
		LOGGER.info(userName +  "  wants to like post " + i ); 
		LikeModel like = new LikeModel();
		like.setEmail(userName);like.setPost_id(i); 
		try {
			likeDao.saveOrUpdate(like);
		} catch (Exception e) {
			// TODO: handle exception
			likeDao.delete(like);
			return new ModelAndView("redirect:/");
		}
		
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/comment/{id}")
	private ModelAndView comment(@PathVariable("id") String id,@RequestParam(required = false, name = "comment") String comment) {
		if(comment.equals("")) {
			return new ModelAndView("redirect:/");
		}
		userDetails();
		Integer i = Integer.parseInt(id);
		LOGGER.info(userName + " wants to comment " + comment + " on post id " + i);
		CommentModel obj = new CommentModel();
		obj.setPost_id(i);
		obj.setEmail(userName);
		obj.setComment(comment);
	    commentDao.saveOrUpdate(obj);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/deleteComment/{id}")
	private ModelAndView deleteComment(@PathVariable("id") String id) {
	
		Integer i = Integer.parseInt(id);
		LOGGER.info( i + " id comment to be deleted");
		commentDao.delete(i);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/userProfile/{user:.+}")
	public String userProfile(@PathVariable("user") String user,Model m) {
		int likeCount;
		HashMap<Integer, Integer> map = new HashMap<>();
		StudentModel1 profile = studentDao.get(user);
		List<PostsModel> list = postDao.userPostlist(user);
		
		
		//likes
		List<LikeModel> likeList = likeDao.list();
		m.addAttribute("likeList", likeList);
		
		for(PostsModel x: list) {
			likeCount = likeDao.count(x.getId());
			map.put(x.getId(), likeCount);
		}
		m.addAttribute("likeCount", map);
		
		//comments
				List<CommentModel> comments = commentDao.list();
				m.addAttribute("commentList", comments);
				
		m.addAttribute("postList", list);
		m.addAttribute("userProfile", profile);
		return "user";
	}
	
	//report post
	@RequestMapping(value = "/reportPost/{id}")
	private ModelAndView reportPost(@PathVariable("id") int id) {
		userDetails();
		LOGGER.info(userName+" is reporting post " + id);
		return new ModelAndView("redirect:/");
	}
	
	//function
	private void userDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    Collection<? extends GrantedAuthority> currentUserRole1 = authentication.getAuthorities();
		    System.out.println("authorities ----" + currentUserRole1);
		    currentUserRole =  currentUserRole1;
		    userName = currentUserName;
		    }
	    for (GrantedAuthority grantedAuthority : currentUserRole) {
			authorities.add(grantedAuthority);
		}
	}
}
