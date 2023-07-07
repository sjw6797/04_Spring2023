<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<style>
#sub_img {
	width: 1000px;
	height: 300px;
	background-color: #e6e2df;
	margin: 20px auto;
	border-radius: 20px 20px 20px 20px;
	overflow: hidden;
}

.sub_text {
	position: relative;
	float: left;
	margin: auto;
	float: left;
	float: left;
	top: 30%;
	font-weight: bold;
	font-size: 130%;
	line-height: 50px;
	vertical-align: middle;
	left: 5%;
}

.qna_title {
	display: flex;
	padding: 30px 0;
	margin: 0 auto;
}

.qna_title_input {
	width: 70%;
	border: 0;
	border-bottom: 1px solid black;
	outline: none;
	font-size: 130%;
	font-weight: bold;
	margin: 0 auto;
}

.qna_content {
	display: flex;
	padding: 30px 0;
}

.qna_content_text {
	resize: none;
	margin: 0 auto;
	outline: none;
}

.h2 {
	margin-left: 30px;
}

.qna_button {
	position: relative;
	display: block;
	background-color: #013066;
	padding: 10px;
	color: white;
	border-radius: 10px;
	font-weight: bold;
	border: 0;
	float: right;
	margin-right: 7%;
}
</style>
<script>
	function insertQna() {
		if (document.frm.title.value == "") {
			alert("제목을 입력해주세요")
			return false
		} else if (document.frm.content.value == "") {
			alert("내용을 입력해주세요")
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
<div id="content">
	<form name="frm" method="post" action="air.do">
		<input type="hidden" name="command" value="qnaInsert">
		<div class="qna_title">
			<h2 class="h2">Q&A제목</h2>
			<input type="text" name="title" class="qna_title_input" placeholder="제목을 입력하세요">
		</div>
		<div class="qna_content">
			<h2 class="h2">Q&A내용</h2>
			<textarea rows="15" cols="100" name="content" class="qna_content_text"></textarea>
		</div>
		<input type="button" value="목록으로" class="qna_button" onclick="location.href='air.do?command=qnaList' ">
		<input type="submit" value="작성하기" class="qna_button" style="margin-right: 3%;" onclick="return insertQna()">
	</form>
</div>
<div class="clear"></div>
<%@ include file="../include/footer.jsp"%>