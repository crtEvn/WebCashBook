package com.portfolio.cashbook.common.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.portfolio.cashbook.common.logger.LoggerInterceptor;

public class AuthInterceptor extends HandlerInterceptorAdapter{
	
	protected Log log = LogFactory.getLog(LoggerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession(false);
		Object userSession = session.getAttribute("userSession");
		
		if(userSession == null) {
			log.debug("no userSession");
			if(!request.getRequestURI().equals("/cashbook/user/signin.do")) {
				response.sendRedirect(request.getContextPath()+"/user/signin.do");
			}
		}else {
			// userSession이 존제할 경우
			if(request.getRequestURI().equals("/cashbook/user/signin.do")) {
				response.sendRedirect(request.getContextPath()+"/ledger/main.do");
			}
		}
		
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
	
}
