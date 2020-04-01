<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ledger_main</title>
<%@include file="../include/main_head.jsp" %>
</head>
<body class="hold-transition sidebar-mini layout-fixed">

	<!-- Wrapper -->
	<div class="wrapper">
		<%@include file="../include/main_header.jsp" %>
		<%@include file="../include/main_left_column.jsp" %>
		<%@include file="./ledger_day.jsp" %>
		<%@include file="../include/main_footer.jsp" %>
	</div>
	<!-- /.wrapper -->
	<%@include file="../include/main_plugin.jsp" %>
</body>
</html>