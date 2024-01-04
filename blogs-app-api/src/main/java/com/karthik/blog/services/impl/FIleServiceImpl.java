package com.karthik.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.karthik.blog.services.FileService;

@Service
public class FIleServiceImpl implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		
		//File Name
		String name=file.getOriginalFilename();
		String randomId=UUID.randomUUID().toString();
		String fileName1=randomId.concat(name.substring(name.lastIndexOf(".")));
		//Full Path
		
		String filePath=path+File.separator+fileName1;
		//create Image folder if not created
		File f=new File(path);
		
		if(!f.exists()) {
			f.mkdir();
		}
	
		
		
		//file copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return name;
	}

	@Override
	public InputStream getResourses(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		String fullPath=path+File.separator+fileName;
		
		InputStream is=new FileInputStream(fullPath);
		return is;
	}

}
