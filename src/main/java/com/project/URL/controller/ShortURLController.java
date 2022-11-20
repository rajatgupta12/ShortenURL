package com.project.URL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.project.URL.entity.RedisShortURL;
import com.project.URL.entity.ShortURL;
import com.project.URL.exception.ResourceNotFoundException;
import com.project.URL.service.ShortURLService;

import java.util.*;

@RestController
public class ShortURLController 
{
	@Autowired
	RedisTemplate<String, Object> redisTemp;
	
	public static final String HASH_KEY = "RedisShortURL";
	
	private ShortURLService shortserv;
	
	ShortURLController(ShortURLService shortserv)
	{
		this.shortserv = shortserv;
	}
	
	@GetMapping("/create-url")
	public ResponseEntity<ShortURL> create(@RequestParam String str) throws ResourceNotFoundException
	{
		System.out.println("OriginalURL " + str);
		
		ShortURL obj = shortserv.createObject(str);
		RedisShortURL redisobj = shortserv.createRedisObject(str);
		
		redisTemp.opsForHash().put(HASH_KEY, String.valueOf(redisobj.getId()), redisobj);
		
		return new ResponseEntity<ShortURL>(shortserv.createUrl(obj), HttpStatus.CREATED);
	}
	
	@GetMapping("/get-url/{key}")
	public ResponseEntity<Object> getUrl(@PathVariable("key") String key) throws ResourceNotFoundException
	{
		List<RedisShortURL> redisurls =  (List<RedisShortURL>)(Object)(redisTemp.opsForHash().values(HASH_KEY));
		
		for (RedisShortURL url : redisurls)
		{
			System.out.println(url);
			if (key.equals(url.getKey()))
			{
				return new ResponseEntity<Object>(url, HttpStatus.OK);
			}
		}
		System.out.printf("Key %s does not exist in redis, so finding in DB\n", key);
		
		ShortURL obj = shortserv.getUrl(key);
		
		RedisShortURL redisobj = shortserv.createRedisObjectWithKey(key, obj.getOrgurl());
		
		redisTemp.opsForHash().put(HASH_KEY, String.valueOf(redisobj.getId()), redisobj);
		
		return new ResponseEntity<Object>(obj, HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<ShortURL>> getAll()
	{
		return new ResponseEntity<List<ShortURL>>(shortserv.getAll(), HttpStatus.OK);
	}
}
