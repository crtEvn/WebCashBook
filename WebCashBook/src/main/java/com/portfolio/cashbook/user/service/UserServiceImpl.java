package com.portfolio.cashbook.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

	@Override
	public void insertFirstCategory(String user_idx) throws Exception {
		
		String[] expenditure = {"식비","주거비","교통비","통신비","보험비","생활비","교육비","기타소비지출"};
		String[] income = {"근로소득","금융소득","기타소득"};
		
		Map<String, String> ctgrMap = null;
		
		// Insert Expenditure Category
		for(int a=0; a<expenditure.length; a++) {
			
			ctgrMap = new HashMap<String, String>();
			ctgrMap.put("user_idx", user_idx);
			ctgrMap.put("category_ex_name", expenditure[a]);
			
			userDAO.insertCategory_ex(ctgrMap);
		}
		
		// Insert Income Category
		for(int a=0; a<income.length; a++) {
			
			ctgrMap = new HashMap<String, String>();
			ctgrMap.put("user_idx", user_idx);
			ctgrMap.put("category_in_name", income[a]);
			
			userDAO.insertCategory_in(ctgrMap);
		}
		
	}
	
	
	
	

}
