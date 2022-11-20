package com.project.URL.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.URL.repository.UserRepo;
import com.project.URL.entity.CustomUserDetail;
import com.project.URL.entity.User;

@Service
public class CustomUserDetailsServ implements UserDetailsService
{
	@Autowired
	private UserRepo userrepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		List<User> users = userrepo.findAll();
		
		for (User user : users)
		{
			System.out.println(user);
			if (user.getUsername().equals(username))
			{
				System.out.println(user);
				return new CustomUserDetail(user);
			}
		}
		
		
		throw new UsernameNotFoundException("Not Found");
	}
	
}
