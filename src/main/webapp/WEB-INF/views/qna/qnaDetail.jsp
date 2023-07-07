<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<link type="text/css" rel="stylesheet" href="/css/qna.css">
<script src="/script/qna.js"></script>

<style type="text/css">

</style>
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
						location.replace('qnaList')
					}
				}
			})
		} else {
			return false
		}

	}
</script>
<div id="qna_sub_img">
	<img alt="고객센터이미지" src="/images/qna/qnaList_logo.jpg" style="margin:0 auto; display: block;">
</div>
<div class="clear"></div>

<div id="content" style="width:72%;">
	<h2>Q&amp;A</h2>
	<div class="detail_title">
		<h2 class="h2">${qnaVO.title}</h2>
	</div>
	<div id="detail_info1" class="detail_info">
		<div class="detail_box_h2">작성자</div><div class="detail_box">${qnaVO.name}</div>
		<div class="detail_box_h2">아이디</div><div class="detail_box">${qnaVO.id}</div>
		<div class="detail_box_h2">작성일자</div>
		<div class="detail_box">
			<fmt:formatDate value="${ qnaVO.indate}" pattern="yyyy-MM-dd" />
		</div>
	</div>
	<div id="detail_info2" class="detail_info">
		<div class="detail_box_h2">이메일</div><div class="detail_box">${qnaVO.email}</div>
		<div class="detail_box_h2">조회수</div><div class="detail_box">${qnaVO.readcount}</div>
	</div>
	<div class="detail_content" style="border-bottom: 2px solid #00256c;">
	<div class="detail_box_h2">문의사항</div>
		<pre>${ qnaVO.content }</pre>
		<div style="width:300px; text-align: center">
				<c:choose>
					<c:when test="${empty qnaVO.imgfilename}">
						<img src="/upload/noname.jpg" width="250">
					</c:when>
					<c:otherwise>
						<img src="/upload/${qnaVO.imgfilename}" width="250">
					</c:otherwise>
				</c:choose>
			</div>
	</div>
	<div class="detail_reply">
		<h4>답변</h4>
		<pre style="font-size: 130%;">${ qnaVO.reply }</pre>
	</div>
	<button class="content_btn" onclick="location.href='qnaList'">목록으로</button>
	<!-- 본인인 경우만 수정하기 버튼이 나오게 -->
	<c:if test="${loginUser.id==qnaVO.id}">
		<button class="content_btn" onclick="location.href='qnaUpdateForm?qna_num=${qnaVO.qna_num}'">수정하기</button>
		<button class="content_btn" onclick="return deleteQna('${ qnaVO.qna_num }')">삭제하기</button>
	</c:if>
</div>

<div class="clear"></div>
<%@ include file="../include/footer.jsp"%>
