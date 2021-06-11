package com.ncu.springsecurity.demo.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ncu.springsecurity.demo.Mail.JavaMailSender;

import com.ncu.springsecurity.demo.Model.RegisterModel;
import com.ncu.springsecurity.demo.Model.StudentModel1;
import com.ncu.springsecurity.demo.dao.RegisterDao;
import com.ncu.springsecurity.demo.dao.RegisterDaoImp;
import com.ncu.springsecurity.demo.dao.StudentDaoImp;

@Controller
public class LoginController {
	@Autowired
	private RegisterDaoImp registerDao;
	
	  @Autowired
	    private JavaMailSender emailSender;
	  
		@Autowired
		private StudentDaoImp studentDao;

	  private final String from = "serenity007.pvt.ltd@gmail.com";
	  private String sys_otp;
	  private String final_email = "";
	  private StudentModel1 student = new StudentModel1(); 

	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		
		// return "plain-login";

		return "fancy-login";
		
	}
	
	// add request mapping for /access-denied
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "access-denied";
		
	}
	
	@GetMapping("/register")
	public String register(@ModelAttribute("user") RegisterModel user) {
		return "emailAuth";
	}
	

	
	@RequestMapping(value = "/signUp" ,method = RequestMethod.POST)
	public String signUp(Model m ,@ModelAttribute("user") RegisterModel user) throws IOException {
		
	System.out.println(user);
    try {
	registerDao.saveOrUpdate(user);
    } catch (Exception e) {
	// TODO: handle exception
    	return "emailAuth";
    }

	     
		 return "fancy-login";
	}
	
	@RequestMapping("/emailAuthanticate")
	public String emailAuth(@RequestParam(required = false, name = "email") String email,Model m) {
		final_email = email;
		//check if email is already been registerd
		student = studentDao.get(email);
		if(student!=null) {
			System.out.println("c");
			m.addAttribute("invalid", "email already exist");
			return "emailAuth";
		}
		//generate otp
		 Random r = new Random();
		    sys_otp = String.format("%04d", r.nextInt(1001));
		    System.out.println(sys_otp);
		    
		    //send mail otp
			 emailSender.getJavaMailSender().send(new MimeMessagePreparator() {
					
					@Override
					public void prepare(MimeMessage mimeMessage) throws Exception {
						// TODO Auto-generated method stub
						MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
						mimeMessageHelper.setTo(email);
						mimeMessageHelper.setFrom(from);
						mimeMessageHelper.setText(sys_otp);
						mimeMessageHelper.setSubject("OTP");
						
						
					}
				});
			 m.addAttribute("email", email);
		return "OTP";
	}
	
	@RequestMapping(value = "/otp" ,method = RequestMethod.GET)
	public String otp(Model m,@RequestParam(required = false, name = "otp") String otp,@ModelAttribute("user") RegisterModel user) {
	            System.out.println(otp);
	            if(sys_otp.equals(otp)) {
	            	m.addAttribute("email", final_email );
	            	return "register";
	            }
		return "OTP";
	}
	

	
}









