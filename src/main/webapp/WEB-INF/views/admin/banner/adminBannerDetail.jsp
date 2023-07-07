<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<article>
	<h1>배너</h1>
	<form name="frm" method="post">
		<table class="adminTable">
			<!-- 게시물의 내용 -->
			<tr>
				<th width="20%">배너 이름</th>
				<td style="text-align: left;">${dto.name}</td>
			</tr>
		</table>
		<div style="height: 550px;">
			<img src="upload/${ dto.image }" width="100%" height="500" style="position: absolute; left: 0;">
		</div>
		<input type="button" class="input_button" value="목록으로" onClick="history.back()" style="float: right; margin-right: 30px; margin-bottom: 10px;">
	</form>
</article>
<%@ include file="../include/footer.jsp"%>