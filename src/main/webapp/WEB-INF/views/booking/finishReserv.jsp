<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%>

<!DOCTYPE html>
<style>
body {
    font-family: Arial, sans-serif;
   
}

h1{
	  color:#00256c;
	  font-weight:bold;
	  	  
}

article {  /*노란색*/
	width: 1300px;
	heigth : 2000px;
    max-width: 1900px;
    max-heigth : 5000px;
    margin: 0 auto;
  
    margin: 0 auto;
    padding-top:100px;
    padding-bottom:100px;
    
}


div#page{
padding-top:100px;
padding-bottom:200px;

}


div#title{
margin: 0 auto;

}

input[type="submit"] {
	
	border: none;
	color: black;
	
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin-top : 50px;
	margin-left: 500px;
	transition-duration: 0.4s;
	cursor: pointer;
	background-color : white;
	margin-bottom : 40px;
	font-weight:bold;
	font-size : 23px;
}

#image{
padding-top:10px;

}
</style>
<meta charset="UTF-8">

<article>
<div id="page">
	<form method="post" action="/">
		<div id="title" style="text-align: center;">
			<h1>결제완료 되셨습니다</h1>
		</div>
			<img src="images/check.jpg" width="600" style="display: block; margin: 0 auto;"> 
			<br>
			<br>
			<br>
			<input type="submit" value="예약하러가기">
			<img src="images/화살표.png" width="40" height="20" id="image"> 
		
	</form>
</div>
</article>

<%@ include file="../include/footer.jsp"%>