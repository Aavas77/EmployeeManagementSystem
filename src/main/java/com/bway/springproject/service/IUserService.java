package com.bway.springproject.service;

import com.bway.springproject.model.User;


public interface IUserService {

	void signup(User user);

	User login(String un, String psw);

	User isUserExist(String un);
	
	User isMailExist(String mail);

}
