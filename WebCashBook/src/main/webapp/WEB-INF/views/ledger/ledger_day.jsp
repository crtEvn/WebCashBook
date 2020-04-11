<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script>
//가계부 내역 수정
function fn_updateLedger(ledger_idx){
	alert("updateLedger("+ledger_idx+")");
}

// 가계부 내역삭제
function fn_deleteLedger(ledger_idx){

	var crtForm = document.createElement('form');
	crtForm.name='newForm';
	crtForm.method='post';
	crtForm.action='<c:url value="/ledger/deleteLedger.do"/>';

	var input = document.createElement('input');
	input.setAttribute("type","hidden");
	input.setAttribute("name","ledger_idx");
	input.setAttribute("value",ledger_idx);
	
	crtForm.appendChild(input);
	
	document.body.appendChild(crtForm);
	
	crtForm.submit();
}
</script>


	<!-- Content Wrapper -->
	<div class="content-wrapper">
	
		<!-- Content Header -->
    	<section class="content-header">
      		<div class="container-fluid">
        		<div class="row mb-2">
         			<div class="col-sm-6">
            			<h1>일일</h1>
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
							<%@include file="./ledger_insertForm.jsp" %>
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
													<th>
														<button class="btn btn-primary btn-sm" title="수정" onclick="fn_updateLedger(${row.LEDGER_IDX})">
															<i class="fas fa-pencil-alt"></i>
														</button>
														<button class="btn btn-danger btn-sm" title="삭제" onclick="fn_deleteLedger(${row.LEDGER_IDX})">
															<i class="fas fa-trash-alt"></i>
														</button>
													</th>
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
