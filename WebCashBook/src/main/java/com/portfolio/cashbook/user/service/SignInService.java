package com.portfolio.cashbook.user.service;

import javax.servlet.http.HttpSession;

import com.portfolio.cashbook.user.dto.SignDTO;

public interface SignInService {
	
	public boolean startSignIn(SignDTO signDTO, HttpSession session) throws Exception;
	
}
