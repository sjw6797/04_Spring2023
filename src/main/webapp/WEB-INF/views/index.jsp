<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$(function() {
	//input을 datepicker로 선언
	$("#datepicker").datepicker({
		dateFormat : 'yy-mm-dd' //달력 날짜 형태
		,
		showOtherMonths : false //빈 공간에 현재월의 앞뒤월의 날짜를 표시
		,
		showMonthAfterYear : true // 월- 년 순서가아닌 년도 - 월 순서
		,
		changeYear : true //option값 년 선택 가능
		,
		changeMonth : true //option값  월 선택 가능                
		,
		buttonText : "선택" //버튼 호버 텍스트              
		,
		yearSuffix : "년" //달력의 년도 부분 뒤 텍스트
		,
		monthNamesShort : [ '1월', '2월', '3월', '4월', '5월',
				'6월', '7월', '8월', '9월', '10월', '11월', '12월' ] //달력의 월 부분 텍스트
		,
		monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월',
				'7월', '8월', '9월', '10월', '11월', '12월' ] //달력의 월 부분 Tooltip
		,
		dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ] //달력의 요일 텍스트
		,
		dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일',
				'금요일', '토요일' ] //달력의 요일 Tooltip
		,
		minDate : "0D" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
		,
		maxDate : "+10M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)  
	});

	$('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)            
});
$(function() {
	//input을 datepicker로 선언
	$("#datepicker2").datepicker({
		dateFormat : 'yy-mm-dd' //달력 날짜 형태
		,
		showOtherMonths : false //빈 공간에 현재월의 앞뒤월의 날짜를 표시
		,
		showMonthAfterYear : true // 월- 년 순서가아닌 년도 - 월 순서
		,
		changeYear : true //option값 년 선택 가능
		,
		changeMonth : true //option값  월 선택 가능                
		,
		buttonText : "선택" //버튼 호버 텍스트              
		,
		yearSuffix : "년" //달력의 년도 부분 뒤 텍스트
		,
		monthNamesShort : [ '1월', '2월', '3월', '4월', '5월',
				'6월', '7월', '8월', '9월', '10월', '11월', '12월' ] //달력의 월 부분 텍스트
		,
		monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월',
				'7월', '8월', '9월', '10월', '11월', '12월' ] //달력의 월 부분 Tooltip
		,
		dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ] //달력의 요일 텍스트
		,
		dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일',
				'금요일', '토요일' ] //달력의 요일 Tooltip
		,
		minDate : "0D" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
		,
		maxDate : "+10M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)  
	});

	$('#datepicker2').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)            
});

	$(function() {
		var num = 0;
		setInterval(function() {
			$('.mainbanner2').animate({
				left : num * -document.body.clientWidth 
			}, 1000);
			num++;
			if (num == Number('${size}'))
				num = 0;
		}, 2000);
	});
</script>
<link type="text/css" rel="stylesheet" href="/css/main.css">
<link type="text/css" rel="stylesheet" href="/css/m_main.css">
<form>
	<div id="maincontent">
		<div id="content" style="margin: 0 auto;">
			<div id="selectroute">
				<div id="h1">
					<h1 style="color: white; margin-top: 0;">즐겨 찾는 여행 사이트를 빠르고 쉽게 검색하세요</h1>
				</div>
				<div id="inputroute">
					<div class="inputcontent">
						<label>출발지</label>
						<input type="text" name="depAirportNm" id="search" list="searchOptions" class="content-input" style="width: 100%;"/>
			            <datalist id="searchOptions" class="content-input" style="width: 100%;">
			            	<c:forEach items="${countryList}" var="country">
			            		<option value="${country}">
			                </c:forEach>
			            </datalist>
					</div>
					
					<div class="inputcontent">
						<label>도착지</label>
						<input type="text" name="arrAirportNm" id="search" list="searchOptions" class="content-input" style="width: 100%;"/>
			            <datalist id="searchOptions" class="content-input" style="width: 100%;">
			            	<c:forEach items="${countryList}" var="country">
			            		<option value="${country}">
			                </c:forEach>
			            </datalist>
					</div>
					<div class="inputcontent">
						<label>가는날짜</label>
						<input type="text" name="depPlandTime" id="datepicker" class="content-input" style="width: 100%;" readonly>
					</div>
					<div class="inputcontent" id="comback">
						<label>오늘날짜</label>
						<input type="text" name="arrPlandTime" id="datepicker2" class="content-input" style="width: 100%;" readonly>
					</div>
					<div class="inputcontent">
						<label>탑승인원</label>
						<input type="number" name="passenNum" class="content-input" min="1" max="10" value="1" style="width: 100%;">
					</div>
					<div class="inputcontent" style="text-align: center; background-color: #0062e3; color: white; cursor: pointer; line-height: 60px; font-weight: bold; font-size: 140%;" onclick="">검색하기</div>
				</div>
				<div id="trip">
					<input type="radio" name="flag" value="2" checked="checked" onclick="roundtrip()">왕복
					<input type="radio" name="flag" value="1" onclick="oneway()">편도
				</div>
			</div>
		</div>
	</div>
</form>
<div id="content_img">
	<div class="rollbanner">
		<div class="mainbanner2">
			<c:forEach items="${bannerList}" var="dto">
				<img src="/upload/${dto.image}">
			</c:forEach>
		</div>
	</div>
</div>

<div id="content">
	<h1 style="display: block;">인기여행지역</h1>
	<div class="bestimg">
		<div class="bestcontent">
			<div class="detailimg">
				<img src="https://t1.daumcdn.net/cfile/tistory/2314D84754D09D2F3F">
			</div>
			<div class="detailtext">
				<h3>제주도 인기여행지!!~~</h3>
				<h4>가격 : 3,000,000원</h4>
			</div>
		</div>
		<div class="bestcontent">
			<div class="detailimg">
				<img src="images/어드민로고.png">
			</div>
			<div class="detailtext">
				<h3>제주도 인기여행지!!~~</h3>
				<h4>가격 : 3,000,000원</h4>
			</div>
		</div>
		<div class="bestcontent">
			<div class="detailimg">
				<img src="images/카카오로그인.png">
			</div>
			<div class="detailtext">
				<h3>제주도 인기여행지!!~~</h3>
				<h4>가격 : 3,000,000원</h4>
			</div>
		</div>
		<div class="bestcontent">
			<div class="detailimg">
				<img src="images/카카오로그인큰버전.png">
			</div>
			<div class="detailtext">
				<h3>제주도 인기여행지!!~~</h3>
				<h4>가격 : 3,000,000원</h4>
			</div>
		</div>
	</div>
</div>

<div id="content">
	<h1 style="display: block;">인기여행지역</h1>
	<div class="bestimg">
		<div class="bestcontent">
			<div class="detailimg">
				<img src="https://t1.daumcdn.net/cfile/tistory/2314D84754D09D2F3F">
			</div>
			<div class="detailtext">
				<h3>제주도 인기여행지!!~~</h3>
				<h4>가격 : 3,000,000원</h4>
			</div>
		</div>
		<div class="bestcontent">
			<div class="detailimg">
				<img src="images/어드민로고.png">
			</div>
			<div class="detailtext">
				<h3>제주도 인기여행지!!~~</h3>
				<h4>가격 : 3,000,000원</h4>
			</div>
		</div>
		<div class="bestcontent">
			<div class="detailimg">
				<img src="images/카카오로그인.png">
			</div>
			<div class="detailtext">
				<h3>제주도 인기여행지!!~~</h3>
				<h4>가격 : 3,000,000원</h4>
			</div>
		</div>
		<div class="bestcontent">
			<div class="detailimg">
				<img src="images/카카오로그인큰버전.png">
			</div>
			<div class="detailtext">
				<h3>제주도 인기여행지!!~~</h3>
				<h4>가격 : 3,000,000원</h4>
			</div>
		</div>
	</div>
</div>


<%@ include file="include/footer.jsp"%>