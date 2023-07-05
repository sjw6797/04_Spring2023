<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<link type="text/css" rel="stylesheet" href="/css/qna.css">

<style>

</style>
<script>
	function insertQna() {
		if (document.frm.title.value == "") {
			alert("제목을 입력해주세요");
			document.frm.title.focus();
			return false
		} else if (document.frm.content.value == "") {
			alert("내용을 입력해주세요");
			document.frm.content.focus();
			return false
		}
	}
</script>
<div id="sub_img">
	<div class="sub_text">
		이젠항공은 언제나 고객의 말씀을 경청하겠습니다<br>고객님의 문의, 제안, 칭송, 불편사항을 남겨주세요
	</div>
	<img alt="고객센터이미지" src="https://flyasiana.com/C/pc/image/sub/img_customer_visual.png" style="float: right; display: block;">
</div>
<div class="clear"></div>
<div id="qna_content">
	<form name="frm" method="post" action="qnaInsert">
		<div><h2>성 명&nbsp;&nbsp;<input type="text" name="id" value="${loginUser.id}" disabled></h2></div>
		<div><h2>아이디&nbsp;&nbsp;<input type="text" name="name" value="${loginUser.name}" disabled></h2></div>
		<div class="qna_title">
			<h2 class="h2">Q&amp;A제목</h2>
			<input type="text" name="title" class="qna_title_input" placeholder="제목을 입력하세요" value="${dto.title}">
		</div>
		<div class="qna_content">
			<h2 class="h2">Q&amp;</h2>
			<textarea rows="15" cols="100" name="content" class="qna_content_text" >${dto.content}</textarea>
		</div>
		<input type="button" value="목록으로" class="qna_button" onclick="location.href='qnaList' ">
		<input type="submit" value="작성하기" class="qna_button" style="margin-right: 3%;" onclick="return insertQna()">
		${message}
	</form>
</div>
<div class="clear"></div>

<%@ include file="../include/footer.jsp"%>