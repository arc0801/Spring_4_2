package com.naver.s4.dao;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;

import com.naver.s4.TestAbstractCase;
import com.naver.s4.model.BoardVO;

public class BoardNoticeDAOTest extends TestAbstractCase {

	@Inject
	private BoardNoticeDAO boardNoticeDAO;
	
	@Test
	public void test() throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("ti");
		boardVO.setWriter("wr");
		boardVO.setContents("con");
		
		int result = boardNoticeDAO.boardWrite(boardVO);

		assertEquals(1, result);
	}

}
