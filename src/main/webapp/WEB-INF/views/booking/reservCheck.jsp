<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../header.jsp"%>

<meta charset="UTF-8">

<style>
<style>
body {
    font-family: Arial, sans-serif;
   
}

h2{
	   
	   margin-left:30px;
	   padding: 10px;
	   font-size : 25px;	
	  
}


h4{
	   
	   text-align: center;
	   font-size : 17px;	
	       
}

h5{
	   color: #df0000;
	   text-align: center;
	   font-size : 17px;	
	       
}

div#boxTitle img {
     margin-right: 10px;  
}

article {  /*노란색*/
	width: 1300px;
	heigth : 2000px;
    max-width: 1900px;
    max-heigth : 5000px;
    margin: 0 auto;
    /*border:1px solid #FFFF00; */
    margin: 0 auto;
    padding-top:100px;
    padding-bottom:100px;
    
}

div#searchBox{ /* 검색하기 + 검색 폼 */
/*border:2px solid #00256c; */
width: 1300px;
heigth : 2000px;
max-width: 1900px;
max-heigth : 5000px;
padding-left:400px;
padding-bottom:100px;

}

div#boxTitle{ /*남색 - 검색하기*/
border-bottom:5px solid #E2E2E2;
width: 60%; /*길이*/
text-align: center; /* 글씨위치*/
display: flex;
align-items: center;
justify-content: space-between;
box-shadow: 0px 0px 20px rgba(0,0,0,0.05);
}

div#formBox{  /*빨간색*/

/*border:2px solid #df0000;*/
width: 60%; /*길이*/
text-align: center; /*위치*/
heigth :70%;
box-shadow: 0px 0px 20px rgba(0,0,0,0.05);
}


input[type="submit"] {
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
}


input[type="button"]:hover {
    background-color: #005a8e;
}


input[type="text"] {
    width: 50%;
    padding: 15px;
    margin: 10px 0;
    border: 1px solid #696969;
    border-radius: 4px;
    border-color:#00256c;
}

input[type="number"] {
    width: 50%;
    padding: 10px;
    margin: 10px 0;
    border: 1px solid #696969;
    border-radius: 4px;
    border-color:#00256c;
}

fieldset{
postion :relative;
display : block;
}
#name{
margin-top: 40PX;
}


</style>





<article>
<div id="searchBox">
		<div id="boxTitle">
			<h2>예약 확인 하기 </h2>
			<img src="image/search.png" width="20" style="margin-right:39px;"> 
		</div>	
		
		<div id="formBox">	
			<form method="post" action="air.do"name="routeResultFrm">
			<input type="hidden" name="command" value="reservCheck">
				<!--<input type="hidden" name="command" value="Reserv">  -->
				
				<fieldset>
					<legend></legend>
						<input name="name" type="text" id="name" placeholder="이름을 입력하세요"><br>
						
				        <input name="passport" type="text" placeholder="여권번호를 입력하세요"><br>
				      
				     
				        
				</fieldset>
		
				<input type="submit" value="예약확인하기" class="submit"/><br>
				<h5>${message}</h5>
				<!--  <li><a href="routeList.jsp" class="nav-link px-2 link-secondary">노선검색 결과 페이지로</a></li>-->
				    
			   <br><br>
			    
			</form>
		</div>
</div>
</article>
<%@ include file="../footer.jsp"%>