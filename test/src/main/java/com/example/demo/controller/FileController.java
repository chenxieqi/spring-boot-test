package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
public class FileController {
	
	@Autowired
	private UserService userService;
	 //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "I://csv_FILE//";

	@PostMapping("/upload")
	public ModelAndView uploadFile(@RequestParam("file") MultipartFile file) {
		ModelAndView modelAndView =new ModelAndView();
		modelAndView.setViewName("success");
		InputStream inputStream=null;
		OutputStream outputStream=null;
		try {
			inputStream=file.getInputStream();
			outputStream=new FileOutputStream(UPLOADED_FOLDER+file.getOriginalFilename());
			IOUtils.copy(inputStream, outputStream);
			
			ReadCSV readCsv=new ReadCSV();
			List<User> users=readCsv.read(UPLOADED_FOLDER+file.getOriginalFilename());
			User olduser=null;
			for(User user:users) {
				olduser=userService.findById(user.getId());
				if(olduser!=null) {
					userService.update(user);
				}else {
					userService.create(user);
				}		
			}
			modelAndView.addObject("sucess","upload sucess");
			return modelAndView;
		}catch(IOException ex){
			ex.printStackTrace();
		}finally {
			if(inputStream!=null) {
				try {
					inputStream.close();
				}catch(IOException ex){
					ex.printStackTrace();
				}
			}
			if(outputStream!=null) {
				try {
					outputStream.close();
				}catch(IOException ex){
					ex.printStackTrace();
				}
			}
		}
		modelAndView.addObject("failed","upload failed");
		return modelAndView;
	}
	
	@RequestMapping("/download")
	public void downloadFile(HttpServletRequest request,HttpServletResponse response) {
		
		response.setCharacterEncoding(request.getCharacterEncoding());
		response.setContentType("application/octet-stream");
		FileInputStream inputstream=null;
		try {		
			List<User> users=userService.findAll();
			CreateCSV createCSV=new CreateCSV();
			String fileName=createCSV.createCSV(users,UPLOADED_FOLDER);

			File file =new File(UPLOADED_FOLDER+fileName+".csv");
			inputstream=new FileInputStream(file);
			response.setHeader("Content-Disposition", "attachment;filename="+file.getName());
			IOUtils.copy(inputstream,response.getOutputStream());
			response.flushBuffer();
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			if(inputstream!=null) {
				try {
					inputstream.close();
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
