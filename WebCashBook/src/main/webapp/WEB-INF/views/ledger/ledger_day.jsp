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
					<h1>일일</h1>
				</div>
			</div>
		</div>
	</section>
	<!-- /.content header -->

	<!-- Main content -->
	<section class="content">

		<!-- Row -->
		<div class="row">
			<div class="col-12">
				<!-- Card -->
				<div class="card">
					<!-- Card-body -->
					<div class="card-body">
					
						<form name="selectPeriodForm" id="selectPeriodForm">
							<input type="hidden" name="start_date" id="start_date">
							<input type="hidden" name="end_date" id="end_date">
						</form>
						
						<div class="row">
							<div class="btn-group col-md-5" style="margin: 0 auto">
								<button type="button" id="btnOneMnth" class="btn btn-default">1개월</button>
								<button type="button" id="btnThreeMnth" class="btn btn-default">3개월</button>
								<button type="button" id="btnSixMnth" class="btn btn-default">6개월</button>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-5" style="margin: 0 auto">
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text"> <i
											class="far fa-calendar-alt"></i>
										</span>
									</div>
									<input type="text" class="form-control float-right text-center"
										id="reservation">
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		<!-- /.row -->

		<div class="row">
		
			<div class="col-lg-4">
				<div class="input-group form-group-lg">
					<div class="input-group-prepend">
						<button type="button" class="btn btn-success">수입</button>
					</div>
					<input type="text" class="form-control" value="${ledgerTotal.INCOME }">
				</div>
			</div>

			<div class="col-lg-4">
				<div class="input-group form-group-lg">
					<div class="input-group-prepend">
						<button type="button" class="btn btn-danger">지출</button>
					</div>
					<input type="text" class="form-control" value="${ledgerTotal.EXPENDITURE }">
				</div>
			</div>

			<div class="col-lg-4">
				<div class="input-group form-group-lg">
					<div class="input-group-prepend">
						<button type="button" class="btn btn-primary">합계</button>
					</div>
					<input type="text" class="form-control" value="${ledgerTotal.TOTAL }">
				</div>
			</div>

		</div>


		<!-- Row -->
		<div class="row">
			<div class="col-12">

				<!-- Card -->
				<div class="card">
					<!-- Card-body -->
					<div class="card-body p-0">
						<c:choose>
							<c:when test="${fn:length(dateGroup) > 0}">
								<c:forEach var="row_dg" items="${dateGroup }" varStatus="status">
									<table class="table">
										<thead>
											<tr class="border-top text-bold text-lg">
												<th style="width: 50%">${row_dg.DATE}</th>
												<th style="width: 25%; text-align: right;">수입:
													${row_dg.INCOME}</th>
												<th style="width: 25%; text-align: right;">지출:
													${row_dg.EXPENDITURE}</th>
											</tr>
										</thead>
									</table>
									<table class="table table-valign-middle">
										<tbody>
											<c:forEach var="row_ll" items="${ledgerList[status.index] }">
												<tr id="ledgerTuple${row_ll.LEDGER_IDX}"
													class="border-bottom">
													<input type="hidden" id="date" value="${row_ll.DATE }">
													<%-- <input type="hidden" id="ledger_idx" value="${row_ll.LEDGER_IDX }"> --%>
													<td style="width: 10%;">
														<div id="account">${row_ll.ACCOUNT }</div>
													</td>
													<td style="width: 20%;">
														<div id="category">${row_ll.CATEGORY}</div>
														<div id="sub_category">${row_ll.SUB_CATEGORY}</div>
													</td>
													<td style="width: 35%;">
														<div id="description">${row_ll.DESCRIPTION}</div>
													</td>
													<td style="width: 20%;">
														<div id="asset">${row_ll.ASSET}</div>
														<div id="amount">${row_ll.AMOUNT}</div>
													</td>
													<td style="width: 10%;">
														<button class="btn btn-primary btn-sm" title="수정"
															onclick="fn_updateModal(${row_ll.LEDGER_IDX})">
															<i class="fas fa-pencil-alt"></i>
														</button>
														<button class="btn btn-danger btn-sm" title="삭제"
															onclick="fn_deleteLedger(${row_ll.LEDGER_IDX})">
															<i class="fas fa-trash-alt"></i>
														</button>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<table class="table">
									<thead>
										<tr>
											<th>데이터가 없습니다.</th>
										</tr>
									</thead>
								</table>
							</c:otherwise>
						</c:choose>
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
				<%@include file="./ledger_updateForm.jsp"%>
				<!-- /.update-form -->
			</div>
			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-default"
					onclick="fn_closeModal()">닫기</button>
				<button type="button" class="btn btn-primary"
					onclick="fn_updateLedger()">수정하기</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- Script -->
<script type="text/javascript">

window.onload = function() {
	//Date range picker
	$('#reservation').daterangepicker({
		locale:{
			format: 'YYYY-MM-DD'
		},
		startDate: '${LedgerDTO.start_date}',
		endDate: '${LedgerDTO.end_date}'
	}, function(start,end,label){
		fn_selectLedgerPeriod(start.format('YYYY-MM-DD'),end.format('YYYY-MM-DD'));
	});
	
	// Month Button
	$('#btnOneMnth').click(function(){
		fn_selectLedgerPeriod(moment().subtract(30, 'days').format('YYYY-MM-DD'),moment().format('YYYY-MM-DD'));
	});
	
	$('#btnThreeMnth').click(function(){
		fn_selectLedgerPeriod(moment().subtract(60, 'days').format('YYYY-MM-DD'),moment().format('YYYY-MM-DD'));
	});
	
	$('#btnSixMnth').click(function(){
		fn_selectLedgerPeriod(moment().subtract(180, 'days').format('YYYY-MM-DD'),moment().format('YYYY-MM-DD'));
	});
};

// 가계부 기간 검색
function fn_selectLedgerPeriod(start_date,end_date){
	var selectForm = $('#selectPeriodForm');
	selectForm.find('#start_date').val(start_date);
	selectForm.find('#end_date').val(end_date);
	selectForm.action = '<c:url value="/ledger/main.do"/>';
	selectForm.method = 'get';
	selectForm.submit();
}

// [Modal]: 가계부 내역 수정 Modal 열기
function fn_updateModal(ledger_idx){
	// Modal 열기
	$("#modal-lg").modal("show");
	
	// ledger_idx에 해당하는 정보 가져오기
	var tuple = $("#ledgerTuple"+ledger_idx);
	var date = tuple.find("#date").val();
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