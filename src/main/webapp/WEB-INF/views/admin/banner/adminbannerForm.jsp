<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<style>
input#file-upload-button {
	background: rgb(0, 37, 108);
}
</style>
<script type="text/javascript">
$(function(){
	$('#myButton').click( function(){
		
		var formselect = $("#fileupForm")[0];   // 지목된 폼을 변수에 저장
		var formdata = new FormData(formselect);   // 전송용 폼객에 다시 저장
		
		$.ajax({    // 웹페이지 이동 또는 새로고침이 필요없는 request요청
			url:"<%=request.getContextPath() %>/fileup",    // 현재주소의 fileup 리퀘스트로 요청  http://localhost:8070/fileup
			type:"POST",
			enctype:"multipart/form-data",
			async: false, 
			data: formdata,
	    	timeout: 10000,
	    	contentType : false,
	        processData : false,
	        
	        success : function(data){    // controller 에서 린턴된 해시맵이  data 로 전달됩니다
	            if( data.STATUS == 1 ){  	// 동적으로 div태그 달아주기.
	            	$("#filename").html("<div>"+data.FILENAME+"</div>");
	            	$("#image").val(data.FILENAME);
	            	$("#filename").append("<img src='upload/"+data.FILENAME+"' height='150'/>");
	            }
	        },
	        error: function() {alert("실패");}
		});
	
	});
});
</script>
<article>
	<h1>배너 작성</h1>
	<form name="frm" method="post" action="insertBanner">
		<table class="adminTable">
			<tr>
				<th width="20%">이미지 제목</th>
				<td style="text-align: left;">
					<input type="text" name="name" id="name" style="width: 800px; height: 30px; outline: none; font-size: 130%; border: 0; border-bottom: 1px solid black;">
				</td>
			</tr>
			<tr>
				<th>이미지 첨부</th>
				<td style="text-align: left;">
					<input type="hidden" name="image" id="image">
	   				<div id="filename"></div>
				</td>
			</tr>
		</table>
		<input type="submit" class="input_button" value="작성하기" style="background: rgb(0, 37, 108); float: right;" onclick="return bannerCheck()">
		<input type="button" class="input_button" value="목록으로" onClick="history.back()" style="float: right;">
	</form>
	<div style="position:relative;  border:1px solid black; width:500px; margin:0 auto;">
	<form name="fromm" id="fileupForm" method="post" enctype="multipart/form-data">
		<input type="file" name="fileimage"><input type="button" id="myButton" value="추가">
	</form>
	</div>
</article>
<%@ include file="../include/footer.jsp"%>