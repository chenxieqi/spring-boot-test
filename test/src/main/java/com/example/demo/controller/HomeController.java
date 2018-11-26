package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.User;
import com.example.demo.form.UserForm;
import com.example.demo.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value= {"/","/index"})
	public String index(Model model) {
		model.addAttribute("UserForm",new UserForm());
		return "index";
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String userFormSubmit(@ModelAttribute UserForm userForm,Model model) {
		User user=userService.findById(userForm.getId());		
		if(user!=null)
		{
			if(user.getPassword().equals(userForm.getPassword()))
			{
				model.addAttribute("UserForm",userForm);
				return "edit";
			}
		}
		model.addAttribute("UserForm",new UserForm());
		return "redirect:/index";
	}
}
