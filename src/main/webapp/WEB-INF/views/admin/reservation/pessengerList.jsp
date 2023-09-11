<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
	$(function() {
	
		$('#delete_passen').click(function() {
			var checkedValues = [];
			
			// Find all checkboxes that have a class of 'checked' and are checked
			$('input[type="checkbox"]:checked').each(function() {
				// Add each checked checkbox's value to the checkedValues array
				checkedValues.push($(this).val());
			});
			
			if (checkedValues.length === 0) {
				alert('삭제할 승객을 선택하세요.');
			} else {
				// Convert the array to a string, separating each item with a comma
				var checkedValuesString = checkedValues.join(',');
				
				// Confirm delete
				if (confirm('선택한 승객을 삭제하시겠습니까?')) {
					// If confirmed, send a POST request to the server with the checkedValues
					$.ajax({
						url: 'deletePassenger',
						type: 'POST',
						data: {
							'reserv_nums': checkedValuesString 
							},
						success: function(response) {
							// You may need to handle the server's response here
							alert("성공!")
							location.reload();
						},
						error: function(err) {
							// Handle any errors here
							alert("실패ㅜㅜ")
							console.error(err);
						}
					});
				}
			}
		})
	})
</script>
<style>
#wrap {
	width: 80%;
}
th tr {
	font-size: 90%;
}
</style>
<article>
	<h1>승객리스트</h1>
	<form action="searchPassenAdmin">
		<div class="input_div">
			<input type="text" id="name1" name="key" class="input_text" placeholder="이름 및 전화번호를 입력하세요" value="${key}">	
			<input type="submit" class="input_button" id="search_passen" value="  찾기  ">
			<input type="button" class="input_button" id="delete_passen" value="삭제하기" style="position: absolute; background: #00256c;">
			<br>		
		</div>
	</form>
	
	
	<table class="adminTable">
	    <tr>
	    	<th>비고</th>
		    <th>예약번호</th>
			<th>이름</th>
			<th>번호</th>		
			<th>이메일</th>
			<th>성별</th>
			<th>출발지</th>
			<th>출발일시</th>
			<th>도착지</th>
			<th>도착일시</th>
			<th>이용항공사</th>	
			<th>예약날짜</th>	
			<th>좌석등급</th>
		</tr>
		<c:forEach items="${passenlist}" var="ReservVO" varStatus="status">
			<tr>   
		  		<td><input type="checkbox" id="checked${status.index}"  value="${ReservVO.reserv_num}"></td>
		  		<td>${ReservVO.reserv_num}<input type="hidden" id="reserv_num${status.index}"></td>
			    <td>${ReservVO.r_name}<input type="hidden" id="name${status.index}" value="${ReservVO.r_name}"></td>
			    <td>${ReservVO.r_phone}<input type="hidden" id="phone${status.index}" value="${ReservVO.r_phone}"></td>
			    <td>${ReservVO.r_email}<input type="hidden" id="email${status.index}"> </td>
			    <td>${ReservVO.r_gender}<input type="hidden" id="gender${status.index}"></td>
			    <td>${ReservVO.departures}<input type="hidden" id="departures${status.index}"></td>		    
				<td>${ReservVO.dep_time}<input type="hidden" id="dep_time${status.index}"></td>
			    <td>${ReservVO.arrivals}<input type="hidden" id="arrivals${status.index}"></td>
				<td>${ReservVO.return_time}<input type="hidden" id="return_time${status.index}"></td>
				<td>${ReservVO.airplane_name}<input type="hidden" id="airplane_name${status.index}"></td>
				<td><fmt:formatDate value="${ReservVO.indate}" pattern="yy-MM-dd" /></td>		
				<td>${ReservVO.r_class}<input type="hidden" id="r_class${status.index}"></td>				
			</tr>	
		</c:forEach>
	</table>
	<br>
</article>
<jsp:include page="../include/paging.jsp">
	<jsp:param name="command" value="searchPassenAdmin" />
</jsp:include>
<%@ include file="../include/footer.jsp"%>