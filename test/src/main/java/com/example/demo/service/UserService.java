package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService extends BaseService<User>{
	
	User findByName(String name);
}
