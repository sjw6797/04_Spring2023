<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<link type="text/css" rel="stylesheet" href="/css/board.css">
<div id="content">
	<h1>공지사항</h1>
	<hr style="border-top: 2px solid #00256c;">
	<div class="detail_title">
		<h2 class="h2">${dto.title}</h2>
		<span class="span"><fmt:formatDate value="${dto.indate}" pattern="yyyy-MM-dd" /></span>
	</div>
	<br>
	<hr>
	<div class="detail_content">
		<c:if test="${ not empty dto.image }">
			<img alt="이미지" src="upload/${dto.image}" class="detail_img">
		</c:if>
		<pre style="font-size: 150%; font-weight: bold; white-space: normal;">${dto.content}</pre>
	</div>
	<button class="content_btn" onclick="history.back()">목록으로</button>
</div>

<%@ include file="../include/footer.jsp"%>