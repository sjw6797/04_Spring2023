<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/login.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="/script/member.js"></script>
</head>
<body>
	<div class="login-container">
		<h2>로그인</h2>
		<form method="post" action="loginCheck" name="loginFrm">
			<div class="form-group">
				<label>아이디</label>
				<input type="text" name="id" id="id" value="${dto.id}">
			</div>
			<div class="form-group">
				<label for="password">비밀번호</label>
				<input type="password" id="pwd" name="pwd">
			</div>
			<p>${message}</p>
			<div class="form-group">
				<input type="submit" value="로그인">
				<input type="button" class="btn-secondary" id="login" value="회원가입" onclick="location.href='joinForm' ">
				<input type="button" class="btn-secondary" id="login" value="카카오 회원가입" onclick="location.href='kakaostart' ">
				<input type="button" class="btn-secondary" value="메인으로" onclick="location.href='/' ">
			</div>
		</form>
	</div>
</body>
</html>