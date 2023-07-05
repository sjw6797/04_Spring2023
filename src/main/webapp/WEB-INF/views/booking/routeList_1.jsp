<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
body {
	font-family: Arial, sans-serif;
	/*background-color: #f3f7f9;*/
}

article {
	width: 1300px;
	max-width: 1900px;
	margin: 0 auto;
}

#routeInfo { /* 출발지 -> 도착지 */
	display: flex;
	align-items: center;
	width: 260px;
	background: #00256c;
	border-radius: 10px 10px 0 0;
	padding: 10px;
}

.route-info-wrapper {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 100%;
}

#routeInfo span:not(:last-child) {
	margin-right: 10px;
	margin-left: 10px;
}

.routeList {
	margin-top: 0;
	margin: 0px auto;
	width: 100%;
	border-collapse: collapse;
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.05);
	border: 2px solid #00256c;
}

.routeList th, .routeList td {
	padding: 20px;
	text-align: left;
	color: #5d5f61;
}

.routeList th {
	background-color: #ffffff;
	color: #808588;
	text-transform: uppercase;
	letter-spacing: 2px;
	font-size: 14px;
	cursor: pointer;
}

.routeList td {
	background-color: #ffffff;
	font-size: 14px;
	cursor: pointer;
}

.routeList tr:not(:first-child) {
	border-top: 1px solid #e8e8e8;
}

.routeList tr:hover td {
	background-color: #e0e0e0;
}

input[type="submit"] {
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
	border-color: #00256c;
}

input[type="submit"]:hover {
	background-color: #005a8e;
}

input[type="text"] {
	width: 30%;
	padding: 10px;
	margin: 10px 0;
	border: 1px solid #ddd;
	border-radius: 4px;
}

label {
	display: block;
	margin: 10px 0;
	font-size: 16px;
	color: #5d5f61;
}

article h2 {
	text-align: center;
	color: #5d5f61;
	padding: 20px 0;
}
 p {
          margin:auto;
          margin-top:30px;
          text-align:center;
        }
/* Add the hover property to the .routeList tr class */
</style>
<script>
let clickedRowData = null; 
document.addEventListener("DOMContentLoaded", function() {
    function rowClicked() {
       
    	var table = document.getElementById('routeList');
        if (!table) {
            console.error('Cannot find a table with id "routeList". Please check your HTML code.');
            return;
        }
        var rowList = table.rows;
        
        for (i = 1; i < rowList.length; i++) {
        	
            var row = rowList[i];
            
            row.onclick = function() {
            	     	
            	
            	
            	var depAirportNm = this.cells[0].innerHTML.trim();
            	var arrAirportNm = this.cells[1].innerHTML.trim();
            	 
		          var depPlandTime = this.cells[2].innerText.trim(); // 출발일시 
		          var arrPlandTime = this.cells[3].innerText.trim(); // 도착일시 
		          var airlineNm = this.cells[4].innerText.trim(); // 항공사 이름  
		          var economyCharge = this.cells[5].innerText.trim(); // 이코노미 
		          var prestigeCharge = this.cells[6].innerText.trim(); // 프레스티지석 
		          
			        console.log('depAirportNm: ' + depAirportNm);
		            console.log('arrAirportNm: ' + arrAirportNm);
		            console.log('depPlandTime: ' + depPlandTime);
		            console.log('arrPlandTime: ' + arrPlandTime);
		            console.log('economyCharge: ' + economyCharge);
		            console.log('prestigeCharge: ' + prestigeCharge);
		            console.log('airlineNm: ' + airlineNm);
		            
		            
		            
               // var cells = this.cells;
                var str=  depAirportNm+ 
                          "/" + arrAirportNm+
                          "/" + depPlandTime +
                          "/" + arrPlandTime +
                          "/" + economyCharge +
                          "/" + prestigeCharge +
                          "/" + airlineNm;
               
                clickedRowData = {
                		depAirportNm: depAirportNm,
                		arrAirportNm: arrAirportNm,
                		depPlandTime: depPlandTime,
                		arrPlandTime: arrPlandTime,
                		economyCharge: economyCharge,
                		prestigeCharge: prestigeCharge,
                		airlineNm: airlineNm
                	};
                
                console.log(str);
                document.querySelector('p').innerText = str;
            };
        }            
    }
    rowClicked();
});

function goto_routeList2() {
    // Check if any row has been clicked
    if (clickedRowData === null) {
        alert("Please select a row first!");
        return;
    }

    // Process data and create parameters
    let params = {};
    for (let key in clickedRowData) {
        let value = clickedRowData[key];

        // Remove line breaks and tab characters
        value = value.replace(/\n|\t/g, "");

        // URL encode the processed value
        
       value = encodeURIComponent(value.trim());
        
        //value = value.innerText.trim();;
        
        console.log( "1.value   "+ value);
        
        params[key] = decodeURIComponent(value);
        
        console.log( "2.params   "+ params);
    }

    // Convert parameters object to string
    let paramsStr = new URLSearchParams(params).toString();
    console.log( "3.paramsStr   "+ paramsStr);

    // Create form
    let form = document.createElement('form');
    form.method = 'POST';
    form.action = "/flightInfo2";
    form.style.display = 'none';

    // Split params string into individual parameters
    let splitParams = paramsStr.split("&");

    splitParams.forEach(param => {
        let [key, value] = param.split("=");
        let input = document.createElement('input');
        input.type = 'hidden';
        input.name = key;
        value = decodeURIComponent(value); // Decode value before setting
        console.log( "4.input.value   "+ value);
        input.value = value;
        form.appendChild(input);
    });

    // Append form to body and submit
    document.body.appendChild(form);
    form.submit();
}

</script>
<html>


<article>
	<h2>출발하는날 노선 결과물 routeList ${flag }</h2>
	<form id="frm" method="post" action="flightInfo2">
		<input type="hidden" name="flag" value="${flag}">
		<div id="routeInfo">
			<div class="route-info-wrapper">
				<span>
					<h4 style="color: #ffffff;">${depAirportNm}&nbsp;<input type= "hidden" name="depAirportNm" value="${depAirportNm}"></h4>
				</span>
				<span>
					<h3 style="color: #ffffff;">→</h3>
				</span>
				<span>
					<h4 style="color: #ffffff;">${arrAirportNm}&nbsp;<input type= "hidden" name="arrAirportNm" value="${arrAirportNm}"></h4>
				</span>
			</div>
		</div>
		<div class="row">
		
		
			<table class="routeList" id="routeList">
				<tr>
					
					<th>출발지 </th> 
					<th>목적지 </th>
					<th>출발일시 </th>						
					<th>도착일시</th>
					<th>항공사</th>  
					<th>이코노미석 가격</th>
					<th>프레티지석 가격</th>
				</tr>
				
				
				
				<c:forEach items="${dep_list}" var="item" varStatus="state"> 
					    <tr>
					        <td>
					            ${item.depAirportNm}
					        </td>
					        <td>					            
					            ${item.arrAirportNm}
					        </td>
					        <td>				           
					            ${item.depPlandTime}
					        </td>
					        <td>					           
					            ${item.arrPlandTime}
					        </td>
					        <td>				            
					            ${item.airlineNm}
					        </td>
					        <td>				           
					            ${item.economyCharge}
					        </td>
					        <td>					           
					            ${item.prestigeCharge}
					        </td>
					    </tr>
						</c:forEach>

			</table>
			
 			<p></p>
			<input type="submit" value="선택하기" class="submit" onclick="event.preventDefault(); goto_routeList2()"/>
		</div>
	</form>
</article>

</html>

