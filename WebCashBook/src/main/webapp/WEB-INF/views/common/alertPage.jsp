<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Alert를 띄운 후 페이지 이동 -->
	
	<script type="text/javascript">
		var message = "${message}";
		var url = "${pageContext.request.contextPath}"+"${url}";
		alert(message);
		document.location.href = url;
	</script>

</body>
</html>