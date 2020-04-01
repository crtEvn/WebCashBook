<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- Content Wrapper -->
	<div class="content-wrapper">
	
		<!-- Content Header -->
    	<section class="content-header">
      		<div class="container-fluid">
        		<div class="row mb-2">
         			<div class="col-sm-6">
            			<h1>일일</h1>
          			</div>
          			<div class="col-sm-6">
            			<!-- <ol class="breadcrumb float-sm-right">
              			  <li class="breadcrumb-item"><a href="#">Home</a></li>
              			  <li class="breadcrumb-item"><a href="#">Layout</a></li>
              			  <li class="breadcrumb-item active">Fixed Layout</li>
            			</ol> -->
          			</div>
        		</div>
      		</div>
    	</section>
    	<!-- /.content header -->

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-12">
					<!-- Card -->
					<div class="card">
						<!-- Card Header -->
						<div class="card-header">
							<h3 class="card-title">가계부</h3>
							<div class="card-tools">
								<!-- <div class="input-group input-group-sm" style="width: 150px;">
									<input type="text" name="table_search"
										class="form-control float-right" placeholder="Search">

									<div class="input-group-append">
										<button type="submit" class="btn btn-default">
											<i class="fas fa-search"></i>
										</button>
									</div>
								</div> -->
							</div>
						</div>
						<!-- /.card-header -->
						<!-- Card Body -->
						<div class="card-body table-responsive p-0">
							<table class="table table-hover text-nowrap">
								<thead>
									<tr>
										<th>날짜</th>
										<th>입출</th>
										<th>분류</th>
										<th>분류</th>
										<th>내용</th>
										<th>금액</th>
										<th>자산</th>
										<th>수정/삭제</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${fn:length(ledgerList) > 0}">
											<c:forEach var="row" items="${ledgerList }">
												<tr>
													<th>${row.DATE }</th>
													<th>${row.ACCOUNT }</th>
													<th>${row.CATEGORY }</th>
													<th>${row.SUB_CATEGORY }</th>
													<th>${row.DESCRIPTION }</th>
													<th>${row.AMOUNT }</th>
													<th>${row.ASSET }</th>
													<th>수정/삭제</th>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr>
												<td colspan="8">가계부 내역이 없습니다.</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->
				</div>
			</div>
			<!-- /.row -->

		</section>
		<!-- /.main content -->

	</div>
	<!-- /.content wrapper -->

</body>
</html>