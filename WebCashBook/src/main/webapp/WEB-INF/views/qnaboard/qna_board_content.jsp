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
									${boardContent.USER_ID }, ${boardContent.REG_DATE }
								</span>
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
								<button type="button" class="btn btn-default">
									<i class="fas fa-reply"></i> 이전글
								</button>
								<button type="button" class="btn btn-default">
									<i class="fas fa-bars"></i> 목록으로
								</button>
								<button type="button" class="btn btn-default">
									<i class="fas fa-share"></i> 다음글
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
									<tr>
										<td>183</td>
										<td>Bacon ipsum dolor sit amet salami venison chicken
											flank fatback doner.</td>
										<td>John Doe</td>
										<td>11-7-2014</td>
										<td>13</td>
									</tr>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->

						<!-- Card-footer -->
						<div class="card-footer">
							<ul class="pagination pagination-sm m-0"
								style="justify-content: center;">
								<li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
								<li class="page-item"><a class="page-link" href="#">1</a></li>
								<li class="page-item"><a class="page-link" href="#">2</a></li>
								<li class="page-item"><a class="page-link" href="#">3</a></li>
								<li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
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
<!-- /.content Wrapper -->