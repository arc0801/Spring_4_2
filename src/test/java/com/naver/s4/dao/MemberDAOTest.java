package com.naver.s4.dao;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;

import com.naver.s4.TestAbstractCase;
import com.naver.s4.model.MemberVO;

public class MemberDAOTest extends TestAbstractCase {

	@Inject
	private MemberDAOImpl memberDAOImpl;
	
	@Test
	public void test() throws Exception {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("testid");
		memberVO.setPw("testpw");
		memberVO.setName("testname");
		memberVO.setEmail("test@test.com");
		memberVO.setBirth("1992-07-20");
		memberVO.setGender("F");
		
		int result =  memberDAOImpl.memberJoin(memberVO);
		
		assertEquals(1, result);
		
	}

}
