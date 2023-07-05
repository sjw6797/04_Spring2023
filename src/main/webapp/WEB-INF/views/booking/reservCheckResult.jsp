<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
    body {
        font-family: Arial, sans-serif;
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
        width: 60%;
        max-width: 600px;
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
$(function() {
	$('#button').click(
			function() {
				
				var reservNum_dep = $('#reservNum_dep').val();
				var reservNum_return = $('#reservNum_return').val(); 
				
			    console.log(reservNum_dep/reservNum_return );
				$.ajax({
					url: 'updateReserv',
					type: 'POST',
					data: {
						reservNum_dep: reservNum_dep,
						reservNum_return: reservNum_return
						
					},
					success: function(response) {
						// Handle the response from the server
						console.log(response);
						// Redirect to a new page if necessary
						window.location.href = 'newPage.html';
					},
					error: function(xhr, status, error) {
						// Handle errors
						console.log(error);
					}
				});
			});
		});
			


</script>

<article>

	<!-- <form method="post" action="air.do?command=finishReserv" name="PassengerFrm"> -->
	<h3>예약 내역</h3>

	<form>
		<input type="hidden" name="command" value="Reserv">

		<table id="reservCheck" class="reservCheck">
			<c:forEach items="${list1}" var="ReservVO" >
			
				<tr>
					<th>예약날짜  </th>
					<td>${ReservVO.indate}</td>
				</tr>
				
				<tr>
					<th>예약명(승객명)</th>
					<td>${ReservVO.r_name}</td>
				</tr>
				
				<tr>
					<th>이메일</th>
					<td>${ReservVO.r_email}</td>
				</tr>
				
				<tr>
					<th>성별 </th>
					<td>${ReservVO.r_gender}</td>
				</tr>
				
				
				<tr>
					<th>전화번호 </th>
					<td>${ReservVO.r_phone}</td>
				</tr>

				<tr>
					<th>출발일시</th>
					<td>${ReservVO.dep_time}</td>
				</tr>

				<tr>
					<th>출발지</th>
					<td>${ReservVO.departures}</td>
				</tr>



				<tr>
					<th>도착일시</th>
					<td>${ReservVO.return_time}</td>
				</tr>

				<tr>
					<th>도착지</th>
					<td>${ReservVO.arrivals}</td>
				</tr>


				
				<tr>
					<th>이용항공사 </th>
					<td>${ReservVO.airplane_name}</td>
				</tr>
				

				
				<tr>
					<th>좌석등급 </th>
					<td>${ReservVO.r_class}</td>
					
				</tr>
				<tr>
					<th>예약번호  </th>
					<td>${ReservVO.reserv_num}<input type="hidden" id="reservNum_dep" value="${ReservVO.reserv_num}"></td>
					
				</tr>
				
			
				

			</c:forEach>
			<c:forEach items="${list2}" var="ReservVO" >
				<tr>
					<th>출발일시</th>
					<td>${ReservVO.dep_time}</td>
				</tr>

				<tr>
					<th>출발지</th>
					<td>${ReservVO.departures}</td>
				</tr>



				<tr>
					<th>도착일시</th>
					<td>${ReservVO.return_time}</td>
				</tr>

				<tr>
					<th>도착지</th>
					<td>${ReservVO.arrivals}</td>
				</tr>


				
				<tr>
					<th>이용항공사 </th>
					<td>${ReservVO.airplane_name}</td>
				</tr>
				

				
				<tr>
					<th>좌석등급 </th>
					<td>${ReservVO.r_class}</td>
				
				</tr>
				
				<tr>
					<th>예약번호  </th>
					<td>${ReservVO.reserv_num}<input type="hidden" id="reservNum_return" value="${ReservVO.reserv_num}"></td>
					
				</tr>
			
				
			
			
			</c:forEach>


		</table>
		
		
		

		<button id="button">예약변경하기 </button>





	</form>

</article>

