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
			<th width="300">제목</th> <!-- 제목이 길수도 있으니까 width를 줬다  -->
			<th>작성자</th>
			<th>날짜</th>
			<th>조회수</th>
			<th>첨부파일</th>
		</tr>
		
		<!-- Controller(boardListView(bList);) => boardListView.jsp 에서 jstl로 받았다 -->
		<c:forEach items="${bList }" var="board"> <!--<c:foreach>태그안에  begin="1", end="10" 넣어주면 : 10개가 나온다-->
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
		
		<!-- pageNavi(naviBar) -->
		<tr align="center" height="20">
			<td colspan="6">
				[이전]
				<c:forEach var="p" begin="${pi.startNavi }" end="${pi.endNavi }"> <!-- begin:시작값 , end: 끝값, 	p: for문이 돌면서 값을 1개씩 넣어준다	3번 반복이면 p는 1,2,3 이 된다 -->
					<!-- ${pi.currentPage } :현재페이지 값 , ${p} : 현재의 인덱스값-->
					<!-- <a href= "/board/list.kh?page=${pi.currentPage }">${p }</a>   		name값:?page=${pi.currentPage } , value값: ${p } --> 
					<c:url var="pagination" value="/board/list.kh"> <!-- /board/list.kh을   var를 이용해 pagination으로 지정 -->
						<c:param name="page" value="${p }"></c:param>	<!-- 지정한 name값, valuer값을 불러오기만해서 쓴다 -->
					</c:url>
					<a href="${pagination }">${p }</a>&nbsp;
					
				</c:forEach>
				[다음]
			</td>
		</tr>
	</table>
</body>
</html>