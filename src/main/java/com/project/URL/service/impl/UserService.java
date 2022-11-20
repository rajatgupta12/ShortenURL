package com.project.URL.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.URL.repository.UserRepo;
import com.project.URL.entity.User;

@Service
public class UserService 
{
	@Autowired
	private UserRepo userrepo;
	@Autowired
	BCryptPasswordEncoder bcrryppasswordencoder;
	
	public User adduser(User user)
	{
		user.setPassword(bcrryppasswordencoder.encode(user.getPassword()));
		return userrepo.save(user);
	}
}
