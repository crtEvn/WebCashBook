<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>index.jsp</h1>
	<div>
		<p><a href="<c:url value='/ledger/main.do'/>">ledger/main.do</p>
		<p><a href="<c:url value='/user/signin.do'/>">user/signin.do</p>
		<p><a href="<c:url value='/user/signup.do'/>">user/signup.do</p>
		<p><a href="<c:url value='/user/signout.do'/>">user/signout.do</p>
		<p><a href="<c:url value='/autoSignIn.do'/>">자동 로그인</p>
	</div>

</body>
</html>