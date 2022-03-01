<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- taglib추가하기위해서는 pom.xml에 해줘도되만, 오류가나서 lib폴더에 넣었다 -->
<html>
<head>
	<title>My Spring Web Page</title>
	<!-- home.jsp에 먹일 css :  member-style.css -->
	<link href="resources/css/menubar-style.css" rel="stylesheet">
</head>
<body>
	<a href="/board/writeView.kh">게시글 등록</a>
	<h1 align="center">Welcome Our WebSite!!</h1>
	<div class="login-area">
		<!-- loginUser값이 비어있다면 로그인 form태그를 보여줘라 ~ -->
		<c:if test="${empty sessionScope.loginUser }">	
			<form action="/member/login.kh" method="post"> <!-- home.jsp -> Controller  -->
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
		<c:if test="${not empty sessionScope.loginUser }"> <!-- loginUser값이 비어있지 않다면, ~님 환영합니다 -->
			<table align="right">
				<tr>
					<td colspan="2">${loginUser.memberName }님 환영합니다.</td>
				</tr>
				<tr>
					<td><button onclick="showMyInfo();">정보수정</button></td>	<!-- 정보수정 버튼을 누르면, 마이페이지로 이동하게 : home.jsp -> myPage.jsp
																						js: 고전 이벤트 모델 -->
					<td><button onclick="location.href='/member/logout.kh'">로그아웃</button></td> <!-- 로그아웃: home.jsp => MemberController의 memberLogout();
																											js: 인라인 이벤트 모델 -->
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
		function showMyInfo() {	//js: 고전이벤트모델
			location.href = "/member/myInfo.kh"; //showMyInfo()버튼 누르면 myInfo.jsp(/member/myInfo.kh) 로 이동
		}
	</script>
</body>
</html>
