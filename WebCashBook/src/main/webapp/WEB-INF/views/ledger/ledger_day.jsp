<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script>
// [Modal]: 가계부 내역 수정 Modal 열기
function fn_updateModal(ledger_idx){
	// Modal 열기
	$("#modal-lg").modal("show");
	
	// ledger_idx에 해당하는 정보 가져오기
	var tuple = $("#ledgerTuple"+ledger_idx);
	var date = tuple.find("#date").text().substring(0,10);
	var account = tuple.find("#account").text();
	var category = tuple.find("#category").text();
	var sub_category = tuple.find("#sub_category").text();
	var description = tuple.find("#description").text();
	var amount = tuple.find("#amount").text();
	var asset = tuple.find("#asset").text();
	
	// Modal의 updateForm에 정보 입력
	var updateForm = $("#ledgerUpdateForm");
	updateForm.find("#ledger_idx").val(ledger_idx);
	updateForm.find("#date").val(date);
	updateForm.find("#account").val(account);
	updateForm.find("#category").val(category);
	updateForm.find("#sub_category").val(sub_category);
	updateForm.find("#description").val(description);
	updateForm.find("#amount").val(amount);
	updateForm.find("#asset").val(asset);
}

// 가계부 내역 수정 기능
function fn_updateLedger(){
	var form = document.getElementById("ledgerUpdateForm");
	form.action="<c:url value='/ledger/updateLedger.do'/>";
	form.submit();
}

// Modal창 닫기
function fn_closeModal(){
	$("#modal-lg").modal("hide");
}

// 가계부 내역삭제 기능
function fn_deleteLedger(ledger_idx){
	
	// 가계부 삭제를 위한 Form 생성
	var crtForm = document.createElement('form');
	crtForm.name='newForm';
	crtForm.method='post';
	crtForm.action='<c:url value="/ledger/deleteLedger.do"/>';
	
	// ledger_idx값 생성
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
							<!-- Insert Form -->
							<%@include file="./ledger_insertForm.jsp" %>
							<!-- /.insert-form -->
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
												<tr id="ledgerTuple${row.LEDGER_IDX}">
													<th id="date">${row.DATE }</th>
													<th id="account">${row.ACCOUNT }</th>
													<th id="category">${row.CATEGORY }</th>
													<th id="sub_category">${row.SUB_CATEGORY }</th>
													<th id="description">${row.DESCRIPTION }</th>
													<th id="amount">${row.AMOUNT }</th>
													<th id="asset">${row.ASSET }</th>
													<th>
														<button class="btn btn-primary btn-sm" title="수정" onclick="fn_updateModal(${row.LEDGER_IDX})">
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
	
<!-- Modal -->
<div class="modal fade" id="modal-lg">
	<!-- Modal-dialog -->
	<div class="modal-dialog modal-lg">
		<!-- Modal-content -->
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">내역 수정</h4>
				<button type="button" class="close">
					<span aria-hidden="true" onclick="fn_closeModal()">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<!-- Update Form -->
				<%@include file="./ledger_updateForm.jsp" %>
				<!-- /.update-form -->
			</div>
			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-default" onclick="fn_closeModal()">닫기</button>
				<button type="button" class="btn btn-primary" onclick="fn_updateLedger()">수정하기</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
