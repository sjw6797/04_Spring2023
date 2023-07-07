<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<article>
	<h1>인기 여행지</h1>
	<form name="frm" method="post">
		<table class="adminTable">
			<!-- 게시물의 내용 -->
			<tr>
				<th width="20%">제목</th>
				<td style="text-align: left;">${dto.title}</td>
			</tr>
			<tr>
				<th>등록일</th>
				<td style="text-align: left;">
					<fmt:formatDate value="${dto.indate}" pattern="yyyy-MM-dd" />
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td style="text-align: left;">
					<pre style="width: 100%; height: 100%;  white-space: pre-wrap; font-size: 150%;">${dto.content}</pre>
				</td>
			</tr>
		</table>
		<input type="button" class="input_button" value="목록으로" onClick="history.back()" style="float: right; margin-right: 30px; margin-bottom: 10px;">
		<input type="button" class="input_button" value="수정하기" onClick="location.href='adminProductUpdateForm?product_num=${ dto.product_num }' " style="float: right; margin-bottom: 10px;">
	</form>
</article>
<%@ include file="../include/footer.jsp"%>