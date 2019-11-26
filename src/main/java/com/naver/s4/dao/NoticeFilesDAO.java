package com.naver.s4.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.naver.s4.model.FilesVO;

@Repository
public class NoticeFilesDAO {
	
	@Inject
	private SqlSession sqlSession; 
	private static final String NAMESPACE = "noticeFilesMapper.";

	
	public int fileWrite(FilesVO noticeFilesVO) throws Exception {
		return sqlSession.insert(NAMESPACE+"fileWrite", noticeFilesVO); 
	}
	
	public List<FilesVO> fileList(int num) throws Exception {
		return sqlSession.selectList(NAMESPACE+"fileList", num);
	}
	
	public int fileDelete(FilesVO noticeFilesVO) throws Exception {
		return sqlSession.delete(NAMESPACE+"fileDelete", noticeFilesVO);
	} 
	
	public FilesVO fileSelect(FilesVO noticeFilesVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"fileSelect", noticeFilesVO);
	}
	
	
}


