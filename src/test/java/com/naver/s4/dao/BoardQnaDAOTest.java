package com.naver.s4.dao;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;

import com.naver.s4.TestAbstractCase;
import com.naver.s4.model.BoardVO;

public class BoardQnaDAOTest extends TestAbstractCase {

	@Inject
	private BoardQnaDAO boardQnaDAO;
	
	//@Test
	public void test() throws Exception {
		
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("daoTest");
		boardVO.setWriter("daoTest");
		boardVO.setNum(384);
		int result = boardQnaDAO.boardUpdate(boardVO);
		
		assertEquals(1, result);
		
	}
	
	@Test
	public void reply() throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(384);
		int result =  boardQnaDAO.boardReplyUpdate(boardVO);
	
		assertEquals(1, result);
	}

}
