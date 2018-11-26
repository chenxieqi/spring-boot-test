package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> findAll(){
		return userMapper.findAll();
	}
	
	@Override
	public User findById(String id){
		return userMapper.findById(id);
	}
	
	@Override
	public void create(User user) {
		userMapper.create(user);
	}
	
	@Override
	public void delete(String... ids) {
		for(String id:ids) {
			userMapper.delete(id);
		}
	}
	
	@Override
	public void update(User user) {
		userMapper.update(user);
	}
	
	@Override
	public User findByName(String name) {
		return userMapper.findByName(name);
	}
}
