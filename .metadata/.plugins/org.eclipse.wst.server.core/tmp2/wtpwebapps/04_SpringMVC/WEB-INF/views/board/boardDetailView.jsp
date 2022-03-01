<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<h1 align="center">${board.boardNo }번 게시글 상세보기</h1>
	<br><br>
	<table align="center" width="500" border="1">
		<tr>
			<td>제목</td>
			<td>${board.boardTitle }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${board.boardWriter }</td>
		</tr>
		<tr>
			<td>작성날짜</td>
			<td>${board.bCreateDate }</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${board.boardCount }</td>
		</tr>
		<tr height="300">
			<td>내용</td>
			<td>${board.boardContents }</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<a href="#">수정 페이지로 이동</a>
				<a href="#">삭제하기</a>
			</td>
		</tr>
	</table>
	<!-- 댓글 등록 -->
	<table align="center" width="500" border="1">
		<tr>
			<td>
				<textarea rows="3" cols="55" id="rContents"></textarea>
			</td>
			<td>
				<button id="rSubmit">등록하기</button>
			</td>
		</tr>	
	</table>
	<!-- 댓글 목록 -->
	<table align="center" width="500" border="1" id="rtb">
		<thead>
			<tr>
			<!-- 댓글 갯수 -->
				<td colspan="4"><b id="rCount"></b></td>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<br><br><br><br><br><br><br><br><br><br>
	<script>
		getReplyList();
		$("#rSubmit").on("click", function() {
			var refBoardNo = "${board.boardNo }";
			var rContents = $("#rContents").val();
			$.ajax({
				url : "/board/replyAdd.kh",
				type : "post",
				data : { "refBoardNo" : refBoardNo, "replyContents" : rContents },
				success : function(data) {
					if(data == "success") {
						// 댓글 불러오기
						getReplyList();
						// 작성 후 내용 초기화
						$("#rContents").val("");
					}else{
						alert("댓글 등록 실패");
					}
				},
				error : function() {
					alert("ajax 실패!");
				}
			});
		});
		
		function getReplyList() {
			var boardNo = "${board.boardNo }";
			$.ajax({
				url : "/board/replyList.kh",
				type : "get",
				data : { "boardNo" : boardNo },
				success : function(data) {
					var $tableBody = $("#rtb tbody");
					$tableBody.html("");
					var $rWriter;
					var $rContent;
					var $rCreateDate;
					var $btnArea;
					var $tr;
					$("#rCount").text("댓글 (" + data.length + ")"); //댓글 갯수 표시
					// document.querySelector("#rCount").value = "댓글 (" + data.length + ")";
					if(data.length > 0) {
						for(var i in data) {
							$tr = $("<tr>");
							$rWriter = $("<td width='100'>").text(data[i].replyWriter);
							$rContent = $("<td>").text(data[i].replyContents);
							$rCreateDate = $("<td width='100'>").text(data[i].rCreateDate);
							
							$btnArea = $("<td width='80'>")
											.append("<a href='javascript:void(0);' onclick='modifyViewReply();'>수정 </a>")
											.append("<a href='javascript:void(0);' onclick='removeReply("+data[i].replyNo+");'>삭제</a>");
							$tr.append($rWriter);
							$tr.append($rContent);
							$tr.append($rCreateDate);
							$tr.append($btnArea);
							$tableBody.append($tr);
						}
					}
				},
				error : function() {
					alert("ajax 통신 실패! 관리자에게 문의하세요.");
				}
			});
		}
		function removeReply(replyNo) {
			$.ajax({
				url : "/board/replyDelete.kh",
				type : "get",
				data : { "replyNo" : replyNo },
				success : function(data) {
					if(data == "success") {
						getReplyList();
					}else {
						alert("댓글 삭제 실패!");
					}
				},
				error : function() {
					alert("ajax 통신 오류! 관리자에게 문의해주세요.");
				}
			});
		}
		function modifyViewReply() {
			//alert("test");
			var $trModify
		}
	</script>
</body>
</html>