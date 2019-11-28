package com.naver.s4.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.naver.s4.model.BoardVO;
import com.naver.s4.service.BoardNoticeService;
import com.naver.s4.util.Pager;

@RestController
public class JsonController {

	@Inject
	private BoardNoticeService boardNoticeService;
	
	@GetMapping("/getJson3")
	//@ResponseBody
	public List<BoardVO> getJson3(Pager pager) throws Exception {
		
		return boardNoticeService.boardList(pager);
	}
	
	@GetMapping("/getJson2")
	//@ResponseBody //리턴되는 정보가 주소가 아닌 data이므로 view로 가지말고 바로 요청한 곳으로 가라
	public BoardVO getJson2(BoardVO boardVO) throws Exception {
		//ModelAndView mv = new ModelAndView();
		boardVO = boardNoticeService.boardSelect(boardVO);
		//String jmsg = "{\"title\":\""+boardVO.getTitle()+"\", \"writer\":\""+boardVO.getWriter()+"\", \"contents\":\""+boardVO.getContents()+"\"}";
		//System.out.println(jmsg);
		
		//mv.addObject("result", jmsg);
		//mv.setViewName("common/common_ajaxResult");
		
		return boardVO;
	}
	
	@GetMapping("/getJson1")
	public ModelAndView getJson1() throws Exception  {
		//name-iu, age=27
		String jmessage = "{\"name\":\"iu\", \"age\":27}";
		System.out.println(jmessage);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("result", jmessage);
		mv.setViewName("common/common_ajaxResult");
		
		return mv;
	}
}
