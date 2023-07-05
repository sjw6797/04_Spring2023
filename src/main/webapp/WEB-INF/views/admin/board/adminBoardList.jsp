<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script>
	$(function() { // 공지사항 작성
		$('#insert_board').click(function() {
			location.href = 'air.do?command=adminBoardForm'
		})
	})

	function deleteBoard(board_num) {
		if (confirm("정말 삭제하시겠습니까?")) {
			$.ajax({
				url : "air.do?command=adminDeleteBoard&board_num=" + board_num,
				type : "GET",
				async : false,
				success : function(message) {
					if (message == "") {
						alert("게시물 삭제에 실패했습니다")
					} else {
						alert("게시물 삭제에 성공했습니다")
						location.reload(true)
					}
				}
			})
		} else {
			return false
		}

	}
</script>
<article>
	<h1>공지리스트</h1>
	<form method="post" action="air.do">
		<input type="hidden" name="command" value="adminBoardList">
		<div class="input_div">
			<input type="text" name="key" class="input_text" placeholder="제목을 입력하세요" value="${ key }">
			<input type="submit" class="input_button" value="  찾기  ">
			<input type="button" class="input_button" id="insert_board" value="글쓰기" style="position: absolute; background: #00256c;">
		</div>
	</form>
	<table class="adminTable">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성일</th>
			<th>비고</th>
		</tr>
		<c:forEach items="${boardList}" var="boardVO">
			<tr>
				<td>${boardVO.board_num}</td>
				<td>
					<a href="air.do?command=adminBoardDetail&board_num=${ boardVO.board_num }"> ${boardVO.title}</a>
				</td>
				<td>
					<fmt:formatDate value="${boardVO.indate}" pattern="yyyy-MM-dd" />
				</td>
				<td>
					<input type="button" value="수정" onclick="location.href='air.do?command=adminBoardUpdateForm&board_num=${ boardVO.board_num }' ">
					<input type="button" value="삭제" onclick="return deleteBoard('${ boardVO.board_num }')">
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
</article>
<jsp:include page="../../page.jsp">
	<jsp:param name="command" value="air.do?command=adminBoardList" />
</jsp:include>
<%@ include file="../include/footer.jsp"%>