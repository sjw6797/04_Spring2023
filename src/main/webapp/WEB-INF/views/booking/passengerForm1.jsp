<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<meta charset="UTF-8">
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f3f7f9;
}

article {
	width: 1300px;
	max-width: 1900px;
	margin: 0 auto;
}

h2 {
	color: #5d5f61;
	text-align: center;
	padding: 20px 0;
}

h3 {
	color: #5d5f61;
	text-align: center;
	padding: 20px 0;
	font-size: 30px;
}

h4 {
	color: #5d5f61;
	text-align: center;
	padding: 20px 0;
	font-size: 17px;
}

/*   노선 출발 -> 도착 / 출발일시 -> 도착 일시 */
div#route_scheule {
	display: flex;
	flex-wrap: wrap;
	width: 95%;
	padding-bottom: 0px; /* get a space out of boxes*/
	padding-top: 0;
	padding-right: 50px;
	background: #ffffff;
	margin-top:0;
	
}


div#route_scheule span {
	display: flex;
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.05);

	/* get a space out of boxes*/
}

#timeInfo {
	text-align: center;
	width: 1000px; /*최대길이*/
	padding-left: 20px;
	padding-right: 0px;
	padding-botton: 0px;
	padding-top:0px;
	margin-top:0;
	border-radius: 0 10px 0 0;
	display: flex;
	align-items: center;
	
	

}

.timeInfo-wrapper {
	display: flex;
	align-items: center;
	justify-content: center;
	width:100%;
	
	

}

.timeInfo-wrapper img {
	margin-left: 10px;
}

span#loaction {
	text-align: center;
	width: 300px;
	padding-left: 20px;
	border-radius: 10px 0 0 0;
	padding-top: 5px;
}

#info_journey {
	background: #00256c;
	width: 260px;
	border-radius: 10px 10px 0 0;
	padding-bottom: 0;
	margin-bottom : 0;
	
}

/* 승객 입력 폼*/

div#reservForm { 
	/*display: flex;*/
	background: #ffffff;
	
	margin-bottom: 0px;
	margin-top: 20px;
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.05);
	width: 100%;
	border: 1px solid #00256c;
}


/* 가격정보 */
table {
	border-collapse: separate;
	border-spacing: 30px;
	width: 100%;
}

.price {
	padding: 10px;
	background-color: #ffffff;
	border-radius: 10px;
	border: 2px solid #99ccff;
	transition-duration: 0.4s;
	cursor: pointer;
	width: 200px;
}

.price:hover {
	background-color: #99ccff;
	color: #ffffff;
	cursor: pointer;
}


#inputForm {
	display: flex;
	flex-direction: column;
	margin: 10px; 
	
	flex-grow: 1;
	width: 60%;
	padding-left: 200px;
	max-width: 500px;
	height: 600px;
}



#inputForm label { /* 승객폼 입력 란*/
	width: 100px;
}



#buttons {
	order: 3;
}

input[type="button"] {
	align-self: flex-end;
	background-color: #0079c1;
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
}

input[type="button"]:hover {
	background-color: #005a8e;
}

input[type="text"] {
	width: 30%;
	padding: 10px;
	margin: 10px 0;
	border: 1px solid #00256c;
	border-radius: 4px;
	
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>



<script>
window.onload = function() {
    var totalAmount = 0;
    var priceElements = document.querySelectorAll('.price');

    for (let i = 0; i < priceElements.length; i++) {
        priceElements[i].addEventListener('click', function() {
            var hiddenInputValue1 = this.querySelector('input[name="sit1_1"]');
            var hiddenInputValue2 = this.querySelector('input[name="sit2_2"]');
            var price = 0;
            
            if(hiddenInputValue1){
                price = parseFloat(hiddenInputValue1.value);
                console.log('Hidden input value1:', hiddenInputValue1.value);
                document.getElementById('sit_1').value = this.querySelector('input[name="sit1"]').value;
                console.log('가는날 좌석 :', this.querySelector('input[name="sit1"]').value);
            }
            if(hiddenInputValue2){
                price = parseFloat(hiddenInputValue2.value);
                console.log('Hidden input value2:', hiddenInputValue2.value);
                document.getElementById('sit_2').value = this.querySelector('input[name="sit2"]').value;
                console.log('오는날 좌석 :', this.querySelector('input[name="sit2"]').value);
            }
            
            
            if (price === 0 || isNaN(price)) {
                alert('다른 좌석을 선택하세요.');
                return; // 함수 실행 중지
            }
            
            totalAmount += price;
            document.getElementById('finalprice').value = totalAmount;
        });
    }
}

</script>
<script>
	$(function() {
		$('#nextButton').click(
				function() {
					
					var name = $('#name').val();
					var sit1 = $('#sit_1').val(); // 가는날 좌석 
					var sit2 = $('#sit_2').val(); // 오는날 좌석
					var email = $('#email').val();
					var phone = $('#phone').val();					
					var gender = $('#gender').val();
				    var passenNum = $('#passenNum').val();
				    console.log(name/ sit1/ sit2/ email/ phone/ gender/ passenNum );
					$.ajax({
						url: 'insertReservation',
						type: 'POST',
						data: {
							name: name,
							sit1: sit1,
							sit2: sit2,
							email: email,
							phone: phone,
							gender: gender,
							passenNum: passenNum
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



  <div id="info_journey">
			<h3 style="color: #ffffff;">
				<strong>여정정보</strong>
			</h3>

  </div>
		
		
  <div id="reservForm">
		
		<div id="route_scheule">
			
			<!-- first section -->
		    	
<!--  
				<span id="loaction">
					<h3>${depAirportNm}</h3> &nbsp;&nbsp;&nbsp; <img
					src="image/blueairplan.png" width="50"> &nbsp;&nbsp;&nbsp;
					<h3>${arrAirportNm}</h3>  &nbsp;&nbsp;
					&nbsp;&nbsp;
					<h3>||</h3>
				</span>
-->
		
			


				<div id="timeInfo"> <!-- 가는날 --> <!-- 터미널 정보 --> <!-- 항공편 번호 -->

					&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="timeInfo-wrapper">
						<h4>
							<strong>${depAirportNm1}</strong>
						</h4>
						&nbsp;&nbsp;&nbsp;
						<h3>~</h3>
						&nbsp;&nbsp;&nbsp;
						<h4>
							<strong>${arrAirportNm1}</strong>
						</h4>
						&nbsp;&nbsp;
						<h3>||</h3>
						&nbsp; &nbsp;

						<h4>
							<strong>${depPlandTime1}</strong>
						</h4>
						&nbsp; &nbsp;
						<h3>||</h3>
						
						
						<h4>
							<strong>${arrPlandTime1}</strong>
						</h4>
						&nbsp; &nbsp;
						<h3>||</h3>
						
						<h4>
							<strong>${airlineNm1}</strong>
						</h4>
					
					
						&nbsp; &nbsp;
						<h3>||</h3>
						&nbsp; <img src="images/humen.jpeg" width="50" height="40">
						&nbsp;
						<h4>
							<strong> 승객${passenNum}&nbsp;명</strong>
						</h4>
						&nbsp; &nbsp;
						
						<h4 class="price">
						<c:choose>
								  <c:when test="${economyCharge1 ne null and economyCharge1 ne 0 }">
								    <td>								      								      
								       <strong>이코노미석 : <fmt:formatNumber value="${economyCharge1}" pattern="₩ #,###" type="currency" currencySymbol="₩" /></strong>
									   <input type="hidden" name="sit1"  value="이코노미석" >
									   <input type="hidden" name="sit1_1"  value="${economyCharge1}" >
								    </td>
								  </c:when>
								  
								  <c:otherwise>
								    <td>
								      <span style="color: red;">매진</span>
								      <input type="hidden" name="sit1"  value="매진" >
									  <input type="hidden" name="sit1_1"  value="${economyCharge1}" >
								    </td>
								  </c:otherwise>
							</c:choose>
						</h4>
						
		
						
						&nbsp; &nbsp;
						<h4 class="price">
												
							<c:choose>
								  <c:when test="${prestigeCharge1 ne null and prestigeCharge1 ne 0 }">
								    <td>								      								      
								       <strong>프레스티지석 : <fmt:formatNumber value="${prestigeCharge1}" pattern="₩ #,###" type="currency" currencySymbol="₩" /></strong>
									   <input type="hidden" name="sit1"  value="프레스티지석" >
									   <input type="hidden" name="sit1_1"  value="${prestigeCharge1}" >
								    </td>
								  </c:when>
								  
								  <c:otherwise>
								    <td>
								      <span style="color: red;">매진</span>
								      <input type="hidden" name="sit1"  value="매진" >
									  <input type="hidden" name="sit1_1"  value="${prestigeCharge1}" >
								    </td>
								  </c:otherwise>
							</c:choose>
						</h4>
					</div>
				</div>
				
				

	<br> <br>
	<form action="finishReserv" method="post" >
		

 
		<div id="info_journey">
			<h3 style="color: #ffffff;">
				<strong>승객정보</strong>
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
	
		
	</form>
</article>