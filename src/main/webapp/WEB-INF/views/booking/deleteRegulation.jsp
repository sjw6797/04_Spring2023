<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%>
<meta charset="UTF-8">

<style>
body {
   font-family: Arial, sans-serif;
   }

article {
   width: 1300px;
   max-width: 1900px;
   margin: 0 auto;
   margin-bottom : 200px;

   
}


h1{
padding:20px;
/* border: 1px solid; */
border-radius : 10px;
color: #333;
padding-bottom : 50px;
font-weight :bold;
padding-top : 80px;
}



div#entire{

margin-bottom : 70px;


}

div#title_change{
background: #808080;
padding:10px;

padding-left : 30px;

border: 2px solid #00256c;
border-radius: 10px 10px 0 0; 
width:30%;
color : white;


}

h2{
font-size : 23px;

}

div#about_change{
padding-top:40px;
font-size: 25px;
border: 1px solid #808080;
padding-bottom : 30px;
margin-bottom : 100px;
}

div#title_cancel{

background: #808080;
padding :10px;
padding-left : 30px;
border: 2px solid #00256c;
border-radius: 10px 10px 0 0; 
width:30%;
color : white;
}



div#about_cancel{
font-size: 25px;
padding-top:40px;
border: 1px solid #808080;
padding-bottom : 70px;
}

fieldset{
font-weight:bold;
font-size:17px;
border: 0;
padding-left : 30px;
}


    
input[type="submit"] {
	/*align-self: flex-end;*/
	background-color: #a0a0a0;
	border: none;
	color: white;
	padding: 15px 30px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 20px 0;
	/*margin-left: 120px;*/
	transition-duration: 0.4s;
	cursor: pointer;
	
	margin-top:20px;
}

input[type="submit"]:hover {
	background-color: #808080 
}

</style>



<article>
<br><br><br><br>
   <h1>항공권변경 및 취소 규정</h1>
   <!-- <form> -->
   <form>
     

<div id="entire">
   <div id="title_change">
      <h2>항공권 변경</h2>
   </div>
   <div id="about_change">
      <fieldset>
         
            - 항공권 예약 후 탑승자 정보와 항공 일정은 변경이 불가하며, 취소 후 재예약하셔야 합니다. <br> <br> - 항공권 변경에 따른 취소는 항공사 취소수수료가 발생하며, 재예약 시 요금이 변경되거나 좌석이 마감될 수 있습니다.  
            <br><br> - 또한, 항공권 취소에 대한 책임은 탑승자 본인에게 있습니다.
         

      </fieldset>
   </div>
   <div id="title_cancel">
      <h2>항공권 취소 방법</h2>
   </div>
   <div id="about_cancel">
      <fieldset>
         1. 출발 전 전체 취소 방법<br> <br>
          &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;· 출발 전 전체취소는 [마이페이지 > 예약조회 > 상세보기 > 전체취소]에서 취소 가능합니다.
         <br> <br>
         &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; ※ 사전좌석배정 or 체크인된 상태에서는 취소가 불가하오니, 항공사 홈페이지에서 체크인 해제 후 마이페이지에서 취소하시기 바랍니다.
          <br> <br> <br>
         2. 출발 후(미탑승 항공권) 전체취소 방법<br> <br> 
        &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; · 출발 후 항공권(미탑승 항공권) 취소는 EZEN AirLine [마이페이지 > 온라인 문의] 를 통해 접수 신청하시기 바랍니다.
         <br><br>
         &nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; ※ 미탑승 항공권은 각 항공사 취소수수료가 부과됩니다. <br>

      </fieldset>
   </div>

      

      <br> 
</div>
   </form>
   <form method="post" action="deleteReserv" >		
                <input type="submit" value="예약삭제하기"  class="submit" />
        </form>
</article>

<%@ include file="../include/footer.jsp"%>
