<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<link type="text/css" rel="stylesheet" href="/css/qna.css">
<script src="/script/qna.js"></script>

<script>
	function go_check() {
		if (document.frm.title.value == "") {
			alert("제목을 입력해주세요");
			document.frm.title.focus();
			return false
		} else if (document.frm.content.value == "") {
			alert("내용을 입력해주세요");
			document.frm.content.focus();
			return false
		}else{
			return true;	
		}
	}
</script>

<div id="qna_sub_img">
	<img alt="고객센터이미지" src="/images/qna/qnaList_logo.jpg" style="margin:0 auto; display: block;">
</div>
<div class="clear"></div>

<!-- writeForm으로 넘어올때 loginUser세션으로부터 사용자 id,name,email 등을 가지고 오고, 제목/내용/첨부파일내용을 qnaInsert로 보내서 작성한다 -->
<div id="content" style="width:90%">
	<h2>Q&amp;A</h2>
	<form action="qnaInsert" name="frm" method="post">
		<input type="hidden" name="mnum" value="${loginUser.member_num}">
		<div class="detail_title">
			<h2 class="h2"><input type="text" name="title" class="qna_title_input" <%-- value="${qnaVO.title}" --%>></h2>
		</div>
		<div id="detail_info1" class="detail_info">
			<div class="detail_box_h2">작성자</div><div class="detail_box"><input type="text" name="name" value="${loginUser.name}" disabled></div>
			<div class="detail_box_h2">아이디</div><div class="detail_box"><input type="text" name="id" value="${loginUser.id}" disabled></div>
			<div class="detail_box_h2">이메일</div><div class="detail_box"><input type="text" name="email" value="${loginUser.email}" disabled></div>
		</div>
		<div class="detail_content" style="border-bottom: 2px solid #00256c;">
		<div id="detail_info2" class="detail_info">
			<div class="detail_box_h2">비밀글로하기</div><div class="detail_box"><input name="check" type="checkbox" value="secret" onchange="enabled()"></div>
			<div class="detail_box_h2">비밀번호</div><div class="detail_box"><input type="password" name="pass" disabled="disabled"></div>
		</div>
		<div class="detail_box_h2">문의사항</div>
		
			<textarea  rows="21" cols="100" name="content" style="width:620px;height:350px;resize: none" placeholder="내용을 작성하세요" ></textarea>
			<div style="width:300px; text-align: center; float:left">
					<c:choose>
						<c:when test="${empty qnaVO.imgfilename}">
							<img src="/upload/noname.jpg" id="preview" height="300">
						</c:when>
						<c:otherwise>
							<img src="/upload/${qnaVO.imgfilename}" id="preview" height="300">
						</c:otherwise>
					</c:choose>
					<div id="image" style="float:left"></div> <!-- 이미지이름만 화면에 보여주는 역할 -->
				<input type="hidden" name="imgfilename">
				<input type="button" value="파일선택" onclick="selectimg();">
			</div>
		</div>
		<input class="content_btn" type="submit" value="작성하기" onclick="return go_check();">
		<button class="content_btn" onclick="location.href='qnaList'">목록으로</button>
	</form>
</div>
	<div class="clear"></div>
<%@ include file="../include/footer.jsp"%>

<%@ include file="../include/footer.jsp"%>