package com.naver.s4.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.naver.s4.model.FilesVO;

@Component 
public class FileDown extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("FILE DOWN CLASS");	
			
			FilesVO filesVO = (FilesVO)model.get("file");
			//System.out.println(noticeFilesVO.getFname());
			//System.out.println(noticeFilesVO.getOname());
			
			String board =  (String)model.get("board");
			//System.out.println(board);
			
			String realpath = request.getSession().getServletContext().getRealPath("resources/upload/"+board);
			//System.out.println(realpath);
			
			File file = new File(realpath, filesVO.getFname());
			
			//파일명이 한글일 경우를 대비해 인코딩 : 한글처리
			response.setCharacterEncoding("UTF-8");
			
			//파일의 크기 : file.length()
			response.setContentLength((int)file.length());
			
			//다운로드시에 파일이름을 인코딩
			String filename = URLEncoder.encode(filesVO.getOname(), "UTF-8");
			
			//나머지 헤더를 설정
			response.setHeader("Content-disposition", "attachment;filename=\""+filename+"\"");
			response.setHeader("Content-transfer-Encoding", "binary");
			
			//outputStream으로 연결
			FileInputStream fi = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
			
			//내보내기
			FileCopyUtils.copy(fi, os);
			
			os.close();
			fi.close();
	}

}
