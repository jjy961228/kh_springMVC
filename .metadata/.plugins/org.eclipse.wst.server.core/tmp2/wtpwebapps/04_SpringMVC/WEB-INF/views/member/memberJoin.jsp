<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	span.guide {
		display : none;
		font-size : 12px;
		top : 12px;
		right : 10px;
	}
	span.ok { color : green }
	span.error { color : red }
</style>
</head>
<body>
	<h1 align="center">회원가입</h1>
	<div class="">
		<form action="/member/register.kh" method="post">
			<table>
				<tr>
					<td> * 아이디</td>
					<td>
						<input type="text" id="memberId" name="memberId">
						<span class="guide ok">이 아이디는 사용 가능합니다.</span>
						<span class="guide error">이 아이디는 사용할 수 없습니다.</span>
					</td>
				</tr>
				<tr>
					<td> * 비밀번호</td>
					<td>
						<input type="password" name="memberPwd">
					</td>
				</tr>
				<tr>
					<td> * 이름</td>
					<td>
						<input type="text" name="memberName">
					</td>
				</tr>				
				<tr>
					<td> * 이메일</td>
					<td>
						<input type="text" name="memberEmail">
					</td>
				</tr>	
				<tr>
					<td> * 전화번호</td>
					<td>
						<input type="text" name="memberPhone">
					</td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td>
						<input type="text" name="post" class="postcodify_postcode5" size="6">
						<button type="button" id="postcodify_search_button">검색</button>
					</td>
				</tr>
				<tr>
					<td>도로명 주소</td>
					<td>
						<input type="text" name="address1" class="postcodify_address">
					</td>
				</tr>
				<tr>
					<td>상세 주소</td>
					<td>
						<input type="text" name="address2" class="postcodify_extra_info">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="가입하기">
					</td>
				</tr>						
			</table>
		</form>
	</div>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
	<script> 
		$(function() { 
			$("#postcodify_search_button").postcodifyPopUp(); 
		});
		$("#memberId").on("blur", function() {
			var memberId = $("#memberId").val();
			$.ajax({
				url : "/member/checkDupId.kh",
				type : "get",
				data : { "memberId" : memberId },
				success : function(data) {
					console.log(data);
					if(data != 0) {
						$(".guide.ok").hide();
						$(".guide.error").show();
					}else {
						$(".guide.ok").show();
						$(".guide.error").hide();
					}
				},
				error : function() {
					alert("ajax 전송 실패! 관리자에게 문의해주세요.");
				}
			});
		});
	</script>
</body>
</html>