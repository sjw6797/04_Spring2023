<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<article>


<form method="post" action="air.do?command=flightInfo">

출발지 : <input type = "text" name="depAirportNm">
도착지 : <input type = "text" name="arrAirportNm">

출발하는 날 :<input type = "text" name="depPlandTime">
돌아오는 날:<input type = "text" name="returnPlandTime">



 <input type="submit" value="검색하기" class="submit"/>

</form>


</article>