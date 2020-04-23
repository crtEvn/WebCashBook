<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="../include/main_head.jsp" %>

</head>
<body class="hold-transition register-page">
<div class="register-box">
  <div class="register-logo">
    <a href="#"><b>Web</b>CashBook</a>
  </div>

  <div class="card">
    <div class="card-body register-card-body">
      <p class="login-box-msg">WebCashBook을 바로 시작해 보세요</p>

      <form action="<c:url value='/user/signup.do'/>" name="form" id="signUpForm" method="post">
        
        <!-- user_id -->
        <div class="input-group mb-3">
          <input type="text" name="user_id" id="user_id" class="form-control" placeholder="ID를 입력하세요.(영문,숫자 조합 5-20자)">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-user"></span>
            </div>
          </div>
          <div id="msg_user_id">${valid_user_id }</div>
        </div>
        
        <!-- user_pw -->
        <div class="input-group mb-3">
          <input type="password" name="user_pw" id="user_pw" class="form-control" placeholder="Password를 입력하세요.(영문,숫자,특수문자 조합 5-25자)">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>
          <div id="msg_user_pw">${valid_user_pw }</div>
        </div>
        
        <!-- user_pw_check -->
        <div class="input-group mb-3">
          <input type="password" name="user_pw_check" id="user_pw_check" class="form-control" placeholder="Password를 한번 더 입력하세요.">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-lock"></span>
            </div>
          </div>
          <div id="msg_user_pw_check">${valid_user_pw_check }</div>
        </div>
        
        <!-- user_email -->
        <div class="input-group mb-3">
          <input type="email" name="user_email" id="user_email" class="form-control" placeholder="Email을 입력하세요">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fas fa-envelope"></span>
            </div>
          </div>
          <div id="msg_user_email">${valid_user_email }</div>
        </div>
        
        <div class="row">
          <div class="col-8">
            <div class="icheck-primary">
              <input type="checkbox" id="agreeTerms" name="terms" value="agree">
              <label for="agreeTerms">
               <a href="#">약관</a>에 동의하십니까?
              </label>
            </div>
          </div>
          <!-- /.col -->
          <div class="col-4">
            <button type="submit" id="btn_submit" class="btn btn-primary btn-block">가입하기</button>
          </div>
          <!-- /.col -->
        </div>
      </form>

      <div class="social-auth-links text-center">
        <p>- OR -</p>
        <a href="#" class="btn btn-block btn-primary">
          <i class="fab fa-facebook mr-2"></i>
          Sign up using Facebook
        </a>
        <a href="#" class="btn btn-block btn-danger">
          <i class="fab fa-google-plus mr-2"></i>
          Sign up using Google+
        </a>
      </div>
      
      <a href="login.html" class="text-center">로그인 페이지</a>
      
    </div>
    <!-- /.form-box -->
    
    <button type="button" class="btn btn-danger swalDefaultError">
		Launch Error Toast
	</button>
    
  </div><!-- /.card -->
</div>
<!-- /.register-box -->

<input type="hidden" id="errorID" value="${errorID}">
<div onclick="setErrorMsg()">${errorID}</div>

<%@include file="../include/main_plugin.jsp" %>
<script type="text/javascript">

	var check_id = false;
	var check_pw = false;
	var check_ch_pw = false;
	var check_email = false;
	
	$('#btn_submit').click(function(e){
		//e.preventDefault();
		if(check_id==true && check_pw==true && check_ch_pw==true && check_email==true){
			$('#form').submit();
		}else{
			alert("회원가입 Form을 올바르게 작성해 주세요.");
			return false;
		}
	})
	
	//user_id 중복체크 및 정책 검사
	$('#user_id').blur(function() {
		var user_id = {user_id : $('#user_id').val()};
		$.ajax({
			url: "<c:url value='/user/checkID.do'/>",
			type: 'get',
			data: user_id,
			success: function(data){
				if(data == 0){ // ID 정책 오류
					$('#msg_user_id').text('ID는 영문, 숫자를 조합하여, 5자 이상 20자 이내로 적어주세요.');
					check_id = false;
				} else if(data == 1){ // ID 중복 오류
					$('#msg_user_id').text('중복된 아이디 입니다. 다시 입력해 주세요.');
					check_id = false;
				} else if(data == 2){ // 사용 가능
					$('#msg_user_id').text('');
					check_id = true;
				}
			},
			error: function(){
				alert("ajax_error.user_id");
			}
		})
	})
	
	// user_pw 정책 검사
	$('#user_pw').blur(function() {
		var user_pw = {user_pw : $('#user_pw').val()};
		$.ajax({
			url: "<c:url value='/user/checkPW.do'/>",
			type: 'get',
			data: user_pw,
			success: function(data){
				if(data == 0){ // Password 정책 오류
					$('#msg_user_pw').text('Password는 영문, 숫자, 특수문자를 조합하여, 5자 이상 25자 이내로 적어주세요.');
					check_pw = false;
				} else if(data == 1){ // Password 사용 가능
					$('#msg_user_pw').text('');
					check_pw = true;
				}
			},
			error: function(){
				alert("ajax_error.user_pw");
			}
		})
	})
	
	// user_pw_check 검사
	$('#user_pw_check').blur(function() {
		if($('#user_pw').val() != $('#user_pw_check').val()){
			// user_pw 와 user_pw_check이 다른 경우
			$('#msg_user_pw_check').text('Password가 동일하지 않습니다..');
			check_ch_pw = false;
		}else{
			var user_pw_check = {user_pw : $('#user_pw_check').val()};
			$.ajax({
				url: "<c:url value='/user/checkPW.do'/>",
				type: 'get',
				data: user_pw_check,
				success: function(data){
					if(data == 0){ // Password 정책 오류
						$('#msg_user_pw_check').text('Password를 다시 입력해 주세요.');
						check_ch_pw = false;
					} else if(data == 1){ // Password 사용 가능
						$('#msg_user_pw_check').text('');
						check_ch_pw = true;
					}
				},
				error: function(){
					alert("ajax_error.user_pw_check");
				}
			})
		}
	})
	
	// user_email 정책 검사
	$('#user_email').blur(function() {
		var user_email = {user_email : $('#user_email').val()};
		$.ajax({
			url: "<c:url value='/user/checkEmail.do'/>",
			type: 'get',
			data: user_email,
			success: function(data){
				if(data == 0){ // Email 형식 오류
					$('#msg_user_email').text('Email형식이 올바르지 않습니다.');
					check_email = false;
				} else if(data == 1){ // Email 사용 가능
					$('#msg_user_email').text('');
					check_email = true;
				}
			},
			error: function(){
				alert("ajax_error.email");
			}
		})
	})

</script>


</body>
</html>