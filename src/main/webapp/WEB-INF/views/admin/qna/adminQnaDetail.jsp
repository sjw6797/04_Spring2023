<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<article>
	<h1>Q&amp;A 게시판</h1>
	<form name="frm" method="post" action="qnaReply">
		<input type="hidden" name="qna_num" value="${ qnaVO.qna_num }">
		<table class="adminTable">
			<!-- 게시물의 내용 -->
			<tr>
				<th width="20%">제목</th>
				<td style="text-align: left;">${qnaVO.title}</td>
			</tr>
			<tr>
				<th>등록일</th>
				<td style="text-align: left;">
					<fmt:formatDate value="${qnaVO.indate}" pattern="yyyy-MM-dd" />
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td style="text-align: left;">
					<pre>${qnaVO.content}</pre>
				</td>
			</tr>
			<tr>
				<th>답변</th>
				<td style="text-align: left;">
					<c:choose>
						<c:when test="${ qnaVO.result == 'Y' }">
							<pre style="font-size: 130%;">${qnaVO.reply}</pre>
						</c:when>
						<c:otherwise>
							<textarea name="reply" rows="10" cols="70" autocapitalize="none" style="resize: none; outline: none;" ></textarea>
							<input type="submit" class="input_button" value="작성하기" style="bottom: 10px; position: relative;">
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
		<input type="button" class="input_button" value="목록으로" onClick="location.href='adminQnaList'"
				style="float: right; margin-right: 30px; margin-bottom: 10px;">
	</form>
</article>
<%@ include file="../include/footer.jsp"%>