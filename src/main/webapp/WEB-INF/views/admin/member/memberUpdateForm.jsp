<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<head>
<title>Document</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<style>
* {
	margin: 0px;
	padding: 0px;
	text-decoration: none;
	font-family: sans-serif;
}

body {
	background-image: #34495e;
}

.joinForm {
	position: absolute;
	width: 400px;
	height: 400px;
	padding: 30px, 20px;
	background-color: #FFFFFF;
	text-align: center;
	top: 25%;
	left: 50%;
	transform: translate(-50%, -50%);
	border-radius: 15px;
}

.joinForm h2 {
	text-align: center;
	margin: 30px;
}

.textForm {
	border-bottom: 2px solid #adadad;
	margin: 30px;
	padding: 10px 10px;
}

.style {
	width: 100%;
	border: none;
	outline: none;
	color: black;
	font-weight: bold;
	font-size: 16px;
	height: 25px;
	background: none;
	font-size: 16px;
}

input::placeholder {
	color: #636e72;
	font-weight: 400;
}

.btn {
	position: relative;
	left: 40%;
	transform: translateX(-50%);
	margin-bottom: 40px;
	width: 80%;
	height: 40px;
	background: linear-gradient(125deg, #81ecec, #6c5ce7, #81ecec);
	background-position: left;
	background-size: 200%;
	color: white;
	font-weight: bold;
	border: none;
	cursor: pointer;
	transition: 0.4s;
	display: inline;
}

.btn:hover {
	background-position: right;
}

#span {
	display: block;
}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	$(function() {
		$('#submit').click(
				function() {
					if ($('#pwd').val().length < 5
							|| $('#pwd').val().length > 15) {
						alert('비밀번호를를 5글자이상 15글자 이하로 맞춰주세요')
						return false
					} else if ($('#pwd').val() != $('#pwdcheck').val()) {
						alert("비밀번호 확인이 틀렸습니다")
						return false
					} else if ($('#name').val().length < 1) {
						alert("이름을 입력해주세요")
						return false
					} else if ($('#birth').val().length < 1) {
						alert('생일을 선택해주세요')
						return false
					} else if ($('#email').val().length < 1) {
						alert('이메일을 입력해주세요')
						return false
					} else if ($('#phone').val().length < 1) {
						alert('전화번호를 입력해주세요')
						return false
					} else if ($('#sample4_roadAddress').val().length < 1
							|| $('#sample4_postcode').val().length < 1) {
						alert('우편번호 찾기를 해주세요')
						return false
					} else if ($('#sample4_detailAddress').val().length < 1) {
						alert('상세주소를 입력해주세요')
						return false
					} else {
						return true
					}

				})
	})
</script>
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
</script>
</head>
<body>
	<form action="air.do" method="post" class="joinForm" name="frm">
		<input type="hidden" name="command" value="adminUpdateMember">
		<input type="hidden" name="id" value="${ mdto.id }">
		<h2>회원정보 수정</h2>
		<span id="span"></span>
		<div class="textForm" style="margin-top: 0;">
			<input name="pwd" type="password" class="style" placeholder="비밀번호" id="pwd" value="${ mdto.pwd }">
		</div>
		<div class="textForm">
			<input name="pwdcheck" type="password" class="style" placeholder="비밀번호 확인" id="pwdcheck" value="${ mdto.pwd }">
		</div>
		<div class="textForm">
			<input name="name" type="text" class="style" placeholder="이름" id="name" value="${ mdto.name }">
		</div>
		<div class="textForm">
			<input type="text" id="birth" name="birth" class="style" placeholder="생년월일" readonly="readonly" value="${ mdto.birth }">
		</div>
		<div class="textForm">
			<input name="email" type="text" class="style" placeholder="이메일" id="email" value="${ mdto.email }">
		</div>
		<div class="textForm">
			<input name="phone" type="text" class="style" placeholder="전화번호" id="phone" value="${ mdto.phone }">
		</div>
		<div class="textForm">
			<input type="text" id="sample4_postcode" placeholder="우편번호" name="zip_num" class="style" readonly value="${ mdto.zip_num }">
		</div>
		<input type="button" onclick="sample4_execDaumPostcode()" value="우 편 번 호  찾 기" class="btn">
		<div class="textForm" style="margin-top: 0;">
			<input type="text" id="sample4_roadAddress" placeholder="도로명주소" class="style" name="address1" readonly value="${ mdto.address1 }">
		</div>
		<div class="textForm">
			<input type="text" id="sample4_detailAddress" placeholder="상세주소" class="style" name="address2" value="${ mdto.address2 }">
		</div>
		<input type="hidden" id="sample4_jibunAddress" placeholder="지번주소" class="style" disabled="disabled">
		<span id="guide" style="color: #999; display: none"></span>
		<input type="hidden" id="sample4_extraAddress" placeholder="참고항목" class="style" disabled="disabled">
		<div class="textForm">
			<c:if test="${ mdto.gender =='M' }">
				<input name="gender" type="radio" value="M" checked="checked">
			남성 &nbsp;&nbsp;
			<input name="gender" type="radio" value="F">
			여성
			</c:if>
			<c:if test="${ mdto.gender =='F' }">
				<input name="gender" type="radio" value="M">
			남성 &nbsp;&nbsp;
			<input name="gender" type="radio" value="F" checked="checked">
			여성
			</c:if>
		</div>
		<div class="textForm">
			<c:if test="${ mdto.useyn =='Y' }">
				<input name="useyn" type="radio" value="Y" checked="checked">
			사용 &nbsp;&nbsp;
			<input name="useyn" type="radio" value="N">
			탈퇴
			</c:if>
			<c:if test="${ mdto.useyn =='N' }">
				<input name="useyn" type="radio" value="Y">
			사용 &nbsp;&nbsp;
			<input name="useyn" type="radio" value="N" checked="checked">
			탈퇴
			</c:if>
		</div>
		<input type="submit" value="수 정 하 기" class="btn" id="submit">
		<input type="reset" value="취 소" class="btn">
		<input type="button" class="btn" value="목록으로" onClick="history.back()">
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
							document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

							// 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
							if (roadAddr !== '') {
								document.getElementById("sample4_extraAddress").value = extraRoadAddr;
							} else {
								document.getElementById("sample4_extraAddress").value = '';
							}

							var guideTextBox = document.getElementById("guide");
							// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
							if (data.autoRoadAddress) {
								var expRoadAddr = data.autoRoadAddress
										+ extraRoadAddr;
								guideTextBox.innerHTML = '(예상 도로명 주소 : '
										+ expRoadAddr + ')';
								guideTextBox.style.display = 'block';

							} else if (data.autoJibunAddress) {
								var expJibunAddr = data.autoJibunAddress;
								guideTextBox.innerHTML = '(예상 지번 주소 : '
										+ expJibunAddr + ')';
								guideTextBox.style.display = 'block';
							} else {
								guideTextBox.innerHTML = '';
								guideTextBox.style.display = 'none';
							}
						}
					}).open();
		}
	</script>
</body>
</html>