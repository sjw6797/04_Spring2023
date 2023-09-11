<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%>


<meta charset="UTF-8">
<style>
body {
	font-family: Arial, sans-serif;
	background-color:#ffffff;
}

article {
	width: 70%;
	/* max-width: 2100px; */
	margin: 0 auto;
	height : 1500px;
	padding-top:200px;
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
	margin-bottom : 0;
}

h4 {
	color: #5d5f61;
	text-align: center;
	
	font-size: 22px;
}

/*   노선 출발 -> 도착 / 출발일시 -> 도착 일시 */


div#reservForm1 { 
	/*display: flex;*/
	background: #ffffff;
	height : 300px;
	margin-bottom: 0px;
	margin-top: 0px;
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.05);
	width: 100%;
	border: 1px solid #00256c;
	border-radius: 0 20px 20px 20px;
	padding-top : 0px;
	
	overflow: hidden;
	
}



div#route_scheule {
	display: flex;
	flex-wrap: wrap;
	/* width: 1800px; */
	width:98%;
	padding-bottom: 0px; /* get a space out of boxes*/
	
	padding-right: 20px;
	margin-right: 20px;
	background: #ffffff;
	
	
}


div#route_scheule span {
	display: flex;
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.05);
	

	/* get a space out of boxes*/
}

#timeInfo {
	text-align: center;
	width: 1780px; /*최대길이*/
	magin-left: 20px;
	padding-right: 0px;
	padding-botton: 0px;
	padding-top:10px;
	margin-top:0;
	border-radius: 0 20px 0 0;
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



#info_journey {
	background: #00256c;
	width: 260px;
	border-radius: 10px 10px 0 0;
	padding-bottom: 0;
	margin-bottom : 0;
}


/* 승객 입력 폼*/

#form-wrapper {
  display: flex;
  justify-content: space-between;
  width: 100%;
  /*
  border: 2px solid ;*/
}

div#passenForm{
height : 470px;
width: 75%;
margin-top: 20px;
margin-bottom: 0px;
/*border: 1px solid ;*/
/* padding-left : 120px; */
margin-left:0px;

}

div#reservForm { 
	/*display: flex;*/
	background: #ffffff;
	height : 450px;
	margin-bottom: 0px;
	margin-top: 0px;
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.05);
	width: 58%;
	border: 1px solid #00256c;
	border-radius: 0 20px 20px 20px;
}


/* 가격정보 */

#info_price {
	display: flex;
	flex-direction: column;
	justify-content: flex-start;
	align-items: stretch;
	
	margin-left: 50px; 
	width: 50%; 

.jb-division-line { /*가격 구분선 */
  border-top: 1px solid #E2E2E2;
  width: 100%;        
}


}

.info_price_row {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 10px;
}

.info_price_row label {
	align-self: flex-start;
}

.info_price_row input {
	align-self: flex-end;
	/*0711*/
	width : 100px;
	border:0 solid black;
	font-size:20px;
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

label{
font-size: 22px;
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
	
	margin-top:20px;
}

input[type="button"]:hover {
	background-color: #005a8e;
}

input[type="text"] {
	width:60%;
	padding: 10px;
	margin: 10px 0;
	border: 1px solid #00256c;
	border-radius: 4px;
	height : 30px;
	margin-top:20px;
	
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
window.onload = function() {
    var totalAmount = 0;
    var priceElements = document.querySelectorAll('.price');
    var selectedElement1 = null;
    var selectedElement2 = null;
    var selectedPrice1 = 0;
    var selectedPrice2 = 0;

    for (let i = 0; i < priceElements.length; i++) {
        priceElements[i].addEventListener('click', function() {
            var hiddenInputValue1 = this.querySelector('input[name="sit1_1"]');
            var hiddenInputValue2 = this.querySelector('input[name="sit2_2"]');
            var price = 0;

            if(hiddenInputValue1){
                price = parseFloat(hiddenInputValue1.value);
                document.getElementById('sit_1').value = this.querySelector('input[name="sit1"]').value;
                if (selectedElement1) {
                    selectedElement1.style.backgroundColor = ""; 
                    selectedElement1.style.color = "";
                    totalAmount -= selectedPrice1;
                }
                selectedElement1 = this;
                selectedPrice1 = price;
            }
            if(hiddenInputValue2){
                price = parseFloat(hiddenInputValue2.value);
                document.getElementById('sit_2').value = this.querySelector('input[name="sit2"]').value;
                if (selectedElement2) {
                    selectedElement2.style.backgroundColor = ""; 
                    selectedElement2.style.color = "";
                    totalAmount -= selectedPrice2;
                }
                selectedElement2 = this;
                selectedPrice2 = price;
            }

            // Apply styling to the selected element and add its price to total amount
            this.style.backgroundColor = "#87CEEB"; 
            this.style.color = "#FFFFFF";
            totalAmount += price;

            if (price === 0 || isNaN(price)) {
                alert('다른 좌석을 선택하세요.');
                return; // 함수 실행 중지
            }

            document.getElementById('finalprice').value = totalAmount;

            var finalPrice = parseFloat(document.getElementById('finalprice').value.replace(',', ''));
            var expense1 = parseFloat(document.getElementById('expense1').value.replace(',', ''));
            var tax = parseFloat(document.getElementById('tax').value.replace(',', ''));
            // 0711
            var sitPrice = finalPrice + expense1 + tax;
            document.getElementById('sitprice').value = sitPrice.toLocaleString();
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
		    //var intPassenNum = parseInt(passenNum);
		    
		    console.log("승객명수 passenNum: "+passenNum);
		    console.log(name, sit1, sit2, email, phone, gender, passenNum);
		    
		    
		    if (name.trim() == "" || sit1.trim() == ""|| sit2.trim() == ""|| email.trim() == ""|| phone.trim() == "") {
				alert('모든 필드를 입력해주세요');
				return;
			}
		    if(sit1=="매진" || sit2=="매진"){
		    	alert('좌석을 선택해주세요');
		    	return;
		    }
		    
	if (passenNum > 1) {
				
				console.log("승객명수 1보다 많음");

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
						console.log(response);
						//var newPassenNum = response.newPassenNum;
						
						passenNum = passenNum -1;
						window.location.href = 'insertPassen?passenNum='+ passenNum;
						
					},
					error: function(xhr, status, error) {
						
						console.log(error);
					}
				});
			} else {
				console.log("승객명수 1명");
				
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
						console.log(response);
						// Redirect to a new page if passenNum is 1
						window.location.href = 'finishReserv';
					},
					error: function(xhr, status, error) {
						// Handle errors
						console.log(error);
					}
				});
			}
		});
	});
	</script>







<article>



  <div id="info_journey">
			<h3 style="color: #ffffff;">
				<strong>여정정보</strong>
			</h3>

  </div>
		
		
  <div id="reservForm1">
		
		<div id="route_scheule">
			



				<div id="timeInfo"> <!-- 가는날 --> <!-- 터미널 정보 --> <!-- 항공편 번호 -->

					
					<div class="timeInfo-wrapper">
						<h4>
							<strong>${depAirportNm1}</strong>
						</h4>
						&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
						<h3>~</h3>
						&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
						<h4>
							<strong>${arrAirportNm1}</strong>
						</h4>
						&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
						<h3>||</h3>
						&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;

						<h4>
							<strong>${depPlandTime1}</strong>
						</h4>
						&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
						<h3>||</h3>
						&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
						
						<h4>
							<strong>${arrPlandTime1}</strong>
						</h4>
						&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
						<h3>||</h3>
						&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
						<h4>
							<strong>${airlineNm1}</strong>
						</h4>
					
					
						&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
						<h3>||</h3>
						&nbsp; <img src="images/humen.jpeg" width="50" height="40">
						&nbsp;
						<h4>
						&nbsp; &nbsp;
							<strong> 승객${(passenNum1 != null) ? passenNum1 : passenNum}&nbsp;명</strong>
					        <input type="hidden" name="passenNum" id="passenNum" value="${(passenNum1 != null) ? passenNum1 : passenNum}" >
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
				
				


						<div id="timeInfo"> <!-- 오는날 --> <!-- 터미널 정보 --> <!-- 항공편 번호 -->

					
					<div class="timeInfo-wrapper">
						<h4>
							<strong>${depAirportNm2}</strong>
						</h4>
						&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
						<h3>~</h3>
						&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;
						<h4>
							<strong>${arrAirportNm2}</strong>
						</h4>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<h3>||</h3>
						&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

						<h4>
							<strong>${depPlandTime2}</strong>
						</h4>
						&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
						<h3>||</h3>
						
						&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
						<h4>
							<strong>${arrPlandTime2}</strong>
						</h4>
						&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
						<h3>||</h3>
						&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
						<h4>
							<strong>${airlineNm2}</strong>
						</h4>
						
						&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
						<h3>||</h3>
						&nbsp; <img src="images/humen.jpeg" width="50" height="40">
						&nbsp; &nbsp;
						<h4>
							<strong> 승객${(passenNum1 != null) ? passenNum1 : passenNum}&nbsp;명</strong>
					        <input type="hidden" name="passenNum" id="passenNum" value="${(passenNum1 != null) ? passenNum1 : passenNum}" >
						</h4>
						&nbsp; &nbsp;
						<h4 class="price">
						
						<c:choose>
								  <c:when test="${economyCharge2 ne null and economyCharge2 ne 0 }">
								    <td>								      								      
								       <strong>이코노미석:<fmt:formatNumber value="${economyCharge2}" pattern="₩ #,###" type="currency" currencySymbol="₩" /></strong>
									   <input type="hidden" name="sit2"  value="이코노미석" >
									   <input type="hidden" name="sit2_2"  value="${economyCharge2}" >
								    </td>
								  </c:when>
								  
								  <c:otherwise>
								    <td>
								      <span style="color: red;">매진</span>
								      <input type="hidden" name="sit2"  value="매진" >
									  <input type="hidden" name="sit2_2"  value="${economyCharge2}" >
								    </td>
								  </c:otherwise>
							</c:choose>
						
						
									
						</h4>
						&nbsp; &nbsp;&nbsp; &nbsp;
					<h4 class="price">
					
					
					<c:choose>
								  <c:when test="${prestigeCharge2 ne null and prestigeCharge2 ne 0 }">
								    <td>								      								      
								       <strong>프레스티지석:<fmt:formatNumber value="${prestigeCharge2}" pattern="₩ #,###" type="currency" currencySymbol="₩" /></strong>
									   <input type="hidden" name="sit2"  value="프레스티지석" >
									   <input type="hidden" name="sit2_2"  value="${prestigeCharge2}" >
								    </td>
								  </c:when>
								  
								  <c:otherwise>
								    <td>
								      <span style="color: red;">매진</span>
								      <input type="hidden" name="sit2"  value="매진" >
									  <input type="hidden" name="sit2_2"  value="${prestigeCharge2}" >
								    </td>
								  </c:otherwise>
							</c:choose>
					
						</h4>
					</div>
				</div>
					

		</div>
</div>


		<br> <br>

	<br> <br>
	
	<form action="finishReserv" method="post" >
		    <br><br>
			<div id="notice">
			예약 후 성명 변경은 불가하오니 탑승 시 제시할 신분증에 기재된 성명과 언어가 정확히 일치하는지 확인하시기 바랍니다.
			</div>
			<br><br>


			 
					<div id="info_journey">
						<h3 style="color: #ffffff;">
							<strong>승객정보</strong>
						</h3>
			
					</div>
					
				<div id="form-wrapper">	
							<div id="reservForm">	<!-- 0711 -->
								<div id="passenForm1" class="passenForm" style="float:left; height:100%;text-align:left; margin-top: 20px;">
									<h2 style="padding:0; margin-top:26px; margin-bottom: 50px;"><span>이름&nbsp;:&nbsp;</span></h2>
									<h2 style="padding:0; margin-bottom: 50px;"><span>전화번호&nbsp;:&nbsp;</span></h2>
									<h2 style="padding:0; margin-bottom: 50px;"><span>이메일&nbsp;:&nbsp;</span></h2>
									<h2 style="padding:0; margin-bottom: 50px;"><span>성별&nbsp;:&nbsp;</span></h2>
									<input type="hidden" id="sit_1" name="sit_1" ><!-- 선택한 가는날 좌석 -->
									<input type="hidden" id="sit_2" name="sit_2" ><!-- 선택한 오는날 좌석 -->
								</div>
								
								<div id="passenForm2" class="passenForm" style="margin-top: 20px;">
									<span><input name="name" type="text" id="name" size="30"></span>
									<span><input name="phone" type="text" id="phone" size="30"></span>
									<span><input name="email" type="text" id="email" size="30"></span><br>
									<span><input type="radio" id="gender" name="gender" value="F" size="30" style="width:40px;height:40px;font-size:14pt;
											    border: 1px solid #00256c;    border-radius: 4px; margin-top: 20px" checked/>
											    <b style="vertical-align: middle;display: inline-block; margin-bottom: 32px;color:#5d5f61;">여성</b>
  										  <input type="radio" id="gender" name="gender" value="M" size="30" style="width:40px;height:40px;font-size:14pt;
											    border: 1px solid #00256c;    border-radius: 4px; margin-top: 20px"/>
											    <b style="vertical-align: middle;display: inline-block; margin-bottom: 32px;color:#5d5f61;">남성</b></span>
								</div> 
								<br><br>
							<div id="buttons">
								<!-- <input type="submit" id="submitButton" value="예약하기" class="submit" /> -->
								<input type="button" id="nextButton" value="다음으로" class="submit" style="margin:0;"/>
							</div>
							
							</div>
					
								<div id="info_price">
								      <div class="jb-division-line"></div>
								      		<br>
											<div class="info_price_row">
												<label><strong>항공운송료</strong></label>
											</div>
											<br><br>
											<div class="info_price_row">
												<label for="finalprice">운임</label> <!-- 0711 -->
												<input name="finalprice" id="finalprice" disabled>
											</div>
											<br>
											<div class="info_price_row">
												<label for="expense1">유류할증료</label>
												<input id="expense1" value="15,400" disabled>
											</div>
											<br>
											<div class="info_price_row">
												<label for="tax">세금,수수료 및 기타요금</label>
												<input id="tax" value="8,000" disabled>
											</div>
											<br><br>
									 <div class="jb-division-line"></div>
									       <br><br>
											<div class="info_price_row">
												<label for="sitprice">총액</label>
												<input name="sitprice" id="sitprice" >
											</div>	
											<br><br>
									 <div class="jb-division-line"></div>
							</div>
												
				
		 </div>
	</form>
</article>
<%@ include file="../include/footer.jsp"%>