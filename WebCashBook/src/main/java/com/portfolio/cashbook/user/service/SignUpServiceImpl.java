package com.portfolio.cashbook.user.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.portfolio.cashbook.user.dao.UserDAO;
import com.portfolio.cashbook.user.dto.SignDTO;
import com.portfolio.cashbook.user.vo.UserVO;

@Service("signUpService")
public class SignUpServiceImpl implements SignUpService{

	@Resource(name="userDAO")
	private UserDAO userDAO;
	
	@Resource(name="checkUserService")
	private CheckUserService checkUserService;
	
	Log log = LogFactory.getLog(this.getClass());
	
	// 회원가입 진행
	@Override
	public boolean startSignUp(Model model, @Valid SignDTO signDTO, Errors errors) throws Exception {
		
		// 회원가입 Errors 처리
		if(errors.hasErrors()) {
			// 회원가입 실패 시
			log.debug("validator 오류");
			
			// 유효성 통과 못한 필드와 메시지를 핸들링
			Map<String, String> validatorResult = validateHandling(errors);
			for (String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
				log.debug("key: "+key+", result: "+validatorResult.get(key));
		  	}
			
			return false;
		}
		
		// 아이디 중복 체크
		if(checkUserService.checkUser_ID(signDTO.getUser_id())) {
			log.debug("ID 중복 오류 user_id: "+signDTO.getUser_id());
			model.addAttribute("valid_user_id", "중복된 아이디 입니다. 다시 입력해 주세요.");
			return false;
		}
		
		// PW 암호화
		signDTO.setUser_pw(hashPassword(signDTO.getUser_pw()));
		
		// [INSERT] Sign Up
		userDAO.insertUser(signDTO);
		
		// Category 입력
		UserVO userVO = checkUserService.selectUser(signDTO);
		insertFirstCategory(userVO.getUser_idx());
		
		return true;
	}
	
	// 에러 체크
	@Override
	public Map<String, String> validateHandling(Errors errors) throws Exception {

		Map<String, String> validatorResult = new HashMap<String, String>();
				
		for(FieldError error : errors.getFieldErrors()) {
			String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
		}
		
		return validatorResult;
	}
	
	// password 암호화
	@Override
	public String hashPassword(String user_pw) throws Exception {
		// 암호화 된 pw 리턴
		return BCrypt.hashpw(user_pw,BCrypt.gensalt());
	}
	
	// Category 입력
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
