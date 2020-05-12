<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form name="ledgerInputForm" id="ledgerInputForm" method="post">
	<div class="form-group clearfix">
		<table class="text-center col-md-12">
			<tr>
				<td>
					<div class="icheck-success d-inline">
						<input type="radio" name="account" id="radioAccount1" value="수입" checked>
							<label for="radioAccount1"> 수입 </label>
					</div>
				</td>
				<td>
					<div class="icheck-danger d-inline">
						<input type="radio" name="account" id="radioAccount2" value="지출">
						<label for="radioAccount2"> 지출 </label>
					</div>
				</td>
				<td>
					<div class="icheck-info d-inline">
						<input type="radio" name="account" id="radioAccount3" value="이동"
							disabled> <label for="radioAccount3"> 이동 </label>
					</div>
				</td>
			</tr>
		</table>
	</div>

	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<button type="button" class="btn btn-default">날짜</button>
		</div>
		<input type="text" name="date" id="selectDate" class="form-control">
	</div>

	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<button type="button" class="btn btn-default">분류</button>
		</div>
		<select name="category" class="form-control">
			<option value="ctgr_1">ctgr_1</option>
			<option value="ctgr_2">ctgr_2</option>
		</select>
	</div>

	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<button type="button" class="btn btn-default">분류</button>
		</div>
		<select name="sub_category" class="form-control">
			<option value="sb_ctgr_1">sb_ctgr_1</option>
			<option value="sb_ctgr_2">sb_ctgr_2</option>
		</select>
	</div>

	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<button type="button" class="btn btn-default">내용</button>
		</div>
		<input name="description" type="text" autocomplete="off"
			class="form-control">
	</div>

	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<button type="button" class="btn btn-default">자산</button>
		</div>
		<select name="asset" class="form-control">
			<option value="현금">현금</option>
			<option value="부산은행">부산은행</option>
		</select>
	</div>

	<div class="input-group mb-3">
		<div class="input-group-prepend">
			<button type="button" class="btn btn-default">금액</button>
		</div>
		<input name="amount" type="number" autocomplete="off"
			class="form-control">
	</div>
</form>