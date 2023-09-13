package com.bway.springproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springproject.model.User;
import com.bway.springproject.repository.UserRepository;
import com.bway.springproject.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void signup(User user) {
		userRepo.save(user);
		
	}

	@Override
	public User login(String un, String psw) {
		
		return userRepo.findByUsernameAndPassword(un, psw);
	}

	@Override
	public User isUserExist(String un) {
		
		return userRepo.findByUsername(un);
	}

	@Override
	public User isMailExist(String mail) {
		
		return userRepo.findByEmail(mail);
	}

}
