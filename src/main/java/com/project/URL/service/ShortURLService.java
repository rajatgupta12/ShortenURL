package com.project.URL.service;

import com.project.URL.entity.ShortURL;
import com.project.URL.exception.ResourceNotFoundException;

import java.util.List;

import com.project.URL.entity.RedisShortURL;

public interface ShortURLService 
{
	public ShortURL createUrl(ShortURL obj);
	public ShortURL getUrl(String key) throws ResourceNotFoundException;
	public ShortURL createObject(String str) throws ResourceNotFoundException;
	public RedisShortURL createRedisObject(String str) throws ResourceNotFoundException;
	public RedisShortURL createRedisObjectWithKey(String str, String orgURL);
	public List<ShortURL> getAll();
}
