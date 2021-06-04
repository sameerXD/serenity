package com.ncu.springsecurity.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ncu.springsecurity.demo.Model.PostsModel;
import com.ncu.springsecurity.demo.Model.StudentModel;
import com.ncu.springsecurity.demo.Model.StudentModel1;
import com.ncu.springsecurity.demo.dao.PostDaoImp;
import com.ncu.springsecurity.demo.dao.PostsDao;
import com.ncu.springsecurity.demo.dao.StudentDaoImp;

@Controller
public class DemoController {
	String userName = "";
	String role1 = "";
	String role2 = " ";
	Collection<? extends GrantedAuthority> currentUserRole = null;
    List<GrantedAuthority> authorities
      = new ArrayList<>();
    
    
	@Autowired
	private StudentDaoImp studentDao;
	
	@Autowired
	private PostDaoImp postDao;

	@RequestMapping(value = "/" )
	public String showHome(Model m, @ModelAttribute("stud") StudentModel stud, @ModelAttribute("posts") PostsModel posts) throws SQLException {
		StudentModel1 student  = new StudentModel1();
 
		//set username and authentication from spring security
		userDetails();

	    
	    System.out.println(authorities.get(0));
	    
	    for(int i =0 ; i<authorities.size();i++) {
	    	if(i==0) {
	    		role1 = authorities.get(i).toString();
	    	}
	    	if(i==1) {
	    		role2  =  authorities.get(i).toString();
	    	}
	    	
		    if(role1.equals("ROLE_Student") && role2.equals("ROLE_Teacher")) {
                System.out.println("----walla you are in teacher ");
 	
		    }
		    
		    student = studentDao.get(userName);
		    System.out.println("jdbc student  " + student+ "user " + userName);

		    m.addAttribute("student", student);
		}
		if(student==null) {
			m.addAttribute("userName1", userName);
			return "buildProfile";
		}
		//all posts 
		List<PostsModel> posts1 = postDao.list();
		m.addAttribute("posts1", posts1);
		
		return "home";
	}
	
	// add request mapping for /leaders

	@GetMapping("/teachers")
	public String showLeaders() {
		
		return "leaders";
	}
	
	// add request mapping for /systems
	
	@GetMapping("/systems")
	public String showSystems() {
		
		return "systems";
	}
	
	@RequestMapping(value = "/buildProfile" ,method = RequestMethod.POST)
	public String buildProfile(Model m,@ModelAttribute("stud") StudentModel stud,@RequestParam("file") MultipartFile file) throws IOException {
		stud.setImage(file);
		StudentModel1 user  = new StudentModel1();
		try {
			studentDao.saveOrUpdate(stud);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return "buildProfile";
		}
		
		System.out.println("JDBC STUDENT "+studentDao.get(stud.getUserName()));
		
		user = studentDao.get(userName);
		m.addAttribute("student", user);
		return "home";
	}
	
	@RequestMapping(value = "/getStudentPhoto/{email}")
	public void getStudentPhoto(HttpServletResponse response, @PathVariable("email") String email) throws Exception {
		response.setContentType("image/jpeg/video/mp4");
		System.out.println("lob-----------" + email);
		StudentModel1 student  = new StudentModel1();
		 student = studentDao.get(email+".com");
        System.out.println(student);
		Blob ph = student.getImage();

		byte[] bytes = ph.getBytes(1, (int) ph.length());
		System.out.println("bytes--------------------------------"+bytes);
		
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}
	
	@RequestMapping(value = "/yourPosts/getPostclip/id/{id}/type/{type}")
	public void getPostClip(HttpServletResponse response, @PathVariable(value = "id") int id, @PathVariable(value = "type") String type) throws SQLException, IOException {
		response.setContentType("image/jpeg/video/mp4"); 
		Blob ph = null ;
		PostsModel post=postDao.get(id);
		System.out.println("type " + type);
		if(type.equals("{image}")) {
			ph = post.getImage();
		}
		if(type.equals("{video}")) {
			System.out.println("its a video");
			 ph = post.getVideo();
			
		}
		byte[] bytes = ph.getBytes(1, (int) ph.length());
		System.out.println("bytes--------------------------------"+bytes);
		
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}
	
	
	@RequestMapping(value = "/post" ,method = RequestMethod.POST)
	public String post( @ModelAttribute("posts") PostsModel posts,@RequestParam("image1") MultipartFile image,@RequestParam("video1") MultipartFile video) {
        posts.setImage1(image);
        posts.setVideo1(video);
        postDao.saveOrUpdate(posts);
		System.out.println(posts);
		return "xyz";
	}
	
	@RequestMapping(value = "yourPosts/{email}")
	public String yourPosts(@PathVariable("email") String email , Model m) {
		System.out.println("showing all pasts of " + email+".com");
		List<PostsModel> posts = postDao.userlist(email+".com");
		m.addAttribute("posts", posts);
		return "posts";
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










