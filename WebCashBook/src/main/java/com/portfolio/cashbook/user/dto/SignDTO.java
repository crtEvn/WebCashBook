package com.portfolio.cashbook.user.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class SignDTO {
	
	@NotEmpty(message="ID를 입력해 주세요.")
	@Pattern(regexp="^(?=.*\\d)(?=.*[a-zA-Z]).{5,20}$", message="ID는 영문, 숫자를 조합하여, 5자 이상 20자 이내로 적어주세요.")
	@Length(min=5, max=20, message="ID는 영문, 숫자를 조합하여, 5자 이상 20자 이내로 적어주세요.")
	private String user_id;
	
	@NotEmpty(message="Password를 입력해 주세요.")
	@Pattern(regexp="^(?=.*\\d)(?=.*[a-zA-Z])(?=.*\\W).{5,25}$", message="Password는 영문, 숫자, 특수문자를 조합하여, 5자 이상 25자 이내로 적어주세요.")
	@Length(min=5, max=25, message="Password는 영문, 숫자, 특수문자를 조합하여, 5자 이상 25자 이내로 적어주세요.")
	private String user_pw;
	
	@NotEmpty(message="Password를 다시 입력해 주세요.")
	private String user_pw_check;
	
	@NotEmpty(message="Email을 입력해 주세요.")
	@Email(message="Email형식이 올바르지 않습니다.")
	private String user_email;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_pw_check() {
		return user_pw_check;
	}
	public void setUser_pw_check(String user_pw_check) {
		this.user_pw_check = user_pw_check;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	
}
