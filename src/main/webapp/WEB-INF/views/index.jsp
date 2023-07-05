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
            left : num * -document.getElementById('content_img').clientWidth
         }, 1000);
         num++;
         if (num == Number('${size}'))
            num = 0;
      }, 2000);
   });
</script>
<link type="text/css" rel="stylesheet" href="/css/main.css">
<form>
${countryList}
   <div id="maincontent">
      <div id="content" style="margin: 0 auto;">
         <div id="selectroute">
            <h1 style="color: white; margin-left: 50px; margin-top: 0;">즐겨 찾는 여행 사이트를 빠르고 쉽게 검색하세요</h1>
            <div id="inputroute">
               <div class="inputcontent" style="width: 15%;">
                  <label>출발지</label>
                  <!-- 검색어 입력창   -->
				  <input type="text" id="search" list="searchOptions" />
				  <datalist id="searchOptions">
				  	<c:forEach items="${countryList}" var="country">
				  			<option value="${country}">
				  	</c:forEach>
				  </datalist>
<!--                   <select class="content-input" style="width: 100%;">
                     <option>1</option>
                     <option>2</option>
                     <option>3</option>
                     <option>4</option>
                  </select> -->
               </div>
               <div class="inputcontent" style="width: 15%;">
                  <label>도착지</label>
                  <select class="content-input" style="width: 100%;">
                     <option>1</option>
                     <option>2</option>
                     <option>3</option>
                     <option>4</option>
                  </select>
               </div>
               <div class="inputcontent" style="width: 20%;">
                  <label>출발시간</label>
                  <input type="text" id="datepicker" class="content-input" style="width: 100%;">
               </div>
            <!--    <div class="inputcontent">
                  <label>도작시간</label>
                  <input type="text" id="datepicker2" class="content-input" placeholder="탑승일을 선택하세요">
               </div> -->
               <div class="inputcontent" style="width: 10%;">
                  <label>탑승인원</label>
                  <input type="number" class="content-input" min="1" max="10" value="1" style="width: 100%;">
               </div>
               <div class="inputcontent" style="text-align: center; background-color: #0062e3; color: white; cursor: pointer; width: 15%;" onclick="">
                  <h2>검색</h2>
               </div>
            </div>
         </div>
      </div>
   </div>
</form>
<div id="content" id="content_img" >
   <div class="rollbanner">
      <div class="mainbanner2">
         <c:forEach items="${bannerList}" var="dto">
            <img src="/images/${dto.image}" style="float:left;" height="500" width="20%">
         </c:forEach>
      </div>
   </div>
</div>

<!-- </div> -->

<%@ include file="include/footer.jsp"%>