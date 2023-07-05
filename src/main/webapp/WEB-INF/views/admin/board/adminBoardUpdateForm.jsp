<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<style>
input#file-upload-button {
	background: rgb(0, 37, 108);
}
</style>
<script>
	$(function() {
		$('#imgSelector').change(function() {
			$('#preview').css("display", "inline")
			setImageFromFile(this, '#preview');
		});

		function setImageFromFile(input, expression) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$(expression).attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
	})
</script>
<article>
	<h1>공지사항 작성</h1>
	<form name="frm" method="post" action="air.do?command=adminBoardUpdate&board_num=${ boardVO.board_num }" enctype="multipart/form-data">
		<table class="adminTable">
			<!-- 공지사항 작성 -->
			<tr>
				<th width="20%">제목</th>
				<td style="text-align: left;">
					<input type="text" name="title" value="${ boardVO.title }" style="width: 800px; height: 30px; outline: none; font-size: 130%; border: 0; border-bottom: 1px solid black;">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td style="text-align: left;">
					<textarea name="content" rows="15" cols="80" style="font-size: 130%; resize: none;">${ boardVO.content }</textarea>
				</td>
			</tr>
			<tr>
				<th>이미지 첨부</th>
				<td style="text-align: left;">
					<img src="upload/${ boardVO.image }" width="300" id="preview" onerror="this.style.display='none'">
					<input type="file" name="image" id="imgSelector">
				</td>
			</tr>
		</table>
		<input type="submit" class="input_button" value="작성하기" style="background: rgb(0, 37, 108); float: right;">
		<input type="button" class="input_button" value="목록으로" onClick="history.back()" style="float: right;">
	</form>
</article>
<%@ include file="../include/footer.jsp"%>