package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.User;

@Mapper
public interface UserMapper {
	
	List<User> findAll();

	User findById(String id);
	
	void create(User user);
	
	void delete(String id);
	
	void update(User user);
	
	User findByName(String name);
}
