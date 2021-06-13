package com.ncu.springsecurity.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

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

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ncu.springsecurity.demo.Model.CommentModel;
import com.ncu.springsecurity.demo.Model.LikeModel;
import com.ncu.springsecurity.demo.Model.PostsModel;
import com.ncu.springsecurity.demo.Model.RegisterModel;
import com.ncu.springsecurity.demo.Model.StudentModel;
import com.ncu.springsecurity.demo.Model.StudentModel1;
import com.ncu.springsecurity.demo.Model.ToBeTeacher;
import com.ncu.springsecurity.demo.dao.CommentDao;
import com.ncu.springsecurity.demo.dao.LikeDao;
import com.ncu.springsecurity.demo.dao.PostDaoImp;
import com.ncu.springsecurity.demo.dao.PostsDao;
import com.ncu.springsecurity.demo.dao.RegisterDao;
import com.ncu.springsecurity.demo.dao.RegisterDaoImp;
import com.ncu.springsecurity.demo.dao.StudentDaoImp;

@Controller
public class DemoController {
	String userName = "";
	String role1 = "";
	String role2 = " ";
	Collection<? extends GrantedAuthority> currentUserRole = null;
    List<GrantedAuthority> authorities
      = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(DemoController.class);
    
	@Autowired
	private StudentDaoImp studentDao;
	
	@Autowired
	private PostDaoImp postDao;
	
    
	@Autowired
	private LikeDao likeDao;
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private RegisterDaoImp registerDao; 

	@RequestMapping(value = "/" )
	public String showHome(Model m, @ModelAttribute("stud") StudentModel stud, @ModelAttribute("posts") PostsModel posts) throws SQLException {
		StudentModel1 student  = new StudentModel1();
        int likeCount; 
        TreeMap<Integer, Integer> map = new TreeMap<>();
        HashMap<Integer,Integer> commentCount = new HashMap<Integer,Integer>();
		//set username and authentication from spring security
		userDetails();

	    
	    System.out.println(authorities.get(0));
	    
	    for(int i =0 ; i<authorities.size();i++) {
	    	if(i==0) {
	    		role1 = authorities.get(i).toString();
	    		 LOGGER.info("----role1 " + role1);
	    	}
	    	if(i==1) {
	    		role2  =  authorities.get(i).toString();
	    		 LOGGER.info("----role2 " + role2);
	    	}
	    	
		    if(role1.equals("ROLE_Student") && role2.equals("ROLE_Teacher")) {
                System.out.println("----walla you are in teacher ");
 	
		    }
		    if(role1.equals("ROLE_ADMIN")) {
                LOGGER.info("----walla you are a admin login");
                List<ToBeTeacher> list=registerDao.toBeTeacherList();
                m.addAttribute("toBeTeacherList", list);
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
		
		//likes
		List<LikeModel> likeList = likeDao.list();
		m.addAttribute("likeList", likeList);
		
		for(PostsModel x: posts1) {
			likeCount = likeDao.count(x.getId());
			map.put(x.getId(), likeCount);
		}
		
		LOGGER.info(" likes "+map);
		m.addAttribute("likeCount", map);
		
		//comments
		List<CommentModel> comments = commentDao.list();
		m.addAttribute("commentList", comments);
		return "home";
	}
	
	
	
	
	// add request mapping for /leaders

	@GetMapping("/teachers")
	public String showLeaders(Model m) {
	    List<PostsModel>list = postDao.list();
		m.addAttribute("allPosts",list);
		return "teacherControlPannel";
	}
	
	// add request mapping for /systems
	
	@GetMapping("/systems")
	public String showSystems(Model m) {
		for(int i =0 ; i<authorities.size();i++) {
	    	if(i==0) {
	    		role1 = authorities.get(i).toString();
	    		 LOGGER.info("----role1 " + role1);
	    	}
	    	if(i==1) {
	    		role2  =  authorities.get(i).toString();
	    		 LOGGER.info("----role2 " + role2);
	    	}
	    	if(role1.equals("ROLE_ADMIN")) {
                LOGGER.info("----walla you are a admin login");
                List<ToBeTeacher> list=registerDao.toBeTeacherList();
                m.addAttribute("toBeTeacherList", list);
		    }
	    }
		return "systems";
	}
	
	//Admin all members list
	@GetMapping("/systems/allMembers")
	public String allMembers(Model m) {
		List<StudentModel> list = registerDao.listOfAllUsers();
		LOGGER.info("all users " + list);
		m.addAttribute("allUserNames",list);
		return "AllUsers";
	}
	
	//delete user
	@GetMapping("/systems/deleteUser/{user:.+}")
	public ModelAndView allMembers(@PathVariable("user") String user) {
        LOGGER.info(user + " to be deleted");
        registerDao.deleteUser(user);
		return new ModelAndView("redirect:/systems/allMembers");
	}
	
	@RequestMapping(value = "/admin/{id}")
	public void getIdCard(HttpServletResponse response, @PathVariable("id") int id) throws Exception {
		
		response.setContentType("image/jpeg/video/mp4");
		System.out.println("idCard id-----------" + id);
		ToBeTeacher teacher = new ToBeTeacher();
		teacher = registerDao.getToBeTeacher(id);
        System.out.println(teacher);
		Blob ph = teacher.getIdCard();

		byte[] bytes = ph.getBytes(1, (int) ph.length());
		System.out.println("bytes--------------------------------"+bytes);
		
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}
	
	@RequestMapping(value = "/teacherORNot/id/{id}/user/{user}/permission/{permission}")
	public ModelAndView permissionToBeTeacher(@PathVariable("id") int id,@PathVariable("permission") String permission ,@PathVariable("user") String user) {
		System.out.println(id + permission);
		if(permission.equals("{approve}")) {
			registerDao.approveToBeTeacher(user);
			registerDao.deleteToBeTeacher(id);
			LOGGER.info(id + " teacher to be approved");
		}else if(permission.equals("{deny}")) {
			registerDao.deleteToBeTeacher(id);
			LOGGER.info(id + " teacher to be denied");
		}
		return new ModelAndView("redirect:/systems");
	}
	
	@RequestMapping(value = "/buildProfile" ,method = RequestMethod.POST)
	public ModelAndView buildProfile(Model m,@ModelAttribute("stud") StudentModel stud,@RequestParam("file") MultipartFile file) throws IOException {
		stud.setImage(file);
		StudentModel1 user  = new StudentModel1();
		try {
			studentDao.saveOrUpdate(stud);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return new ModelAndView("redirect:/buildProfile");
		}
		
		System.out.println("JDBC STUDENT "+studentDao.get(stud.getUserName()));
		
		user = studentDao.get(userName);
		m.addAttribute("student", user);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/getStudentPhoto/{email:.+}")
	public void getStudentPhoto(HttpServletResponse response, @PathVariable("email") String email) throws Exception {
		response.setContentType("image/jpeg/video/mp4");
		System.out.println("lob-----------" + email);
		StudentModel1 student  = new StudentModel1();
		 student = studentDao.get(email);
        System.out.println(student);
		Blob ph = student.getImage();

		byte[] bytes = ph.getBytes(1, (int) ph.length());
		System.out.println("bytes--------------------------------"+bytes);
		
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}
	
	@RequestMapping(value = "/getPostclip/id/{id}/type/{type}")
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
	public ModelAndView post( @ModelAttribute("posts") PostsModel posts,@RequestParam("image1") MultipartFile image,@RequestParam("video1") MultipartFile video) {
        posts.setImage1(image);
        posts.setVideo1(video);
        posts.setPoints(0);
        postDao.saveOrUpdate(posts);
		System.out.println(posts);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "yourPosts")
	public String yourPosts( Model m) {
		 TreeMap<Integer, Integer> map = new TreeMap<>();
		 int likeCount;
		userDetails();
		System.out.println("showing all pasts of " + userName);
		List<PostsModel> posts = postDao.userPostlist(userName);
		m.addAttribute("posts", posts);
		//likes
				List<LikeModel> likeList = likeDao.list();
				m.addAttribute("likeList", likeList);
				
				for(PostsModel x: posts) {
					likeCount = likeDao.count(x.getId());
					map.put(x.getId(), likeCount);
				}
				
				LOGGER.info("your likes "+map);
				m.addAttribute("likeCount", map);
				
				//comments
				List<CommentModel> comments = commentDao.list();
				m.addAttribute("commentList", comments);
			
		return "posts";
	}
	
	@RequestMapping(value = "yourPosts/delete/{id}")
	public ModelAndView deletePost(@PathVariable("id") String id) {
		Integer i = Integer.parseInt(id);
		LOGGER.info("id to be deleted ====" + i);
		System.out.println("id to be deleted ====" + i);
		try {
			postDao.delete(i);
			LOGGER.info(i + " profile deleted");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.warn(i +" WAS NOT DELETED BEC   ..." + e);
		}
		
		return new ModelAndView("redirect:/yourPosts");
	}
	
	@RequestMapping(value = "yourPosts/update/{id}")
	public String updatePost(@PathVariable("id") String id ,  @ModelAttribute("posts") PostsModel posts, Model m) {
		
		Integer i = Integer.parseInt(id);
		LOGGER.info(i + " to be updated");
		PostsModel post = postDao.get(i);
		m.addAttribute("post",post);
		return "updatePost";
	}
	
	@RequestMapping(value = "yourPosts/update/updatePost" , method =RequestMethod.POST)
	public ModelAndView updatedPost( @ModelAttribute("posts") PostsModel posts, Model m,@RequestParam(name = "image1", required = false) MultipartFile image,@RequestParam(name ="video1" , required = false) MultipartFile video,@RequestParam("id") String id) {
        LOGGER.info("image  " + image + "video " + video);
		Integer i = Integer.parseInt(id);
		posts.setId(i);
		System.out.println(posts);
		LOGGER.info(posts + " to be updated");
		postDao.Update(posts);
		return new ModelAndView("redirect:/yourPosts ");
	}
	
	@RequestMapping(value = "/profile")
	public String updateProfile(@ModelAttribute("stud") StudentModel stud,Model m) {
		userDetails();
		StudentModel1 student  = new StudentModel1();
		 student = studentDao.get(userName);
		    System.out.println("jdbc student  " + student+ "user " + userName);

		    m.addAttribute("student", student);
		return "updateProfile";
	}
	
	@RequestMapping(value = "/updateProfile")
	public ModelAndView updatedProfile(@ModelAttribute("stud") StudentModel stud, @RequestParam("file") MultipartFile file, Model m) throws IOException {
		String duplicate = null;
		userDetails();
		stud.setEmail(userName);
		LOGGER.info("profile to be updated ----- " + stud + "  image " + file);
		stud.setImage(file);
		try {
			studentDao.Update(stud);
		} catch (Exception e) {
			// TODO: handle exception
			return new ModelAndView("redirect:/profile");
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










