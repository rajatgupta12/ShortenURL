package com.project.URL.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.URL.service.impl.CustomUserDetailsServ;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private CustomUserDetailsServ customuserdetail;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.userDetailsService(customuserdetail)
		.passwordEncoder(this.passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http
		.authorizeRequests()
		.antMatchers("/create-url").hasRole("USER")
		.antMatchers("/get-url/**", "/users/**").permitAll()
		.antMatchers("/getall").hasRole("ADMIN")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder(10);
	}
	
}
