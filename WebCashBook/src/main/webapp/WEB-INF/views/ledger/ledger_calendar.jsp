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
					<h1>달력</h1>
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
						<div class="row">
							<div class="btn-group col-md-5" style="margin: 0 auto">
								<button type="button" class="btn btn-default" onclick="fn_openInsertModal()">가계부 입력</button>
							</div>
						</div>
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->
			</div>
		</div>
		<!-- /.row -->
	
		<!-- Row -->
		<div class="row">
			<div class="col-12">
				<!-- Card -->
				<div class="card">
					<!-- Card-body -->
					<div class="card-body p-0">
						<!-- CALENDAR TABLE-->
						<div id="calendar" class="fc fc-ltr fc-unthemed">
							<div class="fc-toolbar fc-header-toolbar">
								<div class="fc-left">
									<div class="fc-button-group">
										<button type="button"
												class="fc-prev-button fc-button fc-button-primary"
												id="btnPrevMonth">
											<span class="fc-icon fc-icon-chevron-left"></span>
										</button>
										<button type="button"
												class="fc-next-button fc-button fc-button-primary"
												id="btnNextMonth">
											<span class="fc-icon fc-icon-chevron-right"></span>
										</button>
									</div>
								</div>
								<div class="fc-center">
									<h2 id="todayTitle">${fn:substring(LedgerDTO.date,0,7) }</h2>
								</div>
								<div class="fc-right">
									<div class="input-group">
										<form name="selectMonth" id="selectMonth">								
											<input type="month" name="date" class="form-control" value="${fn:substring(LedgerDTO.date,0,7) }">
										</form>	
										<div class="input-group-append" id="submitSelectMonth">
											<span class="input-group-text">이동</span>
										</div>
									</div>
								</div>	
							</div>
							<table>
								<thead class="fc-head">
									<tr>
										<th class="fc-day-header fc-widget-header fc-sun"><span>일</span></th>
										<th class="fc-day-header fc-widget-header fc-mon"><span>월</span></th>
										<th class="fc-day-header fc-widget-header fc-tue"><span>화</span></th>
										<th class="fc-day-header fc-widget-header fc-wed"><span>수</span></th>
										<th class="fc-day-header fc-widget-header fc-thu"><span>목</span></th>
										<th class="fc-day-header fc-widget-header fc-fri"><span>금</span></th>
										<th class="fc-day-header fc-widget-header fc-sat"><span>토</span></th>
									</tr>
								</thead>
								<tbody class="fc-body">
									<c:set var="i" value="0" />
									<c:set var="j" value="7" />
									<c:forEach var="row" items="${calDateGroup }">

										<c:if test="${i%j == 0 }">
											<tr style="height: 86px;">
										</c:if>
										
										<c:choose>
											<c:when test="${empty row.COLOR }">
												<td>
											</c:when>
											<c:when test="${row.COLOR eq 'GRAY'}">
												<td style="background: #EDEDED;">
											</c:when>
										</c:choose>

											<div class="fc-day-number">${fn:substring(row.DATE,8,10) }</div>
											
											<c:if test="${not empty row.CNT }">
												<a class="fc-day-grid-event fc-h-event fc-event fc-start fc-end fc-draggable" 
													style="background-color: rgba(0,0,0,0.0); border-color: rgba(0,0,0,0.0); color: green; text-align:right">
													<span class="fc-time">+</span>
													<span class="fc-title">${row.INCOME }</span>
												</a>
												<a class="fc-day-grid-event fc-h-event fc-event fc-start fc-end fc-draggable" 
													style="background-color: rgba(0,0,0,0.0); border-color: rgba(0,0,0,0.0); color: red; text-align:right">
													<span class="fc-time">-</span>
													<span class="fc-title">${row.EXPENDITURE }</span>
												</a>
											</c:if>
											
										</td>
										
										<c:if test="${i%j == j-1 }">
											</tr>
										</c:if>

										<c:set var="i" value="${i+1 }" />

									</c:forEach>

								</tbody>
							</table>
						</div>
						<!-- /.calendar Table -->
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
<div class="modal fade" id="modal-lg-insert">
	<!-- Modal-dialog -->
	<div class="modal-dialog modal-default">
		<!-- Modal-content -->
		<div class="modal-content card-info">
			<div class="modal-header">
				<h4 class="modal-title">가계부 입력</h4>
				<button type="button" class="close" onclick="fn_closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<!-- Insert Form -->
				<%@include file="./ledger_insertForm.jsp"%>
				<!-- /.insert-form -->
			</div>
			<div class="modal-footer justify-content-between">
				<button type="button" class="btn btn-default"
					onclick="fn_closeModal()">닫기</button>
				<button type="button" class="btn btn-primary"
					onclick="fn_insertLedger()">입력하기</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script type="text/javascript">

window.onload = function() {
	$('#submitSelectMonth').click(function() {
		fn_selectMonth();
	});
	
	$('#btnPrevMonth').click(function() {
		var today = $('#todayTitle').text(); // YYYY-MM
		var tempToday = parseInt(today.replace('-','')); // YYYYMM
		
		tempToday = String(tempToday-1); // YYYYMM-1
		today = tempToday.substring(0,4)+'-'+tempToday.substring(4,6);

		var input = $('#selectMonth').find('input[name=date]').val(today);
		
		fn_selectMonth();
	});
	
	$('#btnNextMonth').click(function() {
		var today = $('#todayTitle').text(); // YYYY-MM
		var tempToday = parseInt(today.replace('-','')); // YYYYMM
		
		tempToday = String(tempToday+1); // YYYYMM+1
		today = tempToday.substring(0,4)+'-'+tempToday.substring(4,6);
		
		var input = $('#selectMonth').find('input[name=date]').val(today);
		
		fn_selectMonth();
	});
	
	// ledger insert form 날짜 입력 용
	$('#selectDate').daterangepicker({
		locale:{
			format: 'YYYY-MM-DD'
		},
		singleDatePicker: true,
		showDropdowns: true
	});
}
	
function fn_selectMonth() {
	var form = $("#selectMonth");
	form.action = "<c:url value='/ledger/calendar.do'/>";
	form.submit();
}

//[Modal]: 가계부 내역 입력 Modal 열기
function fn_openInsertModal(){
	// Modal 열기
	$("#modal-lg-insert").modal("show");
}

// 가계부 입력
function fn_insertLedger() {
	var form = document.getElementById("ledgerInputForm");
	form.action = "<c:url value='/ledger/insertLedger.do'/>";
	form.submit();
}

// Modal창 닫기
function fn_closeModal(){
	$("#modal-lg-insert").modal("hide");
	$("#modal-lg-update").modal("hide");
}

	
</script>