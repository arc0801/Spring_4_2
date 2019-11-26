package com.naver.s4.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.naver.s4.model.FilesVO;
import com.naver.s4.model.MemberVO;
import com.naver.s4.service.MemberServiceImpl;

@Controller
@RequestMapping("/member/**")
public class MemberController {

	@Inject
	private MemberServiceImpl memberServiceImpl;

	@GetMapping(value = "memberJoin")
	public void memberJoin() throws Exception {
	}

	@PostMapping(value = "memberJoin")
	public ModelAndView memberJoin(MemberVO memberVO, HttpSession session) throws Exception {
		/*
		 * System.out.println(memberVO.getFile().getName());
		 * System.out.println(memberVO.getFile().getOriginalFilename());
		 * System.out.println(memberVO.getFile().getSize());
		 */
		
		int result = memberServiceImpl.memberJoin(memberVO, session);
		 
		String msg = "회원가입 실패";
		 
		ModelAndView mv = new ModelAndView();
		
		
		if (result > 0) { msg = "회원가입 성공"; }
		  
		mv.addObject("msg", msg); 
		mv.addObject("path", "../");
		mv.setViewName("common/common_result");
		
		return mv;
	}

	
	@GetMapping(value = "memberIdCheck")
	public void memberIdCheck(String id, Model model, MemberVO memberVO) throws Exception {
		memberVO.setId(id);
		memberVO = memberServiceImpl.memberIdCheck(memberVO);
		
		if(memberVO == null) {
			model.addAttribute("msg", 1);
		} else {
			model.addAttribute("msg", 2);
		}
		
	}
	
	@GetMapping(value = "memberEmailCheck")
	public void memberEmailCheck(String email, Model model, MemberVO memberVO) throws Exception {
		memberVO.setId(email);
		memberVO = memberServiceImpl.memberEmailCheck(memberVO);
		
		if(memberVO == null) {
			model.addAttribute("msg", 1);
		} else {
			model.addAttribute("msg", 2);
		}
		
	}
	

	@GetMapping("memberLogin")
	public void memberLogin() throws Exception {
	}

	@PostMapping("memberLogin")
	public String memberLogin(MemberVO memberVO, HttpSession session) throws Exception {
		memberVO = memberServiceImpl.memberLogin(memberVO);
		
		
		if (memberVO != null) {
			String birth =  memberVO.getBirth();
			birth =  birth.substring(0, 10);
			memberVO.setBirth(birth);
			session.setAttribute("member", memberVO);
		}

		return "redirect:../";
	}

	@GetMapping("memberLogout")
	public String memberLogout(HttpSession session) throws Exception {
		// 1. 세션에 있는 vo지우기
		// session.removeAttribute("member");

		// 2. 세션의 시간을 0으로 만들어서 vo지우기
		session.invalidate();

		return "redirect:../";
	}

	@GetMapping("memberMypage")
	public void memberMypage() throws Exception {
	}

	@RequestMapping(value = "memberDelete")
	public ModelAndView memberDelete(MemberVO memberVO, HttpSession session) throws Exception {
		int result = memberServiceImpl.memberDelete(memberVO);
		String msg = "탈퇴 실패";

		ModelAndView mv = new ModelAndView();
		if (result > 0) {
			msg = "탈퇴 성공";
		}

		mv.addObject("msg", msg);
		mv.addObject("path", "../");
		mv.setViewName("common/common_result");

		session.invalidate();

		return mv;
	}

	@GetMapping("memberUpdate")
	public void memberUpdate() throws Exception {
	}

	@PostMapping("memberUpdate")
	public ModelAndView memberUpdate(MemberVO memberVO, HttpSession session) throws Exception {		
		int result = memberServiceImpl.memberUpdate(memberVO);
		String msg = "수정 실패";

		ModelAndView mv = new ModelAndView();
		if (result > 0) {
			msg = "수정 성공";
			session.setAttribute("member", memberVO);
		}

		mv.addObject("msg", msg);
		mv.addObject("path", "../");
		mv.setViewName("common/common_result");

		return mv;
	}
	
	@GetMapping("memberSearchId")
	public void memberSearchId() throws Exception {
	}
	
	@PostMapping("memberSearchId")
	public void memberSearchId(MemberVO memberVO) throws Exception{
		
	}

}
