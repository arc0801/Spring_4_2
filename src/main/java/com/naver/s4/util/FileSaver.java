package com.naver.s4.util;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.springframework.web.multipart.MultipartFile;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	
	//file Delete
	public boolean fileDelete(String realpath, String filename) throws Exception {
		File file = new File(realpath, filename);
		
		boolean check = false;
		
		if(file.exists()) {
			check = file.delete();
		}		
		
		return check;
	}
	
	
	
	/////
	
	public String save0(String realpath, MultipartFile multipartFile) throws Exception {
		System.out.println(realpath);
		File file = new File(realpath);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		Calendar ca = Calendar.getInstance();
		String time = String.valueOf(ca.getTimeInMillis());
		
		
		String filename = time +"_"+multipartFile.getOriginalFilename();
		
		file = new File(realpath, filename);
		
		//1.
		/* FileCopyUtils.copy(multipartFile.getBytes(), file); */
		
		//2.
		multipartFile.transferTo(file);
		
		//3.
		/*
		 * FileOutputStream fo = new FileOutputStream(file);
		 * fo.write(multipartFile.getBytes()); fo.close();
		 */
		
		
		return filename;
	}


	
	//1. Spring에서 제공하는 fileCopyUtils라는 클래스의 copy메서드를 사용함.
	public String save(String realPath, MultipartFile multipartFile) throws Exception {
		//realPath 받기
		File file = new File(realPath);
		//System.out.println(file.exists());
		//true면 존재, false면 부존재
		
		if(!file.exists()) {
			file.mkdirs();
			//없는거 다 만들어준다.			
		}
		
		//file.mkdir();
		//마지막의 폴더만 만든다.
		
		//System.out.println(file.exists());
		//System.out.println(file.isDirectory());
		
		//시간으로 파일명 바꾸기 (중복방지 방법1)
		Calendar calendar =  Calendar.getInstance();
		Long name = calendar.getTimeInMillis();
		
		String tree = multipartFile.getOriginalFilename();
		String end =  tree.substring(tree.indexOf("."));
		String fileName = String.valueOf(name)+end;
		file = new File(realPath, fileName);
		
		//save1
		FileCopyUtils.copy(multipartFile.getBytes(), file);
		
		return fileName; 
	}
	

	//2. multipartfile : transferTo메서드 사용
	public String save2(String realPath, MultipartFile multipartFile) throws Exception {
		File file = new File(realPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		//자바 클래스 안에 랜덤한 이름을 만들어주는 애들이 있다.(중복방지 방법2)
		String filename = UUID.randomUUID().toString();
		filename = filename+"_"+multipartFile.getOriginalFilename();
		file = new File(realPath, filename);
		
		
		//save2
		multipartFile.transferTo(file);
		
		return filename;
	}
	
	//3. Io Stream 사용
	public String save3(String realPath, MultipartFile multipartFile) throws Exception  {
		File file = new File(realPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		String filename = UUID.randomUUID().toString();
		filename = filename+"_"+multipartFile.getOriginalFilename();
		file = new File(realPath, filename);
		
		
		//save3
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(multipartFile.getBytes());
		fo.close();
		
		return filename;
	}
	
	
}
