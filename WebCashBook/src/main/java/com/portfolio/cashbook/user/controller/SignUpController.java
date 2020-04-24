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
import com.portfolio.cashbook.user.service.UserService;

@Controller
public class SignUpController {
	
	Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name="userService")
	private UserService userService;
	
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
		
		// 회원가입 Errors 처리
		if(errors.hasErrors()) {
			// 회원가입 실패 시
			log.debug("validator 오류");
			
			// 유효성 통과 못한 필드와 메시지를 핸들링
			Map<String, String> validatorResult = userService.validateHandling(errors);
			for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
                log.debug("key: "+key+", result: "+validatorResult.get(key));
    		}
			return "user/sign_up";
		}
		
		// user_id 중복 체크
		int count = userService.checkUser_id(signDTO.getUser_id());
		if(0 < count) { // user_id 중복인 경우 
			log.debug("ID 중복 오류 user_id: "+signDTO.getUser_id());
			model.addAttribute("valid_user_id", "중복된 아이디 입니다. 다시 입력해 주세요.");
			return "user/sign_up";
		}
		
		// user_pw 암호화 
		String hashedPW = BCrypt.hashpw(signDTO.getUser_pw(),BCrypt.gensalt());
		signDTO.setUser_pw(hashedPW);
		
		// [INSERT] Sign Up
		userService.insertUser(signDTO);
		log.debug("SignUp 성공");
		
		return "redirect:/user/signin.do";
	}

}
