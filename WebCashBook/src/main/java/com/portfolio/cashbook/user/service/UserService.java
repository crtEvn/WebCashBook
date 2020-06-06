package com.portfolio.cashbook.user.service;

import java.util.Map;

import org.springframework.validation.Errors;

import com.portfolio.cashbook.user.dto.SignDTO;
import com.portfolio.cashbook.user.vo.UserVO;

public interface UserService {
	
	UserVO selectUser(SignDTO signDTO) throws Exception;
	
	int checkUser_id(String user_id) throws Exception;
	
	void insertUser(SignDTO signDTO) throws Exception;
	
	Map<String, String> validateHandling(Errors errors) throws Exception;
	
	void insertFirstCategory(String user_idx) throws Exception;
	
}
