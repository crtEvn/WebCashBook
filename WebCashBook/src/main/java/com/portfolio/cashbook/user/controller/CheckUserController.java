package com.portfolio.cashbook.user.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.cashbook.user.service.UserService;

@Controller
public class CheckUserController {

	Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name="userService")
	private UserService userService;
	
	private static Pattern pattern = null;
	private static Matcher matcher = null;
	private String regex = null;
	private int returnData = 0; // 0 : error
	
	// user_id 체크
	@RequestMapping(value="/user/checkID.do", method = RequestMethod.GET)
	@ResponseBody
	public int checkUser_id(@RequestParam String user_id) throws Exception {
		
		// user_id: 영문자&숫자 조합, 5-20자
		regex = "^(?=.*\\d)(?=.*[a-zA-Z]).{5,20}$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(user_id);
		
		if(matcher.find()) { // id 정책에 부합
			
			// user_id 중복 체트
			int queryResult = userService.checkUser_id(user_id);
			if(queryResult != 0) { // user_id 중복
				returnData = 1;
			} else { // user_id 사용 가능
				returnData = 2;
			}
			
		} else { // id 정책 오류
			returnData = 0;
		}
		log.debug(returnData);
		// returnData - 0: 정책 오류, 1: ID중복 오류, 2: 사용가능한 ID
		return returnData;
	}
	
	// user_pw 체크
	@RequestMapping(value="/user/checkPW.do", method = RequestMethod.GET)
	@ResponseBody
	public int checkUser_pw(@RequestParam String user_pw) throws Exception {
		
		// user_pw: // 영문자&숫자&특수문자 조합, 5-25자
		regex = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*\\W).{5,25}$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(user_pw);
		
		if(matcher.find()) { // PW 정책에 부합
			returnData = 1;
		} else { // PW 정책 오류
			returnData = 0;
		}
		
		log.debug("user_pw: "+user_pw+", returnData: "+returnData);
		
		return returnData;
	}
	
	// user_email 체크
	@RequestMapping(value="/user/checkEmail.do", method = RequestMethod.GET)
	@ResponseBody
	public int checkUser_email(@RequestParam String user_email) throws Exception {
		
		regex = "^[a-z0-9_+.-]+@([a-z0-9-]+\\.)+[a-z0-9]{2,4}$";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(user_email);
		
		if(matcher.find()) { // email 형식에 부합
			returnData = 1;
		} else { // email 형식 오류
			returnData = 0;
		}
		
		return returnData;
	}
}
