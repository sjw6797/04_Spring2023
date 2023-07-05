<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<link type="text/css" rel="stylesheet" href="/css/board.css">
<div id="content">
<div id="sub_img" style="background-image: url('image/공지사항.png');">
	<!-- <img alt="고객센터이미지" src="image/공지사항.png" style="float: right; display: block;"> -->
</div>
	<h2>공지사항</h2>
	<form action="boardList" method="get">
		<div class="table_div">
			<div class="table_keyword">
				<label>제목을 입력하세요</label>
				<div class="keyword_board">
					<input type="text" class="keyword_input" placeholder="입력하세요" name="key" value="${ key }">
					<input type="submit" value="검색" class="keyword_submit">
				</div>
			</div>
		</div>
	</form>
	<table id="tableList">
	<c:forEach items="${boardList}" var="dto">
		<tr onclick="location.href='boardDetail?board_num=${dto.board_num}' ">
			<th>${dto.board_num}</th>
			<td id="maintd">${dto.title}</td>
			<td><fmt:formatDate value="${dto.indate}" pattern="yyyy-MM-dd"/></td>
		</tr>
	</c:forEach>
	</table>
</div>

<jsp:include page="../include/paging.jsp">
	<jsp:param name="command" value="boardList" />
</jsp:include>

<%@ include file="../include/footer.jsp"%>
