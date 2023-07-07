<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script>
	$(function() { // 상품 작성
		$('#insert_product').click(function() {
			location.href = 'adminProductForm'
		})
	})
</script>
<article>
	<h1>인기 여행지 리스트</h1>
	<form action="adminProductList">
		<div class="input_div">
			<input type="text" name="key" class="input_text" placeholder="제목을 입력하세요" value="${ key }">
			<input type="submit" class="input_button" value="  찾기  ">
			<input type="button" class="input_button" id="insert_product" value="추가하기" style="position: absolute; background: #00256c;">
		</div>
	</form>
	<table class="adminTable">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성일</th>
			<th>비고</th>
		</tr>
		<c:forEach items="${productList}" var="productVO">
			<tr>
				<td>${productVO.product_num}</td>
				<td>
					<a href="adminProductDetail?product_num=${ productVO.product_num }"> ${productVO.title}</a>
				</td>
				<td>
					<fmt:formatDate value="${productVO.indate}" pattern="yyyy-MM-dd" />
				</td>
				<td>
					<input type="button" value="수정" onclick="location.href='adminProductUpdateForm?product_num=${productVO.product_num}' ">
					<input type="button" value="삭제" onclick="location.href='adminProductDelete?product_num=${productVO.product_num}' ">
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
</article>
<jsp:include page="../include/paging.jsp">
	<jsp:param name="command" value="adminProductList" />
</jsp:include>
<%@ include file="../include/footer.jsp"%>