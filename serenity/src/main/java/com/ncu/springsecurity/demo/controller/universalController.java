package com.ncu.springsecurity.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ncu.springsecurity.demo.Model.LikeModel;
import com.ncu.springsecurity.demo.dao.LikeDao;
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
