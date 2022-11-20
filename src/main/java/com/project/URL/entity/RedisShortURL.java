package com.project.URL.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("RedisShortURL")
public class RedisShortURL implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -908378695479116987L;
	@Id
	private long id;
	private String key;
	private String url;
	private String orgurl;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOrgurl() {
		return orgurl;
	}

	public void setOrgurl(String orgurl) {
		this.orgurl = orgurl;
	}

	@Override
	public String toString() {
		return "RedisShortURL [id=" + id + ", key=" + key + ", url=" + url + ", orgurl=" + orgurl + "]";
	}

	public RedisShortURL(long id, String key, String url, String orgurl) {
		super();
		this.id = id;
		this.key = key;
		this.url = url;
		this.orgurl = orgurl;
	}

	public RedisShortURL() 
	{
		super();
	}
}
