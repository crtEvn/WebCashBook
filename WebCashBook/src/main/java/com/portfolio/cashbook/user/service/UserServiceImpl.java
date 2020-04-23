package com.portfolio.cashbook.user.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.portfolio.cashbook.user.dao.UserDAO;
import com.portfolio.cashbook.user.dto.SignDTO;
import com.portfolio.cashbook.user.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource(name="userDAO")
	private UserDAO userDAO;

	@Override
	public UserVO selectUser(SignDTO signDTO) throws Exception {
		
		return userDAO.selectUser(signDTO);
	}
	
	@Override
	public int checkUser_id(String user_id) throws Exception {
		
		return userDAO.dupCheckUser_id(user_id);
	}
	
	@Override
	public void insertUser(SignDTO signDTO) throws Exception {
		
		userDAO.insertUser(signDTO);
	}

	@Override
	public Map<String, String> validateHandling(Errors errors) throws Exception {

		Map<String, String> validatorResult = new HashMap<String, String>();
				
		for(FieldError error : errors.getFieldErrors()) {
			String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
		}
		
		return validatorResult;
	}
	
	
	
	

}
