package com.example.demo.service;

import java.util.List;

public interface BaseService<T> {
	
	// search all
	List<T> findAll();
	
	// search one
	T findById(String id);
	
	// add
	void create(T t);
	
	// delete 
	void delete(String... ids);
	
	// update
	void update(T t);
}
