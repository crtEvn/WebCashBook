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
						<c:when test="${page eq 'list' || page eq 'content'}">
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
		
			<!-- 게시글 Content -->
			<c:if test="${not empty boardContent.BOARD_IDX }">
			
				<!-- Row -->
				<div class="row">
					<!-- Col -->
					<div class="col-12">

						<!-- Card -->
						<div class="card card-primary card-outline">

							<!-- Card-header -->
							<div class="card-header">
								<h5>${boardContent.SUBJECT }</h5>
								<h6>
									<span class="mailbox-read-time float-right">
										${boardContent.USER_ID }, ${boardContent.REG_DATE } </span>
								</h6>
							</div>
							<!-- /.card-header -->

							<!-- Card-body -->
							<div class="card-body p-0">
								<div class="mailbox-read-message">
									<p>${boardContent.CONTENT }</p>
								</div>
							</div>
							<!-- /.card-body -->

							<!-- Card-footer -->
							<div class="card-footer">
								<div class="float-right">
									<button type="button" class="btn btn-default" onclick="fn_linkToNextContent('next')">
										<i class="fas fa-reply"></i> 다음글
									</button>
									<button type="button" class="btn btn-default" onclick="fn_linkToListPage()">
										<i class="fas fa-bars"></i> 목록으로
									</button>
									<button type="button" class="btn btn-default" onclick="fn_linkToNextContent('prev')">
										<i class="fas fa-share"></i> 이전글
									</button>
								</div>
								<button type="button" class="btn btn-default">
									<i class="fas fa-pencil-alt"></i> 수정하기
								</button>
								<button type="button" class="btn btn-default">
									<i class="far fa-trash-alt"></i> 삭제하기
								</button>
							</div>
							<!-- /.card-footer -->

						</div>
						<!-- /.card -->

					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
				
			</c:if>
			<!-- /.게시글 Content -->

			<!-- 게시글 리스트 -->
			<div class="row">
				<div class="col-12">

					<!-- Card -->
					<div class="card">

						<!-- Card-header -->
						<div class="card-header">
							<!-- My contents -->
							<div class="input-group-sm float-left">
								<button type="button" class="btn btn-primary"
									onclick="fn_linkToWrite()">
									<b><i class="fas fa-pencil-alt"></i> 글쓰기</b>
								</button>
								<button type="button" class="btn btn-default"
									onclick="fn_myPost()">
									<b>내가 쓴 글</b>
								</button>
							</div>
							<!-- /.my contents -->
							<!-- Search bar -->
							<div class="input-group input-group-sm float-right"
								style="width: 300px;">
								<form id="searchQnaBoardForm">
									<select name="select_type">
										<option value="sub_cont">제목+내용</option>
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
												<tr onclick="fn_linkToContentPage(${qna_list.BOARD_IDX })" style="cursor: pointer">
													<td>${qna_list.BOARD_IDX }</td>
													<td>${qna_list.SUBJECT }</td>
													<td>${qna_list.USER_ID }</td>
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
									<a class="page-link" onclick="fn_linkForPagingBlock(1)" href="#">처음 </a>
								</li>

								
								<c:if test="${pagingData.hasPreviousBlock == true }">
									<li class="page-item">
										<a class="page-link" onclick="fn_linkForPagingBlock(${param.currentPageNo-10})" href="#">이전 </a>
									</li>
								</c:if>

								<c:forEach var="paging" begin="${pagingData.firstPageNo}" end="${pagingData.lastPageNo}" step="1" varStatus="status">
									<li class="page-item">
										<a class="page-link" onclick="fn_linkForPagingBlock(${status.current})" href="#">
											${status.current}
										</a>
									</li>
								</c:forEach>

								<c:if test="${pagingData.hasNextBlock == true }">
									<li class="page-item">
										<a class="page-link" onclick="fn_linkForPagingBlock(${param.currentPageNo+10})" href="#">다음 </a>
									</li>
								</c:if>

								<li class="page-item">
									<a class="page-link" onclick="fn_linkForPagingBlock(${pagingData.totalPageCount})" href="#">
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
			<!-- /.게시글 리스트 -->

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
		location.href='<c:url value="/qna/myPost.do"/>'
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
	
	function fn_makeURL(url, keyword, select_type, board_idx, currentPageNo){
		
		if(keyword != ''){
			url = url + 'keyword=' + keyword + '&';
		}
		
		if(select_type != ''){
			url = url + 'select_type=' + select_type + '&';
		}
		
		if(board_idx != ''){
			url = url + 'board_idx=' + board_idx + '&';
		}
		
		if(currentPageNo != ''){
			url = url + 'currentPageNo=' + currentPageNo;
		}
		
		return url;
	}
	
	// 게시글 페이지로 이동(/qna/content.do)
	function fn_linkToContentPage(board_idx){
		
		var url = '<c:out value="${pageContext.request.contextPath}"/>' + '/qna/content.do?';
		var keyword = '<c:out value="${param.keyword}"/>';
		var select_type = '<c:out value="${param.select_type}"/>';
		var currentPageNo = '<c:out value="${param.currentPageNo}"/>';
		
		url = fn_makeURL(url, keyword, select_type, board_idx, currentPageNo);
		$(location).attr('href',url);
	}
	
	// 다음 페이징 페이지로 이동
	function fn_linkForPagingBlock(currentPageNo){
		
		var url = $(location).attr('protocol') + '//' + $(location).attr('host') + $(location).attr('pathname')+'?';
		var keyword = '<c:out value="${param.keyword}"/>';
		var select_type = '<c:out value="${param.select_type}"/>';
		var board_idx = '<c:out value="${param.board_idx}"/>';
		
		// 이동하려는 페이지가 totalPageCount보다 클 경우
		if(currentPageNo > '<c:out value="${pagingData.totalPageCount}"/>'){
			currentPageNo = '<c:out value="${pagingData.totalPageCount}"/>';
		}
		
		url = fn_makeURL(url, keyword, select_type, board_idx, currentPageNo);
		
		$(location).attr('href',url);
	}
	
	// 다른 게시글(Content 로 이동)
	function fn_linkToNextContent(nextOrPrev) {
		
		var url = '<c:out value="${pageContext.request.contextPath}"/>' + '/qna/content.do?';;
		var keyword = '<c:out value="${param.keyword}"/>';
		var select_type = '<c:out value="${param.select_type}"/>';
		var board_idx;
		var currentPageNo = '<c:out value="${param.currentPageNo}"/>';
		
		switch(nextOrPrev) {
			case 'next':
				board_idx = '<c:out value="${pagingData.nextBoard_idx}"/>';
				break;
			case 'prev':
				board_idx = '<c:out value="${pagingData.prevBoard_idx}"/>';
				break;
			default:
				alert('해당 함수에는 next 또는 prev 값만 넣어 주세요');
				return;
		}
		
		url = fn_makeURL(url, keyword, select_type, board_idx, currentPageNo);
		$(location).attr('href',url);
	}
	
	// 목록으로 이동(/qna/list.do)
	function fn_linkToListPage() {
		var url = '<c:out value="${pageContext.request.contextPath}"/>' + '/qna/list.do?';
		var keyword = '<c:out value="${param.keyword}"/>';
		var select_type = '<c:out value="${param.select_type}"/>';
		var currentPageNo = '<c:out value="${param.currentPageNo}"/>';
		
		url = fn_makeURL(url, keyword, select_type, '', currentPageNo);
		$(location).attr('href',url);
	}
	
	// 게시글 수정 페이지로 이동
	function fn_linkToUpdatePage() {
		
	}
	
	// 게시글 삭제
	function fn_deleteContent() {
		
	}

</script>