package com.portfolio.cashbook.user.service;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import com.portfolio.cashbook.user.dto.SignDTO;

public interface SignUpService {
	
	public boolean startSignUp(Model model, @Valid SignDTO signDTO, Errors errors) throws Exception;
	public Map<String, String> validateHandling(Errors errors) throws Exception;
	public String hashPassword(String user_pw) throws Exception;
	public void insertFirstCategory(String user_idx) throws Exception;
	
	
}
