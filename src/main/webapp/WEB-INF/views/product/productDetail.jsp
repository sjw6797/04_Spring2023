<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<link type="text/css" rel="stylesheet" href="/css/board.css">
<style>
	.detail_content pre {
		width: 100%; height: 100%;  white-space: pre-wrap; font-size: 150%;
	}
</style>
<script type="text/javascript">
/* 스크롤 */
$(window).scroll(function() {
   let scrollPoint = parseInt( $(window).scrollTop());

   // 제품상세용
   let $floater = $('#scroll'), startPoint = 140;
   if (parseInt(scrollPoint) > parseInt(startPoint)) {
      $floater.css("top", ($(window).scrollTop() - startPoint));
   } else if (parseInt(scrollPoint) <= parseInt(startPoint)) {
      $floater.css("top", 0);
   }

});
/* // 스크롤*/
</script>
<div id="content" style="width: 50%; border: 1px solid red; position: relative;">
	<div class="detail_title">
		<h1>${dto.title}</h1>
		<span class="span" style="font-size: 110%;">작성날짜 : <fmt:formatDate value="${dto.indate}" pattern="yyyy-MM-dd" /></span>
	</div>
	<br>
	<div class="product_div" style="margin-bottom: 20px;">${dto.content}</div>
	<div style="width: 200px; height: 300px; border: 1px solid red; position: absolute;right: 0; top: 0; box-sizing: border-box;" id="scroll">
		<div style="margin: 0 auto; text-align: center;">
			<h2>${dto.category}</h2>
		</div>
	</div>
</div>
<button class="content_btn" onclick="history.back()">목록으로</button>

<%@ include file="../include/footer.jsp"%>