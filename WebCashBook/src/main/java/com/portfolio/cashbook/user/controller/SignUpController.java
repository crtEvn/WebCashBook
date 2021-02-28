package com.portfolio.cashbook.user.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.cashbook.user.dto.SignDTO;
import com.portfolio.cashbook.user.service.SignUpService;
import com.portfolio.cashbook.user.vo.UserVO;

@Controller
public class SignUpController {
	
	@Resource(name="signUpService")
	private SignUpService signUpService;
	
	Log log = LogFactory.getLog(this.getClass());
	
	// 회원가입 페이지
	@GetMapping(value="/user/signup.do")
	public String signUp(SignDTO signDTO) throws Exception {
		log.debug("signUp");
		return "user/sign_up";
	}
	
	// 회원가입 기능
	@PostMapping(value="/user/signup.do")
	public String excute_signUp(Model model, @Valid SignDTO signDTO, Errors errors) throws Exception {
		log.debug("excute_signUp");
		
		if(signUpService.startSignUp(model, signDTO, errors)) {
			// 회원가입 성공
			return "redirect:/user/signin.do";
		}
		
		// 회원가입 실패
		return "user/sign_up";
	}

}
