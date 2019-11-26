package com.naver.s4.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.naver.s4.model.BoardQnaVO;
import com.naver.s4.model.BoardVO;
import com.naver.s4.util.Pager;

@Repository
public class BoardQnaDAO implements BoardDAO {
	
	@Inject
	private SqlSession sqlSession;
	public final static String NAMESPACE = "qnaMapper.";
	
	
	@Override
	public List<BoardVO> boardList(Pager pager) throws Exception {
		return sqlSession.selectList(NAMESPACE+"boardList", pager);
	}

	@Override
	public int boardCount(Pager pager) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"boardCount", pager);
	}

	@Override
	public BoardVO boardSelect(BoardVO boardVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"boardSelect", boardVO);
	}

	@Override
	public int boardWrite(BoardVO boardVO) throws Exception {
		return sqlSession.insert(NAMESPACE+"boardWrite", boardVO) ;
	}

	@Override
	public int boardUpdate(BoardVO boardVO) throws Exception {
		return sqlSession.update(NAMESPACE+"boardUpdate", boardVO);
	}

	@Override
	public int boardDelete(BoardVO boardVO) throws Exception {
		return sqlSession.delete(NAMESPACE+"boardDelete", boardVO);
	}

	public int boardReplyUpdate(BoardVO boardVO) throws Exception  {
		return sqlSession.update(NAMESPACE+"boardReplyUpdate", boardVO);
	}
	
	public int boardReply(BoardVO boardVO) throws Exception {
		return sqlSession.insert(NAMESPACE+"boardReply", boardVO);
	}
	
}
