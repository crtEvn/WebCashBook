package com.portfolio.cashbook.user.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.portfolio.cashbook.user.dao.UserDAO;
import com.portfolio.cashbook.user.dto.SignDTO;
import com.portfolio.cashbook.user.vo.UserVO;

@Service("signInService")
public class SignInServiceImpl implements SignInService{
	
	@Resource(name="userDAO")
	private UserDAO userDAO;
	
	@Resource(name="checkUserService")
	private CheckUserService checkUserService;
	
	Log log = LogFactory.getLog(this.getClass());
	
	// 로그인 시작
	@Override
	public boolean startSignIn(SignDTO signDTO, HttpSession session) throws Exception {
		log.debug("?");
		// 아이디 확인
		if(checkUserService.checkUser_ID(signDTO.getUser_id())) {
			
			UserVO userVO = checkUserService.selectUser(signDTO);
			
			// 비밀번호 확인
			if(checkUserService.checkUser_PW(signDTO.getUser_pw(), userVO.getUser_pw())) {
				
				session.setAttribute("userSession", userVO);
				log.debug("SignIn 성공");
				
				return true;		
			}
		}
		
		return false;
	}
	
}
