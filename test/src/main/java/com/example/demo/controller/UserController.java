package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
public class UserController {

		@Autowired
		private UserService userService;
		
		@RequestMapping("/findAll")
		public List<User> findAll(){
			return userService.findAll();
		}
}
