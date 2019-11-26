package com.naver.s4.service;

import java.io.File;
import java.util.Calendar;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.naver.s4.dao.MemberDAOImpl;
import com.naver.s4.model.MemberVO;
import com.naver.s4.util.FileSaver;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject
	private MemberDAOImpl memberDAOImpl;

	@Override
	public int memberJoin(MemberVO memberVO, HttpSession session) throws Exception {
		//Server의 HDD에 저장
		// /resources/upload
		
		//파일을 저장할 실제 경로
		String realPath = session.getServletContext().getRealPath("resources/upload/member");
		
		FileSaver fileSaver = new FileSaver();
		String filename =  fileSaver.save3(realPath, memberVO.getFile());
		//String filename = fileSaver.save(realPath, memberVO.getFile());
		
		memberVO.setFilename(filename);
		memberVO.setOriginalname(memberVO.getFile().getOriginalFilename());
		
		return memberDAOImpl.memberJoin(memberVO);
	}

	@Override
	public MemberVO memberIdCheck(MemberVO memberVO) throws Exception {
		return memberDAOImpl.memberIdCheck(memberVO);
	}
	
	
	@Override
	public MemberVO memberEmailCheck(MemberVO memberVO) throws Exception {
		return memberDAOImpl.memberEmailCheck(memberVO);
	}

	@Override
	public MemberVO memberLogin(MemberVO memberVO) throws Exception {
		return memberDAOImpl.memberLogin(memberVO);
	}

	@Override
	public int memberUpdate(MemberVO memberVO) throws Exception {
		return memberDAOImpl.memberUpdate(memberVO);
	}

	@Override
	public int memberDelete(MemberVO memberVO) throws Exception {
		return memberDAOImpl.memberDelete(memberVO);
	}

	@Override
	public int memberPointUpdate(MemberVO memberVO) throws Exception {
		return 0;
	}

	@Override
	public MemberVO memberSearchId(MemberVO memberVO) throws Exception {
		return memberDAOImpl.memberSearchId(memberVO);
	}

	
	

}
