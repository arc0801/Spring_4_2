package com.naver.s4.interceptors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.naver.s4.model.BoardVO;
import com.naver.s4.model.MemberVO;

@Component
public class UpdateInterceptor extends HandlerInterceptorAdapter {

	//Controller 종료 후
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//id
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		Map<String, Object> map = modelAndView.getModel();
		String board = (String)map.get("board");
		BoardVO boardVO = (BoardVO)map.get("dto");
		if(!memberVO.getId().equals(boardVO.getWriter())) {
			modelAndView.setViewName("common/common_result");
			modelAndView.addObject("msg", "권한이 없습니다.");
			modelAndView.addObject("path", board+"List");
		}
	}                      
	
}
