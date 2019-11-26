package com.naver.s4.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.naver.s4.model.FilesVO;

@Repository
public class QnaFilesDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String NAMESPACE = "qnaFilesMapper.";
	
	public int fileWrite(FilesVO FilesVO) throws Exception {
		return sqlSession.insert(NAMESPACE+"fileWrite", FilesVO);
	}
	
	public List<FilesVO> fileList(int num) throws Exception {
		return sqlSession.selectList(NAMESPACE+"fileList", num);
	}
	
	public int fileDelete(FilesVO FilesVO) throws Exception {
		return sqlSession.delete(NAMESPACE+"fileDelete", FilesVO);
	}
	
	public FilesVO fileSelect(FilesVO FilesVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"fileSelect", FilesVO);
	}
}
