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
		<c:choose>
			<c:when test="${page eq 'day'}">
				<%@include file="./ledger_day.jsp" %>
			</c:when>
			<c:when test="${page eq 'calendar'}">
				<%@include file="./ledger_calendar.jsp" %>
			</c:when>
		</c:choose>
		<%@include file="../include/main_footer.jsp" %>
	</div>
	<!-- /.wrapper -->
	<%@include file="../include/main_plugin.jsp" %>
</body>
</html>