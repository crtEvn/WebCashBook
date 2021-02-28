package com.portfolio.cashbook.user.service;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.portfolio.cashbook.user.dao.UserDAO;
import com.portfolio.cashbook.user.dto.SignDTO;
import com.portfolio.cashbook.user.vo.UserVO;

@Service("checkUserService")
public class CheckUserServiceImpl implements CheckUserService{

	@Resource(name="userDAO")
	private UserDAO userDAO;
	
	Log log = LogFactory.getLog(this.getClass());
	
	// 아이디 존재 여부 체크
	@Override
	public boolean checkUser_ID(String user_id) throws Exception {
		
		if(userDAO.dupCheckUser_id(user_id) == 1) {
			log.debug("user_id 일치");
			return true;
		}
		
		log.debug("user_id 불일치");
		return false;
	}
	
	// 비밀번호 일치 여부 체크
	@Override
	public boolean checkUser_PW(String input_user_pw, String get_user_pw) throws Exception {
		
		if(BCrypt.checkpw(input_user_pw, get_user_pw)) {
			log.debug("user_pw 일치");
			return true;
		}
		
		log.debug("user_pw 불일치");
		return false;
	}
	
	// 회원정보 불러오기
	@Override
	public UserVO selectUser(SignDTO signDTO) throws Exception {
		
		return userDAO.selectUser(signDTO);
	}
}
