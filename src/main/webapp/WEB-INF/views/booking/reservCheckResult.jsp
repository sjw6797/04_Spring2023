<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp" %>

<style>

	article {
		margin-bottom: 200px;
	}

    h3 {
        position: relative;
        height: 60px;
        color: #333;
        text-align: center;
        margin-bottom: 20px;
        margin-top:90px;
         font-weight: bold;
    }

    .reservCheck {
        margin: 2em auto;
        width: 80%;
        border-collapse: collapse;
        box-shadow: 0 0 20px rgba(0,0,0,0.15);
    }

    .reservCheck th,
    .reservCheck td {
        padding: 12px 15px;
        border: 1px solid #ddd;
        font-size:15px;
    }

    .reservCheck th {
        background-color: #E2E2E2;
        border-bottom:5px solid #E2E2E2;
        color: white;
        text-transform: uppercase;
        font-weight: bold;
        border-bottom: 1px solid white;
    }

    .reservCheck td {
        text-align: center;
    }

    #button {
    align-self: flex-end;
    background-color: #696969;
    border: none;
    color: white;
    padding: 15px 30px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 20px 0;
    transition-duration: 0.4s;
    cursor: pointer;
    margin-bottom: 100px;
    margin-top: 70px;
    }

    
   
</style>

<meta charset="UTF-8">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
/*
$(document).ready(function(){
  $("#delete").click(function(event){
    event.preventDefault();
    var reservNum_return = $('#reservNum_return').val();
    var reservNum_dep = $('#reservNum_dep').val();
    $.post("deleteReserv", 
      {
        reservNum_return: reservNum_return,
        reservNum_dep: reservNum_dep
      },
      function(data, status){
       // alert("Data: " + data + "\nStatus: " + status);
      }
    );
  });
});

*/
</script>
<script>
/*
	$(document).ready(function(){
		$("#regulation").click(function(event){
			event.preventDefault();
		    var reservNum_return = $('#reservNum_return').val();
		    var reservNum_dep = $('#reservNum_dep').val();
			
			
			$.ajax({
				url: "regulation",
				type: "POST",
				
				data: {
					reservNum_return: reservNum_return,
			        reservNum_dep: reservNum_dep
				},
				success: function (response) {
					
					
	                console.log("response" + response);
					
					window.location.href = 'regulationPage';
					
					
			       
			       
				},
	            error: function(xhr, status, error) {
	                console.log(error); // 에러 핸들링
	            }
			});
		});
	});
	
	*/
</script>
<article>
	<h3>예약내역</h3>    
		<table id="reservCheck" class="reservCheck">
			<tr>
				<th>비고</th><th>예약번호</th><th>예약날짜</th><th>예약명(승객명)</th><th>이메일</th><th>성별</th><th>전화번호</th>
				<th> 출발시간</th><th>도착시간</th><th>출발지</th><th>도착지</th><th>항공사명</th><th>좌석등급</th>
			</tr>
			<c:forEach items="${list1}" var="ReservVO" >
				<form method="post" action="regulation">
					<!-- 여기 수정부분 -->
					<c:set var="now" value="<%=new java.util.Date()%>" />
					<c:if test="${now lt ReservVO.dep_timeDate}">
						<tr>
							<td>
								<input type="submit" value="예약취소" class="submit"  id="regulation"/>
							</td>
							<td>${ReservVO.reserv_num}<input type="hidden" name ="reservNum_dep" id="reservNum_dep" value="${ReservVO.reserv_num}"></td>
							<td><fmt:formatDate value="${ReservVO.indate}" pattern="yyyy-MM-dd" /></td>
							<td>${ReservVO.r_name}</td>
							<td>${ReservVO.r_email}</td>
							<td>
								<c:if test="${ReservVO.r_gender=='M'}">
									남자
								</c:if>
								<c:if test="${ReservVO.r_gender=='F'}">
									여자
								</c:if>
							</td>
							<td>${ReservVO.r_phone}</td>
							<td><fmt:formatDate value="${ReservVO.dep_timeDate}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td><fmt:formatDate value="${ReservVO.return_timeDate}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td>${ReservVO.departures}</td>
							<td>${ReservVO.arrivals}</td>
							<td>${ReservVO.airplane_name}</td>
							<td>${ReservVO.r_class}</td>
						</tr>
					</c:if>
				</form>
			</c:forEach>
		</table>
</article>

<%@ include file="../include/footer.jsp" %>