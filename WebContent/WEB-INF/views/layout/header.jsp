<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일루와</title>

<link href="/resources/css/common.css" rel="stylesheet"> 
<script type="text/javascript" src="/resources/js/jquery-2.2.4.min.js"></script>
<script>
	$(function(){
		/*네비*/
		$('.dropmenu').hover(function(){
			$(this).children('ol').toggleClass('open')
		})
		/*마이페이지버튼*/
		$('.tooltip>a').click(function(){
			$(this).next('ol').toggleClass('open')
		})
	})
</script>
</head>
<body>
<div id="header">
	<div class="inner">
		<a href="/" class="logo"><img src="/resources/img/logo.png" alt="logo"></a>
		<ul>
			<li><a href="#">숙소예약</a></li>
			<li><a href="#">추천맛집</a></li>
			<li class="dropmenu">
				<a href="javascript:;">커뮤니티</a>
				<ol>
					<li><a href="#">공지사항</a></li>
					<li><a href="#">자유게시판</a></li>
				</ol>
			</li>
		</ul>
		<div class="rgtmenu">
			<a href="#">호스트되기</a>
			<div class="tooltip">
				<a href="#"><img src="/resources/img/ico_my.png" width="60px"></a>
				<ol>
					<% if( request.getSession().getAttribute("login") !=null ){ %>	
						<% if( (Integer)request.getSession().getAttribute("usergrade")==2   ){ %>			
						<%-- 호스트일때 --%>
						<li><a href="#">계정관리</a></li>
						<li><a href="#">예약</a></li>
						<li><a href="#">이용후기</a></li>
						<li><a href="#">북마크한 숙소</a></li>
						<li><a href="#">예약관리</a></li>
						<li><a href="#">내가 등록한 숙소</a></li>
						<li><a href="/logout">로그아웃</a></li>
						<%}else{ %>
						<li><a href="#">계정관리</a></li>
						<li><a href="#">예약</a></li>
						<li><a href="#">이용후기</a></li>
						<li><a href="#">북마크한 숙소</a></li>
						<li><a href="/logout">로그아웃</a></li>	
						<%} %>
					<% }else{ %>
					<li><a href="/login">로그인</a></li>
					<li><a href="/join">회원가입</a></li>
					<%} %>
				</ol>
			</div>
		</div>
	</div>
</div>
<div id="content">