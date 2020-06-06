package com.portfolio.cashbook.user.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.portfolio.cashbook.common.dao.AbstractDAO;
import com.portfolio.cashbook.user.dto.SignDTO;
import com.portfolio.cashbook.user.vo.UserVO;

@Repository("userDAO")
public class UserDAO extends AbstractDAO{
	
	// SignIn - ID/PW 체크하여 User 정보 불러오기
	public UserVO selectUser(SignDTO signDTO) {
		return (UserVO) selectOne("user.selectUser", signDTO);
	}
	
	// user_id 중복 확인
	public int dupCheckUser_id(String user_id) {
		return (Integer) selectOne("user.checkUser_id",user_id);
	}
	
	// SignUp - 회원가입
	public void insertUser(SignDTO signDTO) {
		insert("user.insertUser", signDTO);
	}
	
	// [INSERT] Income Category
	public void insertCategory_in(Map<String, String> ctgrMap) {
		insert("user.insertCategory_in", ctgrMap);
	}
	
	// [INSERT] Expenditure Category
	public void insertCategory_ex(Map<String, String> ctgrMap) {
		insert("user.insertCategory_ex", ctgrMap);
	}
}
