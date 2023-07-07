<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
    
    
    
    
    
<div id="info_journey">
			<h3 style="color: #ffffff;">
				<strong>변경할 승객정보</strong>
			</h3>

		</div>
		
		
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
					<label>최종결제금액 &nbsp;: &nbsp;&nbsp;</label> <input 
						name="finalprice" id="finalprice"><br>
				



					<input type="hidden" id="sit_1" name="sit_1" ><!-- 선택한 가는날 좌석 -->
					<input type="hidden" id="sit_2" name="sit_2" ><!-- 선택한 오는날 좌석 -->
					
					
				<div id="buttons">
					<!-- <input type="submit" id="submitButton" value="예약하기" class="submit" /> -->
					<input type="button" id="nextButton" value="다음으로" class="submit" />
				</div>
				
		</div>