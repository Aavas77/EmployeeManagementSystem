package com.bway.springproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bway.springproject.model.User;
import com.bway.springproject.service.IUserService;

@Controller
public class ForgotPasswordController {
	
	private IUserService userService;
	
	@GetMapping("/forgotpassword")
	public String getForgotPassword() {
		
		return "ForgotPasswordForm";
	}
	
	@PostMapping("/forgotpassword")
	public String sendNewPassword(@ModelAttribute User user, Model model) {
		
		return "ForgotPasswordForm";
	}

}
