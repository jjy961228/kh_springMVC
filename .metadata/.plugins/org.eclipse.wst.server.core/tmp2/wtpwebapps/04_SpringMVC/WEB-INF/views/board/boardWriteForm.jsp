<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<body>
	<h1 align="center">게시글 등록 페이지</h1>
	<br><br>
	<form action="/board/register.kh" method="post" enctype="multipart/form-data"> <!-- boardWriteView.jsp -> BoardController   -->
		<table align="center" border="1">
		<!-- vo클래스(board)의 멤버변수 명으로 input태그의 name속성값을 바꿔야지만 Controller에서 @ModelAttribute를 사용할 수 있다	  -->
			<tr>
				<td>제목</td>
				<td><input type="text" size="50" name="boardTitle"></td>
			</tr>
			<tr>
				<td>작성자</td> <!-- login한 id로 꽂을거다 -->
				<td><input type="text" size="50" name="boardWriter"></td>
			</tr>
			<tr>
				<td>내용</td> 
				<td><textarea rows="5" cols="50" name="boardContents"></textarea></td> <!-- 공간이 많이필요해서 <texarea> 태그쓴다 -->
			</tr>
			<tr>
				<td>첨부파일</td>
				<td><input type="file" name="uploadFile"></td>
			</tr>
			<tr>
				<td colspan="2" >
					<input type="submit" value="등록">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>