package com.example.demo.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.User;
import com.opencsv.CSVReader;

public class ReadCSV {
	
	
	public List<User> read(String file_path){
		List<User> users=new ArrayList<>();
		try {
			CSVReader  csvReader=new CSVReader(new FileReader(file_path));
			String[] record=null;
			while((record=csvReader.readNext())!=null)
			{
				User user=new User();
				user.setId(record[0]);
				user.setUsername(record[1]);
				user.setPassword(record[2]);
				user.setAddress(record[3]);
				user.setPhone(Integer.parseInt(record[4]));
				user.setPower(Integer.parseInt(record[5]));
				users.add(user);
			}
			csvReader.close();
			return users;
		}catch(IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
