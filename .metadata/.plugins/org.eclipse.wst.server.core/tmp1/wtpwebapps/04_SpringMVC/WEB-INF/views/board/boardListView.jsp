<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
</head>
<body>
	<h1 align="center">게시글 목록</h1>
	<br><br>
	<a href="/board/writeView.kh">게시글 작성</a>
	<br>
	<table align="center" border="1">
		<!-- 번호, 제목, 작성자, 날짜, 조회수, 첨부파일 -->
		<tr>
			<th>번호</th>
			<th width="300">제목</th>
			<th>작성자</th>
			<th>날짜</th>
			<th>조회수</th>
			<th>첨부파일</th>
		</tr>
		<c:forEach items="${bList }" var="board">
			<tr>
				<td>${board.boardNo }</td>
				<c:url var="bDetail" value="/board/detail.kh">
					<c:param name="boardNo" value="${board.boardNo }"></c:param>
				</c:url>
				<td><a href="${bDetail }">${board.boardTitle }</a></td>
				<td>${board.boardWriter }</td>
				<td>${board.bCreateDate }</td>
				<td>${board.boardCount }</td>
				<td>X</td>
			</tr>
		</c:forEach>
		<tr align="center" height="20">
			<td colspan="6">
				[이전]
				<c:forEach var="p" begin="${pi.startNavi }" end="${pi.endNavi }">
					<c:url var="pagination" value="/board/list.kh">
						<c:param name="page" value="${p }"></c:param>
					</c:url>
					<a href="${pagination }">${p }</a>&nbsp;
				</c:forEach>
				[다음]
			</td>
		</tr>
	</table>
</body>
</html>