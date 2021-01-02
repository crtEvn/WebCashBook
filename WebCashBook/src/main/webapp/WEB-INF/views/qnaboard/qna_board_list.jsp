<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- Content Wrapper -->
<div class="content-wrapper">

	<!-- Content Header -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1>QnA 게시판</h1>
				</div>
			</div>
		</div>
	</section>
	<!-- /.content header -->

	<!-- Main content -->
	<section class="content">
		<div class="container-fluid">
			<div class="row">
				<div class="col-12">

					<!-- Card -->
					<div class="card">

						<!-- Card-header -->
						<div class="card-header">
							<!-- My contents -->
							<div class="input-group-append float-left">
								<button type="submit" class="btn btn-block btn-default btn-sm">
									<b>내가 쓴 글</b>
								</button>
							</div>
							<!-- /.my contents -->
							<!-- Search bar -->
							<div class="input-group input-group-sm float-right"
								style="width: 300px;">
								<input type="text" name="table_search" class="form-control"
									placeholder="Search">
								<div class="input-group-append">
									<button type="submit" class="btn btn-default">
										<i class="fas fa-search"></i><b> 검색</b>
									</button>
								</div>
							</div>
							<!-- /.search bar -->
						</div>
						<!-- /.card-header -->

						<!-- Card-body -->
						<div class="card-body table-responsive p-0">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>번호</th>
										<th>제목</th>
										<th>글쓴이</th>
										<th>날짜</th>
										<th>조회수</th>
									</tr>
								</thead>
								<tbody>
								
									<c:choose>
										<c:when test="${fn:length(boardList) > 0}">
											<c:forEach var="qna_list" items="${boardList }">
												<tr onClick="location.href='${pageContext.request.contextPath}/qna/content.do?board_idx=${qna_list.BOARD_IDX }'" style="cursor:pointer">
													<td>${qna_list.BOARD_IDX }</td>
													<td>${qna_list.SUBJECT }</td>
													<td>${qna_list.USER_IDX }</td>
													<td>${qna_list.REG_DATE }</td>
													<td>${qna_list.HIT_CNT }</td>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr>
												<td colspan="5">내역이 없습니다.</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->

						<!-- Card-footer -->
						<div class="card-footer">
							<ul class="pagination pagination-sm m-0"
								style="justify-content: center;">
								<li class="page-item">
									<a class="page-link" href="#">&laquo;</a>
								</li>
								<li class="page-item">
									<a class="page-link" href="#">1</a>
								</li>
								<li class="page-item">
									<a class="page-link" href="#">2</a>
								</li>
								<li class="page-item">
									<a class="page-link" href="#">3</a>
								</li>
								<li class="page-item">
									<a class="page-link" href="#">&raquo;</a>
								</li>
							</ul>
						</div>
						<!-- /.card-footer -->

					</div>
					<!-- /.card -->
				</div>
			</div>
		</div>
	</section>
	<!-- /.main content -->

</div>
<!-- ./content wrapper -->