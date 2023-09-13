package com.bway.springproject.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springproject.model.User;
import com.bway.springproject.service.IUserService;
import com.bway.springproject.utils.VerifyRecaptcha;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/user")
public class UserController {
	
	//private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/login")
	public String getLogin() {
		return "LoginForm";
	}
	
	@PostMapping("/login")
	public String postLogin(@ModelAttribute User user, Model model, HttpSession session, @RequestParam("g-recaptcha-response") String grcCode) throws IOException{
		
		if(VerifyRecaptcha.verify(grcCode)) {
			
			user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
			User usr = userService.login(user.getUsername(), user.getPassword());
			
			if(usr != null) {
				
				log.info("-------------------login success--------------------");
				
				session.setAttribute("validuser", usr);
				session.setMaxInactiveInterval(200);
				
				//model.addAttribute("user",usr);
				return "Home";
			} else {
				
				log.info("-------------login failed--------------------");
				model.addAttribute("message", "user not found!");
				return "LoginForm";
				
			}
		}
		
		log.info("-------------login failed--------------------");
		model.addAttribute("message", "no entry for stupid bots!!");
		return "LoginForm";
	}
	
	@GetMapping("/signup")
	public String getSignup() {
		
		return "SignUpForm";
	}
	
	@PostMapping("/signup")
	public String postSignUp(@ModelAttribute User user) {
		
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userService.signup(user);
		
		return "LoginForm";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		log.info("---------- user logout success -------------");
		
		session.invalidate();//session kill
		return "LoginForm";
	}
	
	@GetMapping("/home")
	public String home() {
		
		return "Home";
	}

	@GetMapping("/profile")
	public String getProfile() {
		
		return "ProfileForm";
	}
	
	@GetMapping("/facebook")
	public String facebookLogin() {
		
		return "";
	}
	
}
