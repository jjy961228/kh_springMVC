<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
	<h1 align="center">마이페이지 - 정보수정</h1>
	<div class="">
		<form action="/member/modify.kh" method="post">
			<table>
				<tr>
					<td> * 아이디 </td>
					<td>
						<input type="text" name="memberId" value="${loginUser.memberId }" readonly>
					</td>
				</tr>
				<tr>
					<td> * 비밀번호 </td>
					<td>
						<input type="password" name="memberPwd" value="${loginUser.memberPwd }">
					</td>
				</tr>
				<tr>
					<td> * 이름 </td>
					<td>
						<input type="text" name="memberName" value="${loginUser.memberName }" readonly>
					</td>
				</tr>
				<tr>
					<td> * 이메일 </td>
					<td>
						<input type="text" name="memberEmail" value="${loginUser.memberEmail }">
					</td>
				</tr>
				<tr>
					<td> * 전화번호 </td>
					<td>
						<input type="text" name="memberPhone" value="${loginUser.memberPhone }">
					</td>
				</tr>
				
				<c:forTokens items="${loginUser.memberAddr }" delims="," var="addr" varStatus="status">
					<c:if test="${status.index eq 0 }">
						<tr>
							<td>우편번호</td>
							<td>
								<input type="text" name="post" class="postcodify_postcode5" size="${$addr}">
								<button type="button" id="postcodify_search_button">검색</button>
							</td>
						</tr>
					</c:if>
					
					<c:if test="${status.index eq 1 }">
						<tr>
							<td>도로명 주소</td>
							<td>
								<input type="text" name="address1" class="postcodify_address">
							</td>
						</tr>
					</c:if>
					
					
					<c:if test="${status.index eq 2 }">
						<tr>
							<td>상세 주소</td>
							<td>
								<input type="text" name="address2" class="postcodify_extra_info">
							</td>
						</tr>
					</c:if>
					
				</c:forTokens>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="수정하기">
						<button type="button" onclick="location.href='/member/remove.kh?memberId=${loginUser.memberId }'">탈퇴하기</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
	<script> $(function() { 
		$("#postcodify_search_button").postcodifyPopUp(); 
		});</script>
	}
</body>
</html>