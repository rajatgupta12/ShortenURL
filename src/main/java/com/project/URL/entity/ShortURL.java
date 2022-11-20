package com.project.URL.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.redis.core.RedisHash;

@Entity
@Table(name="surls")
public class ShortURL
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="key", unique=true)
	private String key;
	
	@Column(name="url")
	private String url;
	
	@Column(name="orgurl")
	private String orgurl;
	
	@CreationTimestamp
	@Column(name="createdtime")
	private java.sql.Timestamp createdtime;

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

	public java.sql.Timestamp getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(java.sql.Timestamp createdtime) {
		this.createdtime = createdtime;
	}
	
	@Override
	public String toString() {
		return "ShortURL [id=" + id + ", key=" + key + ", url=" + url + ", orgurl=" + orgurl + ", createdtime="
				+ createdtime + "]";
	}

	public ShortURL(long id, String key, String url, String orgurl, java.sql.Timestamp createdtime) {
		super();
		this.id = id;
		this.key = key;
		this.url = url;
		this.orgurl = orgurl;
		this.createdtime = createdtime;
	}

	public ShortURL() {
		super();
	}
}
