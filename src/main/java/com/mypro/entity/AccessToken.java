package com.mypro.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ACCESS_TOKEN")
@TableGenerator(name = "ACCESS_TOKEN_SEQ", table = "t_sequence", pkColumnName = "gen_name", pkColumnValue = "gen_value", allocationSize = 1)
public class AccessToken {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ACCESS_TOKEN_SEQ")
	@Column(length = 10)
	private Long id;

	private String accessToken;

	private int expireTime;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date saveTime;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getSaveTime() {
		return saveTime;
	}

	public void setSaveTime(Date saveTime) {
		this.saveTime = saveTime;
	}

}
