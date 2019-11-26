package com.naver.s4.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.naver.s4.dao.BoardQnaDAO;
import com.naver.s4.dao.QnaFilesDAO;
import com.naver.s4.model.BoardQnaVO;
import com.naver.s4.model.BoardVO;
import com.naver.s4.model.FilesVO;
import com.naver.s4.util.FileSaver;
import com.naver.s4.util.Pager;
import com.sun.org.apache.regexp.internal.recompile;

@Service
public class BoardQnaService implements BoardService {
	
	@Inject
	private BoardQnaDAO boardQnaDAO;
	
	@Inject
	private FileSaver fileSaver;

	@Inject
	private QnaFilesDAO qnaFilesDAO;
	
	
	//file
	public int fileDelete(FilesVO filesVO) throws Exception {
		return qnaFilesDAO.fileDelete(filesVO);
	}
	
	
	public FilesVO fileSelect(FilesVO filesVO) throws Exception {
		return qnaFilesDAO.fileSelect(filesVO);
	}
	
	
	
	//
	@Override
	public List<BoardVO> boardList(Pager pager) throws Exception {
		pager.makeRow();
		pager.makePager(boardQnaDAO.boardCount(pager));
		
		return boardQnaDAO.boardList(pager);
	}

	
	@Override
	public BoardVO boardSelect(BoardVO boardVO) throws Exception {
		boardVO = boardQnaDAO.boardSelect(boardVO);
		/*
		 * BoardQnaVO boardQnaVO = (BoardQnaVO)boardVO; 
		 * List<QnaFilesVO> ar = qnaFilesDAO.fileList(boardVO.getNum()); 
		 * boardQnaVO.setFiles(ar);
		 */
		
		//return boardQnaVO;
		return boardVO;
	}

	@Override
	public int boardWrite(BoardVO boardVO, MultipartFile [] file, HttpSession session) throws Exception {
		String realpath = session.getServletContext().getRealPath("resources/upload/qna");
		String filename = "";
		
		FilesVO filesVO = new FilesVO();
		
		int result = boardQnaDAO.boardWrite(boardVO);
		filesVO.setNum(boardVO.getNum());

		for(MultipartFile files : file) {
			filename = fileSaver.save0(realpath, files);
			filesVO.setFname(filename);
			filesVO.setOname(files.getOriginalFilename());
			
			result = qnaFilesDAO.fileWrite(filesVO);
		}
		
		return result;
	}

	@Override
	public int boardUpdate(BoardVO boardVO, MultipartFile [] file, HttpSession session) throws Exception {
		return boardQnaDAO.boardUpdate(boardVO);
	}

	@Override
	public int boardDelete(BoardVO boardVO) throws Exception {
		return boardQnaDAO.boardDelete(boardVO) ;
	}
	

	public int boardReply(BoardVO boardVO) throws Exception {
		int result = boardQnaDAO.boardReplyUpdate(boardVO);
			result = boardQnaDAO.boardReply(boardVO);
		return result;
	}
	

}
