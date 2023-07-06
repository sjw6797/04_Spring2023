<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<article>
	<h1>유저관리 리스트</h1>
	<div class="input_div">
		<form name="frm_mList" action="adminMemberList" method="post">
			<input type="text" id="input_text" name="key" class="input_text" placeholder="아이디를 입력하세요" value="${ key }">
			<input type="submit" value="  찾기  " class="input_button" id="searchBtn">
		</form>
	</div>
	<table class="adminTable">
		<tr>
			<th width="13%">번호</th>
			<th>유저 아이디</th>
			<th>가입날짜</th>
			<th>상태</th>
			<th width="17%">비고</th>
		</tr>
		<c:forEach items="${memberList}" var="memberVO">
			<tr>
				<!-- 탈퇴상태 -->
				<c:if test="${ memberVO.useyn =='N' }">
					<td style="color: red;">${ memberVO.member_num }</td>
					<td style="color: red;">${memberVO.id}</td>
					<td style="color: red;">
						<fmt:formatDate value="${memberVO.indate}" pattern="yyyy-MM-dd" />
					</td>
					<td style="color: red;">탈퇴</td>
				</c:if>
				<!-- 사용상태 -->
				<c:if test="${ memberVO.useyn =='Y' }">
					<td>${ memberVO.member_num }</td>
					<td>${memberVO.id}</td>
					<td>
						<fmt:formatDate value="${memberVO.indate}" pattern="yyyy-MM-dd" />
					</td>
					<td>사용</td>
				</c:if>
				<td>
					<input type="button" value="삭제" onclick="location.href='deleteMember?member_num=${memberVO.member_num}' ">
					<input type="button" value="복구" onclick="location.href='useMember?member_num=${memberVO.member_num}' ">
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
</article>

<jsp:include page="../include/paging.jsp">
	<jsp:param name="command" value="adminMemberList" />
</jsp:include>

<%@ include file="../include/footer.jsp"%>