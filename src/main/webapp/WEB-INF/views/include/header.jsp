<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
   body {
   min-width: 360px;
}
</style>
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1, minimum-scale=1"> -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=3.0,user-scalable=yes">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="/css/header.css">
<link type="text/css" rel="stylesheet" href="/css/m_header.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="/script/member.js"></script>
</head>
<body>
<script type="text/javascript">
   function reservation_select() {
      var options = 'top=10, left=10, width=500, height=600, status=no, menubar=no, toolbar=no, resizable=no';
      var url = "reservation_select"
      var name = "reservation_select"
      window.open(url, name, options);
      
   }
</script>

<div id="wrap">
   <header>
      <div class="head">
         <div id="topmodel">
            <label style="border-bottom: 1px solid black" onclick="location.href='checkReservForm' ">예약 조회</label>
            <c:if test="${not empty loginUser}">
               <label style="border-bottom: 1px solid black;" onclick="location.href='memberUpdateForm' ">회원정보 수정</label>
               <label style="border-bottom: 1px solid black;" onclick="memberDelete()">탈퇴하기</label>
            </c:if>
            <label style="border-bottom: 1px solid black" onclick="location.href='qnaList?first=1' ">고객지원</label>
            <label style="border-bottom: 1px solid black" onclick="location.href='boardList' ">공지사항</label>
         </div>
         
         <div id="myPageDiv">
            <label>회원님이 보유하신 포인트는</label>
            <b style="color: blue; font-size: 150%;">${loginUser.point}</b><b>점 입니다</b>
         </div>
      
         <img src="/images/메인로고.png" onclick="location.href='/' " id="mainlogo">
         
         <ul class="headul">
            <c:choose>
               <c:when test="${empty loginUser}">
                  <li><a href="loginForm">로그인</a></li>
                  <li><a href="joinForm">회원가입</a></li>
               </c:when>
               <c:otherwise>
                  <li style="color: green;">${loginUser.id}(${loginUser.name})님</li>
                  <li><a href="logout">로그아웃</a></li>
               </c:otherwise>
            </c:choose>
            <!-- 어드민 명령어 /admin -->
            <li id="topbutton" style="cursor: pointer;">〓</li>
         </ul>
         
      </div>
   </header>