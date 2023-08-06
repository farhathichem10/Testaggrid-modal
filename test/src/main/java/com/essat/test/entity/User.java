package com.essat.test.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class User {
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String datedeb;

	private String datefin;
	private String email;
	private String username;
	private String password;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDatedeb() {
		return datedeb;
	}
	public void setDatedeb(String datedeb) {
		this.datedeb = datedeb;
	}
	public String getDatefin() {
		return datefin;
	}
	public void setDatefin(String datefin) {
		this.datefin = datefin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User() {
		super();
	}
	public User(Integer id, String datedeb, String datefin, String email, String username, String password) {
		super();
		this.id = id;
		this.datedeb = datedeb;
		this.datefin = datefin;
		this.email = email;
		this.username = username;
		this.password = password;
	}


	


}
