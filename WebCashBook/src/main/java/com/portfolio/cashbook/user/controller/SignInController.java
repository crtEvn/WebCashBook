package com.portfolio.cashbook.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portfolio.cashbook.user.dto.SignDTO;
import com.portfolio.cashbook.user.service.UserService;
import com.portfolio.cashbook.user.vo.UserVO;

@Controller
public class SignInController {
	
	Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name="userService")
	private UserService userService;
	
	// 로그인 페이지
	@GetMapping(value="/user/signin.do")
	public String signIn_view(Model model) throws Exception {
		
		log.debug("로그인 페이지");
		
		return "user/sign_in";
	}
		
	// 로그인 기능
	@PostMapping(value="/user/signin.do")
	public String signIn(Model model, SignDTO signDTO, HttpSession session) throws Exception {
		
		UserVO userVO = userService.selectUser(signDTO);
		
		if(userVO != null) {
			log.debug("user_id 일치 - "+userVO.getUser_id());
			
			if(BCrypt.checkpw(signDTO.getUser_pw(), userVO.getUser_pw())) {
				log.debug("user_pw 일치");
				// session 설정
				session.setAttribute("userSession", userVO);
				log.debug("SignIn 성공");
				return "redirect:/ledger/main.do";
			}else {
				log.debug("user_pw 불일치");
			}
			
		}else {
			log.debug("user_id 불일치");
		}
		
		model.addAttribute("signInError", "ID 또는 Password가 일치하지 않습니다.");
		
		return "user/sign_in";
	}
}
