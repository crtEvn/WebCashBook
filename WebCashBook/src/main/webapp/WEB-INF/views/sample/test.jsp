<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="../include/main_head.jsp" %>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	
	<!-- Wrapper -->
	<div class="wrapper">
		<%@include file="../include/main_header.jsp" %>
		<%@include file="../include/main_left_column.jsp" %>
		
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
    		<section class="content-header">
      			<div class="container-fluid">
        			<div class="row mb-2">
          			<div class="col-sm-6">
            			<h1>${servertime }</h1>
          			</div>
          			<div class="col-sm-6">
            			<ol class="breadcrumb float-sm-right">
              			<li class="breadcrumb-item"><a href="#">Home</a></li>
              			<li class="breadcrumb-item"><a href="#">Layout</a></li>
              			<li class="breadcrumb-item active">Fixed Layout</li>
            			</ol>
          			</div>
        			</div>
      			</div><!-- /.container-fluid -->
    		</section>
			<!-- Main content -->
    		<section class="content">
    			<div class="container-fluid">
    			  test.jsp
    			  한글
    			</div>
    		</section>
		</div>
		<!-- /.content wrapper -->
		
		<%@include file="../include/main_footer.jsp" %>
	</div>
	<!-- /.wrapper -->

	<%@include file="../include/main_plugin.jsp" %>
</body>
</html>