package com.project.URL.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig 
{
	@Bean
    public LettuceConnectionFactory getConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration("redis-13920.c301.ap-south-1-1.ec2.cloud.redislabs.com", 13920);

        redisStandaloneConfiguration.setPassword("CJ0LK76mGUv9zccwBzWOP20rTQ8Z48c8");

        LettuceConnectionFactory lettuceConnectionFactory
                = new LettuceConnectionFactory(redisStandaloneConfiguration);

        return lettuceConnectionFactory;

    }
	
	@Bean
	public RedisTemplate<String, Object> template()
	{
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		
		template.setConnectionFactory(getConnectionFactory());
		
		/*template.setValueSerializer(new JdkSerializationRedisSerializer());
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new JdkSerializationRedisSerializer());*/
		
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
	    template.afterPropertiesSet();    
		
		return template;
	}
}
