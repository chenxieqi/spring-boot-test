package com.example.demo.entity;


public class User{
	
	private String id;
	private String username;
	private String password;
	private String address;
	private int phone;
	private int power;
	
	public User() {}
	
	public User(String username,String password,String address,int phone,int power) {
		this.username=username;
		this.password=password;
		this.address=address;
		this.phone=phone;
		this.power=power;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id=id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username=username;
	}
	public String getPassword() {
		return password;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setPassword(String password) {
		this.password=password;
	}
}
