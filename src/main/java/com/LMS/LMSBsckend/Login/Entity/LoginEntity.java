package com.LMS.LMSBsckend.Login.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Login")
public class LoginEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	private String name;
	private long phone;
	private String Username;
	private String Password;
	private String role;
		
	public LoginEntity() {
		super();
	}
	
	public LoginEntity(int id, String name, long phone, String username, String password,
			String role) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		Username = username;
		Password = password;
		this.role = role;
	}
	

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
