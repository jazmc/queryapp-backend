package com.example.queryappbackend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MaintainerUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	@JsonIgnore
	@Column(name = "password", nullable = false)
	private String passwordHash;
	@JsonIgnore
	@Column(name = "role", nullable = false)
	private String role;
	
	public MaintainerUser() {}
	
	public MaintainerUser(Long id, String username, String passwordHash, String role) {
		this.id = id;
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}
	
	public MaintainerUser(String username, String passwordHash, String role) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
