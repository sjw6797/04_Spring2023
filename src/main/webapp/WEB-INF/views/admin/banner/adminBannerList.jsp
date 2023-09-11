<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script>
	$(function() { // 공지사항 작성
		$('#insert_banner').click(function() {
			location.href = 'adminBannerForm'
		})
	})
</script>
<article>
	<h1>배너리스트</h1>
	<form action="adminBannerList">
		<div class="input_div">
			<input type="text" name="key" class="input_text" placeholder="배너정보를 입력하세요" value="${ key }">
			<input type="submit" class="input_button" value="  찾기  ">
			<input type="button" class="input_button" id="insert_banner" value="추가하기" style="position: absolute; background: #00256c;">
		</div>
	</form>
		<table class="adminTable">
			<tr>
				<th>번호</th>
				<th>배너이름</th>
				<th>순서</th>
				<th>비고</th>
			</tr>
			<c:forEach items="${bannerList}" var="dto">
			<form action="updateBanner" method="post">
			<input type="hidden" name="old_oseq" value="${dto.oseq}">
			<input type="hidden" name="banner_num" value="${dto.banner_num}">
			<c:choose>
				<c:when test="${dto.oseq==-1}">
					<tr>
					<td style="color: red;">${dto.banner_num}</td>
					<td>
						<a href="adminBannerDetail?banner_num=${ dto.banner_num }"  style="color: red;">${dto.name}</a>
					</td>
					<td>
						<select name="oseq">
							<c:choose>
								<c:when test="${dto.oseq==-1}">
									 <option value="-1" selected>미사용</option>
								</c:when>
								<c:otherwise>
									<option value="-1" >미사용</option>					
								</c:otherwise>
							</c:choose>
							<c:forEach var="cnt" begin="1" end="5" varStatus="status">
								<c:choose>
									<c:when test="${cnt==dto.oseq}">
										<option value="${cnt}" selected>${cnt}</option>
									</c:when>
									<c:otherwise>
                                   		<option value="${cnt}" >${cnt}</option>
                                    </c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
					<td>
						<input type="submit" value="저장">
						<input type="button" value="삭제" onclick="location.href='adminBannerDelete?banner_num=${dto.banner_num}' ">
					</td>
				</tr>
				</c:when>
				
				
				<c:otherwise>
					<input type="hidden" name="old_oseq" value="${dto.oseq}">
					<tr>
					<td>${dto.banner_num}</td>
					<td>
						<a href="adminBannerDetail?banner_num=${ dto.banner_num }">${dto.name}</a>
					</td>
					<td>
						<select name="oseq">
						<c:choose>
									<c:when test="${dto.oseq==-1}">
										 <option value="-1" selected>미사용</option>
									</c:when>
									<c:otherwise>
										<option value="-1" >미사용</option>					
									</c:otherwise>
								</c:choose>
							<c:forEach var="cnt" begin="1" end="5" varStatus="status">
								<c:choose>
									<c:when test="${cnt==dto.oseq}">
										<option value="${cnt}" selected>${cnt}</option>
									</c:when>
									<c:otherwise>
                                   		<option value="${cnt}" >${cnt}</option>
                                    </c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
					<td>
						<input type="submit" value="저장">
						<input type="button" value="삭제" onclick="location.href='adminBannerDelete?banner_num=${ dto.banner_num }' ">
					</td>
				</tr>
				</c:otherwise>
			</c:choose>
			</form>
			</c:forEach>
		</table>
	<br>
</article>
<jsp:include page="../include/paging.jsp">
	<jsp:param name="command" value="adminBannerList" />
</jsp:include>
<%@ include file="../include/footer.jsp"%>