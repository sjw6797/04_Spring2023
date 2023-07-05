<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>
.clear {
	clear: both;
}

#wrap {
	width: 1000px;
	text-align: center;
	margin: 20px;
	margin-right: auto;
	margin-left: auto;
}

.nav {
	position: absolute;
	margin: 0 auto;
	font-weight: bold;
	top: 80%;
	left: 50%;
	transform: translate(-50%, -50%);
}

.nav ul {
	position: relative;
	list-style-type: none;
	display: flex;
	padding: 0;
}

.nav li {
	float: left;
	margin-left: 70px;
}

.nav li a {
	display: inline;
	width: 200px;
	text-align: center;
	text-decoration: none;
	color: black;
	padding: 10px 15px;
	margin: 0 auto;
}

.nav li a:hover {
	/* background-color: #6495ED; */
	border-bottom: 5px solid #013066;
}

.header-container {
	position: relative;
	display: flex;
	justify-content: space-between;
	margin: 0 auto;
	width: 100%;
	height: 250px;
	background-color: #6dcbf1;
	margin-bottom: 50px;
}

.logo {
	margin: 0 auto;
}

.logout_a {
	font-size: 110%;
	font-weight: bold;
	color: white;
}

a {
	text-decoration: none;
	color: black;
}

.logout_a:hover {
	border-bottom: 5px solid #013066;
}

.btn:hover {
	background: linear-gradient(to bottom, #476e9e 5%, #7892c2 100%);
	background-color: #476e9e;
}

.btn:active {
	position: relative;
	top: 1px;
}

table.adminTable {
	border-collapse: collapse;
	width: 100%;
	margin-bottom: 20px;
}

table.adminTable th, table.adminTable td {
	padding: 8px;
	border-bottom: 1px solid #333;
	border: 1px solid black;
	text-align: center;
}

table.adminTable th {
	font-size: 130%;
	/* background-color: #CCFFE5; */
	background-color: #65728a;
	color: white;
	font-weight: bold;
}

table.adminTable td {
	background-color: #ffffff;
	font-size: 120%;
}

.input_div {
	margin-bottom: 30px;
}

.input_text {
	width: 400px;
	font-size: 130%;
	height: 30px;
	outline: none;
	border: 0;
	border-bottom: 1px solid black;
}

.input_button {
	height: 40px;
	font-size: 130%;
	margin-left: 30px;
	border-radius: 5px 5px 5px 5px;
	border: 0;
	background-color: #013066;
	color: white;
	cursor: pointer;
	font-weight: bold;
	color: white;
}
</style>
</head>

<body style="margin: 0 auto;">
	<div class="header-container">
		<div class="logo" style="margin: 0 auto;">
			<a href="air.do?command=adminLogout">
				<img style="width: 700px;" src="image/어드민로고.png">
			</a>
		</div>
		<div style="float: right; position: absolute; right: 4%; top: 13%;">
			<a href="air.do?command=adminLogout" class="logout_a">로그아웃</a>
		</div>
		<div class="nav">
			<ul>
				<li><a href="adminMemberList?first=1">유저관리</a></li>
				<li><a href="adminQnaList?first=1">Q&A관리</a></li>
				<li><a href="adminBoardList?first=1">공지사항관리</a></li>
				<li><a href="passengerList?first=1">예약관리</a></li>
			</ul>
		</div>
	</div>

	<div class="clear"></div>
	<div id="wrap">