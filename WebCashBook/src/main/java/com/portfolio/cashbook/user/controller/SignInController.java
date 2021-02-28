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
import com.portfolio.cashbook.user.service.SignInService;
import com.portfolio.cashbook.user.vo.UserVO;

@Controller
public class SignInController {
	
	Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name="signInService")
	private SignInService signInService;
	
	// Sign In 페이지
	@GetMapping(value="/user/signin.do")
	public String signIn_view(Model model) throws Exception {
		
		log.debug("로그인 페이지");
		
		return "user/sign_in";
	}
		
	// Sign In 기능
	@PostMapping(value="/user/signin.do")
	public String signIn(Model model, SignDTO signDTO, HttpSession session) throws Exception {
		log.debug("????-1111");
		
		log.debug("????-2222");
		
		if(signInService.startSignIn(signDTO, session)) {
			return "redirect:/ledger/main.do";
		}
		
		model.addAttribute("signInError", "ID 또는 Password가 일치하지 않습니다.");
		
		return "user/sign_in";
	}
	
	// Sign Out
	@RequestMapping(value="/user/signout.do")
	public String signOut(Model model, HttpSession session) throws Exception{
		
		session.removeAttribute("userSession");
		
		return "user/sign_in";
	}
	
}
