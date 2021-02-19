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
					<c:choose>
						<c:when test="${page eq 'list'}">
							<h1>QnA 게시판</h1>
						</c:when>
						<c:when test="${page eq 'my_post'}">
							<h1>QnA 게시판: 내가 쓴글</h1>
						</c:when>
					</c:choose>
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
							<div class="input-group-sm float-left">
								<button type="button" class="btn btn-primary" onclick="fn_linkToWrite()">
										<b><i class="fas fa-pencil-alt"></i> 글쓰기</b>
								</button>
								<button type="button" class="btn btn-default" onclick="fn_myPost()">
									<b>내가 쓴 글</b>
								</button>
							</div>
							<!-- /.my contents -->
							<!-- Search bar -->
							<div class="input-group input-group-sm float-right" style="width: 300px;">
								<form id="searchQnaBoardForm">
									<select name="select_type">
										<option value="sub+cont">제목+내용</option>
										<option value="sub">제목</option>
										<option value="cont">내용</option>
										<option value="user">글쓴이</option>
									</select>
									<input type="text" name="keyword" class="form-control" placeholder="Search" required>
									<input type="hidden" name="currentPageNo" value="1">
									<div class="input-group-append">
										<button type="button" class="btn btn-default" onclick="fn_searchQnaBoard()">
											<i class="fas fa-search"></i><b> 검색</b>
										</button>
									</div>
								</form>
							</div>
							<!-- /.search bar -->
						</div>
						<!-- /.card-header -->

						<!-- Card-body -->
						<div class="card-body table-responsive p-0">
							<table class="table table-hover">
								<thead>
									<tr>
										<th style="width: 10%;">번호</th>
										<th style="width: 44%;">제목</th>
										<th style="width: 13%;">글쓴이</th>
										<th style="width: 23%;">날짜</th>
										<th style="width: 10%;">조회수</th>
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
									<a class="page-link" href="${pageContext.request.contextPath}/qna/list.do?keyword=${param.keyword}&currentPageNo=1">
										처음
									</a>
								</li>
								
								<c:choose>
									<c:when test="${pagingData.hasPreviousBlock == true }">
										<li class="page-item">
											<a class="page-link" href="${pageContext.request.contextPath}/qna/list.do?keyword=${param.keyword}&currentPageNo=${param.currentPageNo-pagingData.pageSize}">
												이전
											</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item">
											<a class="page-link" href="#">이전 없음</a>
										</li>
									</c:otherwise>
								</c:choose>
								
								<c:forEach var="paging" begin="${pagingData.firstPageNo}" end="${pagingData.lastPageNo}" step="1" varStatus="status">
									<li class="page-item">
										<a class="page-link" href="${pageContext.request.contextPath}/qna/list.do?keyword=${param.keyword}&currentPageNo=${status.current}">
											${status.current}
										</a>
									</li>
								</c:forEach>
								 
								<c:choose>
									<c:when test="${pagingData.hasNextBlock == true }">
										<li class="page-item">
											<a class="page-link" href="${pageContext.request.contextPath}/qna/list.do?keyword=${param.keyword}&currentPageNo=${param.currentPageNo+pagingData.pageSize}">
												다음
											</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item">
											<a class="page-link" href="#">다음 없음</a>
										</li>
									</c:otherwise>
								</c:choose>
								
								<li class="page-item">
									<a class="page-link" href="${pageContext.request.contextPath}/qna/list.do?keyword=${param.keyword}&currentPageNo=${pagingData.totalPageCount}">
											끝
									</a>
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

<!-- Page Script -->
<script type="text/javascript">
	
	function fn_linkToWrite(){
		location.href="<c:url value='/qna/write.do'/>"
	}
	
	function fn_myPost(){
		
		$('select[name=select_type] option:selected').val('my_post');
		
		var form = document.getElementById("searchQnaBoardForm");
		form.action = "<c:url value='/qna/myPost.do'/>";
		form.method = "get"
		form.submit();
	}
	
	function fn_searchQnaBoard(){
		
		if($('input[name=keyword]').val() == ''){
			alert('검색어를 입력해 주세요');
		}else {
			var form = document.getElementById("searchQnaBoardForm");
			
			form.action = "<c:url value='/qna/list.do'/>";
			form.method = "get"
			form.submit();
		}
	}

</script>