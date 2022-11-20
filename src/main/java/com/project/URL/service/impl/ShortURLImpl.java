package com.project.URL.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.URL.repository.ShortURLRepo;
import com.project.URL.service.ShortURLService;
import com.project.URL.entity.RedisShortURL;
import com.project.URL.entity.ShortURL;
import com.project.URL.exception.ResourceNotFoundException;

@Service
public class ShortURLImpl implements ShortURLService
{
	long num = 10;
	String shortkey, url;
	
	ShortURLRepo shortrepo;
	
	ShortURLImpl(ShortURLRepo shortrepo)
	{
		this.shortrepo = shortrepo;
	}
	
	private String shortKey()
	{
		Random random = new Random();
        String tag = Long.toString(Math.abs(random.nextLong()), 36);
        System.out.println("Key Generated " + tag.substring(0,3));
        
        return tag.substring(0,3);
	}
	
	@Override
	public RedisShortURL createRedisObjectWithKey(String str, String orgurl)
	{
		RedisShortURL obj = new RedisShortURL();
		
		obj.setKey(str);
		
		String shorturl = "http://localhost:8080/" + str;
		obj.setUrl(shorturl);
		
		obj.setOrgurl(orgurl);
		
		double num = Math.random()*(500 - 1 + 1) + 1;
		obj.setId((int)num);
		
		return obj;
	}
	
	@Override
	public RedisShortURL createRedisObject(String str) throws ResourceNotFoundException
	{
		RedisShortURL redisobj = new RedisShortURL();
		
		if (shortkey.equals(""))
		{
			throw new ResourceNotFoundException("ShortURL", "original URL", str);
		}
		redisobj.setKey(shortkey);
		
		redisobj.setUrl(url);
		
		redisobj.setOrgurl(str);
		
		double num = Math.random()*(500 - 1 + 1) + 1;
		redisobj.setId((int)num);
		
		return redisobj;
	}
	
	@Override
	public ShortURL createObject(String str) throws ResourceNotFoundException
	{
		List<ShortURL> urls = shortrepo.findAll();
		
		for (ShortURL url : urls)
		{
			if (url.getOrgurl().equals(str))
			{
				shortkey = "";
				throw new ResourceNotFoundException("ShortURL", "original URL", str);
			}
		}
		
		ShortURL obj = new ShortURL();
		obj.setKey(shortKey());
		shortkey = obj.getKey();
		
		String shorturl = "http://localhost:8080/" + obj.getKey();
		obj.setUrl(shorturl);
		url = shorturl;
		
		obj.setOrgurl(str);
		//obj.setId(num++);
		
		return obj;
		
	}
	
	@Override
	public ShortURL createUrl(ShortURL obj)
	{	
		return shortrepo.save(obj);
	}
	
	@Override
	public ShortURL getUrl(String key) throws ResourceNotFoundException
	{
		List<ShortURL> urls = shortrepo.findAll();
		System.out.println("key " + key);
		
		for (ShortURL url : urls)
		{
			if (key.equals(url.getKey()))
			{
				return url;
			}
		}
		throw new ResourceNotFoundException(key);
	}
	
	public List<ShortURL> getAll()
	{
		return shortrepo.findAll();
	}
}
