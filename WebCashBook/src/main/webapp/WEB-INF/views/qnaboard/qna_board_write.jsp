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
					<h1>QnA 글쓰기</h1>
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
					<div class="card card-primary card-outline">
					
              			<div class="card-header">
                			<h3 class="card-title">Q&A 글쓰기</h3>
              			</div>
              			<!-- /.card-header -->
              			
              			<div class="card-body">
              				<form name="writeQnaContentForm" id="writeQnaContentForm" method="post">
                				<div class="form-group">
                  					<input name="subject" class="form-control" placeholder="제목:" minlength="1" maxlength="100">
                				</div>
                				<div class="form-group">
                    				<textarea name="content" id="compose-textarea" class="form-control" style="height: 800px" minlength="1" maxlength="1000">
                    				</textarea>
               					 </div>
                				<div class="form-group">
                  					<div class="btn btn-default btn-file">
                    					<i class="fas fa-paperclip"></i> Attachment
                    					<input type="file" name="attachment">
                  					</div>
                  					<p class="help-block">Max. 32MB</p>
                				</div>
                			</form>
              			</div>
              			<!-- /.card-body -->
              			
              			<div class="card-footer">
                			<div class="float-right">
                  				<button type="button" class="btn btn-primary" onclick="fn_insertQnaContent()">
                  					<i class="fas fa-pencil-alt"></i> 글쓰기
                  				</button>
                			</div>
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

window.onload = function() {
	$(function () {
		//Add text editor
		$('#compose-textarea').summernote({
			height: 150
		})
	})
}

function fn_insertQnaContent() {
	
	var subject = $('input[name=subject]').val();
	var content = $('textarea[name=content]').val();
	
	var form = document.getElementById("writeQnaContentForm");
	form.action = "<c:url value='/qna/insertQnaContent.do'/>";
	form.submit();
}
</script>