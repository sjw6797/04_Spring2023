<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<link type="text/css" rel="stylesheet" href="/css/board.css">
<style>
	.detail_content pre {
		width: 100%; height: 100%;  white-space: pre-wrap; font-size: 150%;
	}
	.button{
		padding: 10px 20px; box-sizing: border-box; font-size: 120%; font-weight: bold; 
		background-color: #51abf3; color: white; border: 0; border-radius: 10px 10px; cursor: pointer;
	}
	
	.button:hover {
		background-color: #0a70c2;
	}
	#span {
		display: block;
	}
	.product_div div {
		font-size: 120%;
		margin-bottom: 20px;
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
<div id="content" style="width: 1000px; position: relative;">
	<div style="width: 600px;">
		<div class="detail_title">
			<h1 style="width: 600px; border-bottom: 1px solid gray;margin-bottom: 30px; padding-bottom: 20px;">${dto.title}</h1>
			<span class="span" style="font-size: 110%; display: block; width: 600px; border-bottom: 1px solid gray; margin-bottom: 30px; padding-bottom: 20px;">작성날짜 : <fmt:formatDate value="${dto.indate}" pattern="yyyy-MM-dd" /></span>
		</div>
		<br>
		<div class="product_div" style="margin-bottom: 20px;">${dto.content}</div>
	</div>
	<div style="width: 250px; height: 300px; border: 1px solid #E2E2E2; position: absolute; right: 0; top: 0; box-sizing: border-box;" id="scroll">
		<div style="margin: 0 auto; text-align: center;">
			<h2 style="display: block;">${dto.category}</h2>
			<button class="button" style="width: 176px;"onclick="location.href='/?con1=${dto.category}' ">
				<img src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIxMCIgaGVpZ2h0PSIxOCIgdmlld0JveD0iMCAwIDEwIDE4Ij4KICAgIDxwYXRoIGZpbGw9IiNGRkYiIGZpbGwtcnVsZT0iZXZlbm9kZCIgZD0iTTMuMzMzIDEwLjM4NUgwTDYuNjY3IDB2Ny42MTVIMTBMMy4zMzMgMTh6Ii8+Cjwvc3ZnPgo=">
				<span>여행하러 가기</span>
			</button>
			<span style="color: #848c94; font-size: 13px; margin: 0 auto; margin-top: 10px; width: 176px; text-align: left;" id="span">버튼 클릭시 항공편 예약페이지로 즉시 이동됩니다.</span>
			<img src="">
		</div>
	</div>
</div>
<button class="content_btn" onclick="history.back()">목록으로</button>

<%@ include file="../include/footer.jsp"%>