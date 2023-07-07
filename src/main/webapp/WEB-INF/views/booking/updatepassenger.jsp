<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



    <script>
    $(function() {
		$('#updateReserv').click(
				function() {
					
					var name = $('#name').val();					
					var email = $('#email').val();
					var phone = $('#phone').val();					
					var gender = $('#gender').val();				   
				    console.log(name/ sit1/ sit2/ email/ phone/ gender/ passenNum );
					$.ajax({
						url: 'updateReserv',
						type: 'POST',
						data: {
							name: name,						
							email: email,
							phone: phone,
							gender: gender						
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
    
    
<!--  승객정보출력   -->
   
    <table id="reservCheck" class="reservCheck">
			<c:forEach items="${list}" var="ReservVO" >
			
				
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

				
    
    </c:forEach>
    </table>
    
    
   <!--  승객 정보 입력란  --> 
						<div id="info_journey">
									<h3 style="color: #ffffff;">
										<strong>변경할 승객정보</strong>
									</h3>
						
								</div>
								
							<form action="finishReserv" method="post" >
								<div id="reservForm">
									<!-- 2개 -->
									<!--  2nd section -->
											
													<label>&nbsp;이&nbsp;&nbsp;&nbsp;&nbsp;름 &nbsp;:
														&nbsp;&nbsp;</label>
													<input name="name" type="text" id="name">
												
										
											<br> <label>전화번호 &nbsp;: &nbsp;&nbsp; </label>
											<input 	name="phone" type="text" id="phone"><br> 
											<label>이&nbsp;메&nbsp;일&nbsp;&nbsp;:
												&nbsp;&nbsp; </label>
												<input name="email" type="text" id="email">
										<br> <label>성별(F/M)&nbsp;:
												&nbsp;&nbsp; </label><input name="gender" type="text" id="gender"><br>
											
						
						
						
											
										<div id="buttons">
										
											<input type="button" id="updateReserv" value="변경하기" class="submit" />
										</div>
										
								</div>
							</form>
							
							
							