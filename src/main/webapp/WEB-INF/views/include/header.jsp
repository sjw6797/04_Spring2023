<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="/css/header.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="/script/member.js"></script>
</head>
<body>
<div id="wrap">
	<header>
		<div class="head">
		<div id="topmodel">
			<c:if test="${not empty loginUser}">
				<label style="border-bottom: 1px solid black" onclick="location.href='memberUpdateForm' ">회원정보 수정</label>
			</c:if>
			<label style="border-bottom: 1px solid black" onclick="location.href='qnaList?first=1' ">고객지원</label>
			<label style="border-bottom: 1px solid black" onclick="location.href='boardList' ">공지사항</label>
			<label>선택하기1</label>
		</div>
			<img src="/images/메인로고.png" height="100" onclick="location.href='/' " style="cursor: pointer;">
			<ul class="headul">
				<c:choose>
					<c:when test="${empty loginUser}">
						<li><a href="loginForm">로그인</a></li>
						<li><a href="joinForm">회원가입</a></li>
					</c:when>
					<c:otherwise>
						<li>${loginUser.id}(${loginUser.name})님</li>
						<li><a href="logout">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
				<li><a href="admin">admin</a></li>
				<li id="topbutton" style="cursor: pointer;">〓</li>
			</ul>
		</div>
	</header>