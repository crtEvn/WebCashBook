<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script>
	function fn_insert(){
		var form = document.getElementById("ledgerInput");
		form.action="<c:url value='/ledger/insertLedger.do'/>";
		form.submit();
	}
</script>

<form name="ledgerInput" id="ledgerInput" method="post">
<input type="hidden" name="user_idx" value="1">
<div>
	<table>
		<tr>
			<th>날짜</th>
			<th>입출</th>
			<th>분류</th>
			<th>분류</th>
			<th>내용</th>
			<th>금액</th>
			<th>자산</th>
		</tr>
		<tr>
			<th><input name="date" type="date" id="inputDate" class="form-control"></th>
			<th>
				<select name="account" class="form-control">
					<option value="수입">수입</option>
					<option value="지출">지출</option>
				</select>
			</th>
			<th>
				<select name="category" class="form-control">
					<option value="ctgr_1">ctgr_1</option>
					<option value="ctgr_2">ctgr_2</option>
				</select>
			</th>
			<th>
				<select name="sub_category" class="form-control">
					<option value="sb_ctgr_1">sb_ctgr_1</option>
					<option value="sb_ctgr_2">sb_ctgr_2</option>
				</select>
			</th>
			<th>
				<input name="description" type="text" autocomplete="off" class="form-control">
			</th>
			<th>
				<input name="amount" type="number" class="form-control">
			</th>
			<th>
				<select name="asset" class="form-control">
					<option value="현금">현금</option>
					<option value="부산은행">부산은행</option>
				</select>
			</th>
		</tr>
	</table>
</div>
<a href='#' onclick="fn_insert()">입력</a>
</form>