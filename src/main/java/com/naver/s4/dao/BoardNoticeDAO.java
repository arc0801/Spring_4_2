package com.naver.s4.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.naver.s4.model.BoardVO;
import com.naver.s4.util.Pager;

@Repository
public class BoardNoticeDAO implements BoardDAO {
	
	@Inject
	private SqlSession sqlSession;
	public final static String NAMESPACE = "noticeMapper.";
	
	
	@Override
	public List<BoardVO> boardList(Pager pager) throws Exception {
		return sqlSession.selectList(NAMESPACE+"boardList", pager);
	}

	@Override
	public int boardCount(Pager pager) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"boardCount", pager) ;
	}

	@Override
	public BoardVO boardSelect(BoardVO boardVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"boardSelect", boardVO);
	}

	@Override
	public int boardWrite(BoardVO boardVO) throws Exception {
		return sqlSession.insert(NAMESPACE+"boardWrite", boardVO) ;
	}
	
	public int boardWriteFile(BoardVO boardVO) throws Exception {
		return sqlSession.insert(NAMESPACE+"boardWriteFile", boardVO);
	}

	@Override
	public int boardUpdate(BoardVO boardVO) throws Exception {
		return sqlSession.update(NAMESPACE+"boardUpdate", boardVO) ;
	}

	@Override
	public int boardDelete(BoardVO boardVO) throws Exception {
		return sqlSession.delete(NAMESPACE+"boardDelete", boardVO);
	}

	
}
