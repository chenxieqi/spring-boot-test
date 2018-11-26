package com.example.demo.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.example.demo.entity.User;
import com.opencsv.CSVWriter;

public class CreateCSV {

	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public String createCSV(List<User> users,String path)throws IOException {
		StringWriter writer=new StringWriter();
		
		CSVWriter csvWriter=new CSVWriter(writer);
		
		List<String[]> data=toStringArray(users);
		csvWriter.writeAll(data);
		csvWriter.close();
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		String Stringdate=format.format(date);
		BufferedWriter out=new BufferedWriter(new FileWriter(path+Stringdate+".csv"));
		out.write(writer.toString());
		out.close();
		
		return Stringdate;
	}
	
	private static List<String[]> toStringArray(List<User> users){
		List<String[]> records=new ArrayList<String[]>();
		
		records.add(new String[] {"ID","USERNAME","ADDRESS","PHONE","POWER"});
		
		Iterator<User> it=users.iterator();
		while(it.hasNext()) {
			User user=it.next();
			records.add(new String[] {user.getId(),user.getUsername(),user.getAddress(),
					Integer.toString(user.getPhone()),Integer.toString(user.getPower())});
			}
		return records;
		}
}
