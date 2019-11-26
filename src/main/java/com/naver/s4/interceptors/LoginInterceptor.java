package com.naver.s4.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	//@Override
	//public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	//		throws Exception {
	//	// TODO Auto-generated method stub
	//	super.afterCompletion(request, response, handler, ex);
	//}
	
	//Controller 진입 전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//true : Controller로 전송
		//false : Controller로 전송 X
		boolean result = false;
		Object obj = request.getSession().getAttribute("member");
		if(obj != null) {
			result = true;
		}else {
			response.sendRedirect("../member/memberLogin");
		}
		
		return result;
	}
}
