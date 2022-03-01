<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>My Spring Web Page</title>
	<link href="resources/css/menubar-style.css" rel="stylesheet">
</head>
<body>
	<a href="/board/writeView.kh">게시글 등록</a>
	<h1 align="center">Welcome Our WebSite!!</h1>
	<div class="login-area">
		<c:if test="${empty sessionScope.loginUser }">
			<form action="/member/login.kh" method="post">
				<table align="right">
					<tr>
						<td>아이디 : </td>
						<td><input type="text" name="user-id"></td>
						<td rowspan="2">
							<input type="submit" value="로그인">
						</td>
					</tr>
					<tr>
						<td>비밀번호 : </td>
						<td><input type="password" name="user-pwd"></td>
					</tr>
					<tr>
						<td colspan="3">
							<a href="/member/joinView.kh">회원가입</a>&nbsp;<a>아이디/비밀번호 찾기</a>
						</td>
					</tr>
				</table>
			</form>
		</c:if>
		<c:if test="${not empty loginUser }">
			<table align="right">
				<tr>
					<td colspan="2">${loginUser.memberName }님 환영합니다.</td>
				</tr>
				<tr>
					<td><button onclick="showMyInfo();">정보수정</button></td>
					<td><button onclick="location.href='/member/logout.kh'">로그아웃</button></td>
				</tr>
			</table>
		</c:if>
	</div>
	<div class="nav-area">
		<div class="menu">HOME</div>
		<div class="menu">공지사항</div>
		<div class="menu">자유게시판</div>
		<div class="menu">ETC</div>
	</div>
	<script>
		function showMyInfo() {
			location.href = "/member/myInfo.kh";
		}
	</script>
</body>
</html>
