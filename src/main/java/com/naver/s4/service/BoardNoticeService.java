package com.naver.s4.service;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.naver.s4.dao.BoardNoticeDAO;
import com.naver.s4.dao.NoticeFilesDAO;
import com.naver.s4.model.BoardNoticeVO;
import com.naver.s4.model.BoardVO;
import com.naver.s4.model.FilesVO;
import com.naver.s4.util.FileSaver;
import com.naver.s4.util.Pager;

@Service
@Transactional
public class BoardNoticeService implements BoardService {

	@Inject
/*	@Qualifier("BoardNoticeDAO")*/
	private BoardNoticeDAO boardNoticeDAO;
	
	@Inject
	private FileSaver fileSaver;
	
	@Inject
	private NoticeFilesDAO noticeFilesDAO;
	
	@Value("${notice}")
	private String board;
	
	//summer 
	public String summerFile(MultipartFile file, HttpSession session) throws Exception {
		String realpath = session.getServletContext().getRealPath("resources/upload/summer");
		return fileSaver.save0(realpath, file);
	}
	
	public boolean summerFileDelete(String file, HttpSession session) throws Exception {
		String realpath = session.getServletContext().getRealPath("resources/upload/summer");
		return fileSaver.fileDelete(realpath, file);
	}
	
	
	
	//
	public int fileDelete(FilesVO noticeFilesVO) throws Exception {
		return noticeFilesDAO.fileDelete(noticeFilesVO);
	}
	
	public FilesVO fileSelect(FilesVO noticeFilesVO) throws Exception {
		return noticeFilesDAO.fileSelect(noticeFilesVO);
	}
	
	
	
	//
	@Override
	public List<BoardVO> boardList(Pager pager) throws Exception {
		pager.makeRow();
		pager.makePager(boardNoticeDAO.boardCount(pager));
		
		return boardNoticeDAO.boardList(pager);
	}

	@Override
	public BoardVO boardSelect(BoardVO boardVO) throws Exception {
		boardVO = boardNoticeDAO.boardSelect(boardVO);
		//BoardNoticeVO boardNoticeVO = (BoardNoticeVO)boardVO;
		//List<NoticeFilesVO> ar = noticeFilesDAO.fileList(boardVO.getNum());
		//boardNoticeVO.setFiles(ar);
		//reselt map�씠�씪�뒗 留ㅽ띁�꽕�젙 �씠�썑 �쐞媛� �븘�슂�뾾�뼱吏�
		
		//return boardNoticeVO;
		
		return boardVO;
	}

	
	//@Transactional
	@Override
	public int boardWrite(BoardVO boardVO, MultipartFile [] file, HttpSession session) throws Exception {
		String realpath = session.getServletContext().getRealPath("resources/upload/"+board);
		String filename = "";
		System.out.println(realpath);
		
		session.setAttribute("realpath", realpath);
		
		FilesVO filesVO = new FilesVO();
		
		int result = boardNoticeDAO.boardWrite(boardVO);
		filesVO.setNum(boardVO.getNum());
		
		
		for(MultipartFile files : file) { 
			if(files.getOriginalFilename() != "") {
				filename = fileSaver.save0(realpath,files); 
				filesVO.setFname(filename);
				filesVO.setOname(files.getOriginalFilename());

				result = noticeFilesDAO.fileWrite(filesVO); 
				
				if(result < 1) {
					throw new SQLException();
				}
			
			}
		}
		  
		 // boardVO.setFilename(filename);
		 // boardVO.setOriginalname(boardVO.getFile().getOriginalFilename());
		  
		 // System.out.println(filename);
		 // System.out.println(boardVO.getFile().getOriginalFilename());
		 // System.out.println(realpath);
		  
		 // boardNoticeDAO.boardWriteFile(boardVO); 
	 	 // boardNoticeDAO.boardWrite(boardVO);
		 
		
		return result;
	}

	//@Transactional
	@Override
	public int boardUpdate(BoardVO boardVO, MultipartFile [] file, HttpSession session) throws Exception {
		String realpath = session.getServletContext().getRealPath("resources/upload/notice"); 
		String filename = "";
		session.setAttribute("realpath", realpath); 
		
		int result = boardNoticeDAO.boardUpdate(boardVO); 
		boardVO = boardNoticeDAO.boardSelect(boardVO);
		
		BoardNoticeVO bVO = (BoardNoticeVO)boardVO;
		
		System.out.println("�썝�옒 :"+ bVO.getFiles().size());
		System.out.println(file);
		System.out.println("異붽� :"+file.length);
		
		FilesVO filesVO = new FilesVO(); 
		filesVO.setNum(boardVO.getNum());
			
			for(MultipartFile files : file) {
				if(files.getOriginalFilename() != "") {
					//files.getSize() != 0
					filename = fileSaver.save0(realpath, files);
					filesVO.setFname(filename);
					filesVO.setOname(files.getOriginalFilename());

					result = noticeFilesDAO.fileWrite(filesVO);
				}
			}

		return result;
	}

	@Override
	public int boardDelete(BoardVO boardVO) throws Exception {
		return boardNoticeDAO.boardDelete(boardVO);
	}


}
