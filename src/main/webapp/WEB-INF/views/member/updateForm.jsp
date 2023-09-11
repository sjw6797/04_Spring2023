<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<head>
<link rel="stylesheet" href="/css/join.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script>
	$.datepicker.setDefaults({
		dateFormat : 'yy-mm-dd',
		prevText : '이전 달',
		nextText : '다음 달',
		monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월',
				'10월', '11월', '12월' ],
		monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월',
				'9월', '10월', '11월', '12월' ],
		dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
		dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
		dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
		showMonthAfterYear : true,
		yearSuffix : '년',
		changeYear : true,
		changeMonth : true,
		maxDate : 0,
		minDate : '-100y',
		yearRange : 'c-100:c+100'
	});
	$(function() {
		$("#birth").datepicker({});
	});
</script>
</head>
<body>
	<form action="memberUpdate" method="post" class="joinForm" name="frm">
	<input type="hidden" name="id" value="${loginUser.id}">
	<input type="hidden" name="provider" value="${loginUser.provider}">
		<h2>회원정보수정</h2>
		<c:if test="${empty loginUser.provider}">
			<div class="textForm" style="margin-top: 0;">
				<input name="pwd" type="password" class="style" placeholder="비밀번호" id="pwd">
			</div>
			<div class="textForm">
				<input name="pwdcheck" type="password" class="style" placeholder="비밀번호 확인" id="pwdcheck">
			</div>
		</c:if>
		<div class="textForm">
			<input name="name" type="text" class="style" placeholder="이름" id="name" value="${loginUser.name}">
		</div>
		<div class="textForm">
			<input type="text" id="birth" name="birth" class="style" placeholder="생년월일" readonly="readonly" value="${loginUser.birth}">
		</div>
		<c:choose>
			<c:when test="${not empty loginUser.provider}">
				<div class="textForm">
					<input name="email" type="text" class="style" placeholder="이메일" id="email" value="${loginUser.email}" readonly>
				</div>
			</c:when>
			<c:otherwise>
				<div class="textForm">
					<input name="email" type="text" class="style" placeholder="이메일" id="email" value="${loginUser.email}">
				</div>
			</c:otherwise>
		</c:choose>
		<div class="textForm">
			<input name="phone" type="text" class="style" placeholder="전화번호" id="phone" value="${loginUser.phone}">
		</div>
		<div class="textForm">
			<input type="text" id="sample4_postcode" placeholder="우편번호" name="zip_num" class="style" readonly value="${loginUser.zip_num}">
		</div>
		<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" class="btn">
		<div class="textForm" style="margin-top: 0;">
			<input type="text" id="sample4_roadAddress" placeholder="도로명주소" class="style" name="address1" readonly value="${loginUser.address1}">
		</div>
		<div class="textForm">
			<input type="text" id="sample4_detailAddress" placeholder="상세주소" class="style" name="address2" value="${loginUser.address2}">
		</div>
		<div class="textForm">
		<c:choose>
			<c:when test="${loginUser.gender == 'F'}">
				<input name="gender" type="radio" value="M">
				남성 &nbsp;&nbsp;
				<input name="gender" type="radio" value="F" checked="checked">
				여성
			</c:when>
			<c:otherwise>
				<input name="gender" type="radio" value="M" checked="checked">
				남성 &nbsp;&nbsp;
				<input name="gender" type="radio" value="F">
				여성
			</c:otherwise>
		</c:choose>
		</div>
		<p style="color: red;">${message}</p>
		<input type="submit" value="수정하기" class="btn" id="submit">
		<input type="reset" value="취소" class="btn">
	</form>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
		function sample4_execDaumPostcode() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

							// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
							// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
							var roadAddr = data.roadAddress; // 도로명 주소 변수
							var extraRoadAddr = ''; // 참고 항목 변수

							// 법정동명이 있을 경우 추가한다. (법정리는 제외)
							// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
							if (data.bname !== ''
									&& /[동|로|가]$/g.test(data.bname)) {
								extraRoadAddr += data.bname;
							}
							// 건물명이 있고, 공동주택일 경우 추가한다.
							if (data.buildingName !== ''
									&& data.apartment === 'Y') {
								extraRoadAddr += (extraRoadAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
							if (extraRoadAddr !== '') {
								extraRoadAddr = ' (' + extraRoadAddr + ')';
							}

							// 우편번호와 주소 정보를 해당 필드에 넣는다.
							document.getElementById('sample4_postcode').value = data.zonecode;
							document.getElementById("sample4_roadAddress").value = roadAddr;
						}
					}).open();
		}
	</script>
</body>
</html>