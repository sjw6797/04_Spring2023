<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script>
	function deleteQna(qna_num) {
		if (confirm("정말 삭제하시겠습니까?")) {
			$.ajax({
				url : "qnaDelete?qna_num=" + qna_num,
				type : "GET",
				async : false,
				success : function(message) {
					if (message == "") {
						alert("Q&A삭제에 실패했습니다")
					} else {
						alert("Q&A삭제에 성공했습니다")
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
	<h1>Q&amp;A 게시글 리스트</h1>
	<form method="post" action="adminQnaList">
		<div class="input_div">
			<input type="text" name="key" class="input_text" placeholder="제목을 입력하세요" value="${ key }">
			<input type="submit" class="input_button" value="  찾기  ">
		</div>
	</form>
	<table class="adminTable">
		<tr>
			<th>번호</th>
			<th style="width: 40%;">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>비고</th>
		</tr>
		<c:forEach items="${qnaList}" var="qnaVO">
			<tr>
				<td>${qnaVO.qna_num}
					<c:choose>
						<c:when test='${qnaVO.result=="N"}'>(미처리)</c:when>
						<c:otherwise>(완료)</c:otherwise>
					</c:choose>
				</td>
				<td>
					<a href="adminQnaDetail?qna_num=${ qnaVO.qna_num }"> ${qnaVO.title}</a>
				</td>
				<td>${ qnaVO.name }(${qnaVO.id})</td>
				<td>
					<fmt:formatDate value="${qnaVO.indate}" pattern="yyyy-MM-dd" />
				</td>
				<td>
					<input type="button" value="삭제" onclick="return deleteQna('${ qnaVO.qna_num }')">
				</td>
			</tr>
		</c:forEach>
	</table>
</article>

<jsp:include page="../include/paging.jsp">
	<jsp:param name="command" value="adminQnaList" />
</jsp:include>

<%@ include file="../include/footer.jsp"%>