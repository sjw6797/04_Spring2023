<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<link type="text/css" rel="stylesheet" href="/css/qna.css">
<script src="/script/qna.js"></script>

<div id="qna_sub_img">
	<img alt="고객센터이미지" src="/images/qna/qnaList_logo.jpg" style="margin:0 auto; display: block;">
</div>
<div class="clear"></div>

<div id="content">
	<h2>Q&amp;A</h2>
	<form action="qnaList" name="frm" method="get">
		<div class="table_div">
			<h3 class="h3">Q&amp;A검색</h3>
			<div class="table_keyword">
				<label style="">키워드 검색</label>
				<div class="keyword_board">
					<input type="text" name="key" class="keyword_input" placeholder="입력하세요" value="${key}">
					<input type="submit" value="검색" id="qnaList_btn1"  class="qnaList_btn">
				</div>
			</div>
			<input type="button" value="질문하기" id="qnaList_btn2"class="qnaList_btn" onClick="location.href='qnaWriteForm' ">
		</div>
	</form>
	<div class="clear"></div>
		<a href="qnaDetail?qna_num=${ qvo.qna_num }">${ qvo.title }</a>
	<table id="tableList" style="border-bottom: 2px solid #00256c;border-top: 2px solid #00256c;">
		<tr>
			<th>제목</th><th>작성자(회원번호)</th><th>조회 수</th><th>답변 결과</th><th>작성일</th>
		</tr>
		<c:forEach items="${ qnaList }" var="qvo" varStatus="status">
			<tr style="text-align: center">			
				<td style="text-align: left; font-weight: bold;" width="30%">
					<c:choose>
							<c:when test="${qvo.passcheck=='Y'}">
								<a href="#" onclick="passCheck('${qvo.qna_num}')">
									${qvo.title}
								</a>&nbsp;
								<img src="/images/key.png" style="width:20px; vertical-align: middle;">
							</c:when>
							<c:otherwise>
								<a href="qnaDetail?qna_num=${ qvo.qna_num }">${ qvo.title }</a>								
							</c:otherwise>
					</c:choose>
				</td>
					
				<td>${qvo.name}(${ qvo.qna_num })</td>
				<td>${qvo.readcount}</td>
				<c:choose>
					<c:when test="${qvo.result=='Y'}">
						<td>답변완료</td>
					</c:when>
					<c:otherwise>
						<td>대기중</td>
					</c:otherwise>
				</c:choose>
				<td>
					<fmt:formatDate value="${qvo.indate }" pattern="yyyy-MM-dd" />
				</td>
		</c:forEach>
	</table>

</div>
<div class="clear"></div>
<jsp:include page="../include/paging.jsp">
	<jsp:param name="command" value="qnaList" />
</jsp:include>
	
<%@ include file="../include/footer.jsp"%>