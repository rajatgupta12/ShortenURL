package com.project.URL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.URL.entity.User;
import com.project.URL.service.impl.UserService;

@RestController
@RequestMapping("/users")
public class UserController 
{
	@Autowired
	private UserService userserv;
	
	@GetMapping("/add")
	public User addUser(@RequestBody User user)
	{
		System.out.println(user);
		return userserv.adduser(user);
	}
}
