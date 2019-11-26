package com.naver.s4.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.naver.s4.model.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	private SqlSession sqlSession;
	public final static String NAMESPACE = "memberMapper.";
	
	
	@Override
	public int memberJoin(MemberVO memberVO) throws Exception {
		return sqlSession.insert(NAMESPACE+"memberJoin", memberVO) ;
	}

	@Override
	public MemberVO memberIdCheck(MemberVO memberVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"memberIdCheck", memberVO);
	}
	
	@Override
	public MemberVO memberLogin(MemberVO memberVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"memberLogin", memberVO) ;
	}

	@Override
	public int memberUpdate(MemberVO memberVO) throws Exception {
		return sqlSession.update(NAMESPACE+"memberUpdate", memberVO);
	}

	@Override
	public int memberDelete(MemberVO memberVO) throws Exception {
		return sqlSession.delete(NAMESPACE+"memberDelete", memberVO);
	}

	@Override
	public int memberPointUpdate(MemberVO memberVO) throws Exception {
		return 0;
	}

	@Override
	public MemberVO memberSearchId(MemberVO memberVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"memberSearchId", memberVO);
	}

	@Override
	public MemberVO memberEmailCheck(MemberVO memberVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"memberEmailCheck", memberVO);
	}
	
	

	
	

}
