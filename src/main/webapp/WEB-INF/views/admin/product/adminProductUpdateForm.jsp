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
	            	/* $("#filename").append("<img src='upload/"+data.FILENAME+"' height='150'/>"); */
	            	$('#content').append("<img width='600' style='display: block;' src='upload/"+ data.FILENAME + "'/>")
	            }
	        },
	        error: function() {alert("실패");}
		});
	
	});
});

$(function(){
	$('#myButton1').click( function(){
		
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
	            	document.getElementById('image').value=data.FILENAME;
	            
	            	$("#filename").html("<div>"+data.FILENAME+"</div>");           	
	            	console.log(data.FILENAME);
	            	$("#filename").append("<img src='upload/"+data.FILENAME+"' width='300'/>");
	            }
	        },
	        error: function() {alert("실패");}
		});
	
	});
});
</script>
<article>
	<h1>공지사항 작성</h1>
	<form name="frm" method="post" action="adminProductUpdate">
	<input type="hidden" name="product_num" value="${dto.product_num}">
		<table class="adminTable">
			<!-- 공지사항 작성 -->
			<tr>
				<th width="20%">제목</th>
				<td style="text-align: left;">
					<input type="text" name="title" id="title" value="${ dto.title }" style="width: 800px; height: 30px; outline: none; font-size: 130%; border: 0; border-bottom: 1px solid black;">
				</td>
			</tr>
			<tr>
				<th width="20%">지역 카테고리</th>
				<td style="text-align: left;">
					<input type="text" name="category" id="search" list="searchOptions" class="content-input" style="width: 100%;" value="${dto.category}"/>
		            <datalist id="searchOptions" class="content-input" style="padding-right: 300px;">
		            	<c:forEach items="${countryList}" var="country">	
		            		<option value="${country}" style="padding-right: 300px;">
		                </c:forEach>
		            </datalist>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td style="text-align: left;">
					<%-- <textarea name="content" id="content" rows="15" cols="80" style="font-size: 130%; resize: none;">${ dto.content }</textarea> --%>
					<div id="content" style="font-size: 130%;width: 800px; min-height: 400px; border: 1px solid #dcdcdc; overflow-y: auto;" contenteditable="true">${ dto.content }</div>
					<input type="hidden" name="content" id="formcontent">
				</td>
			</tr>
			<tr>
				<th>이미지 첨부</th>
				<td style="text-align: left;">
					<input type="hidden" name="image" id="image" value="${dto.image}">
	   				<div id="filename">
	   					<div id="filename"><div>${dto.image}</div><img src="upload/${dto.image}" width="300"></div>
	   				</div>
				</td>
			</tr>
		</table>
		<input type="submit" class="input_button" value="수정하기" style="background: rgb(0, 37, 108); float: right;" onclick="return productCheck('${countryList}')">
		<input type="button" class="input_button" value="목록으로" onClick="history.back()" style="float: right;">
	</form>
	<div style="position:relative;  border:1px solid black; width:500px; margin:0 auto;">
	<form name="fromm" id="fileupForm" method="post" enctype="multipart/form-data">
		<input type="file" name="fileimage"><input type="button" id="myButton" value="추가하기">&nbsp;
		<input type="button" id="myButton1" value="타이틀 이미지">
	</form>
	</div>
</article>
<%@ include file="../include/footer.jsp"%>