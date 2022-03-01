<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax 알아보기</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<h1>Ajax 개요</h1>
	<p>Ajax는 Asynchronous Javascript And XML이란 용어로<br>
	서버로부터 데이터를 가져와 전체 페이지를 새로 고치지 않고 일부만 로드할 수 있게 하는 기법<br>
	비동기식 요청을 함.</p>
	<h3>동기식/비동기식이란?</h3>
	<p>동기식은 서버와 클라이언트가 동시에 통신하여 프로세스를 수행 및 종료까지 같이하는 방식<br>
	이에 반해 비동기식은 페이지 리로딩없이 서버요청 사이사이 추가적인 요청과 처리 가능</p>
	<h3>Ajax 구현(Javascript)</h3>
	<h4>1. ajax로 서버에 전송값 보내기</h4>
	<p>버튼 클릭시 전송값을 서버에서 출력</p>
	<input type="text" id="msg-1">
	<button onclick="jsFunc();">JS로 보내기</button>
	<h3>Ajax 구현(jQuery)</h3>
	<h4>2. ajax로 서버에 전송값 보내기</h4>
	<p>버튼 클릭시 전송값을 서버에서 출력</p>
	<input type="text" id="msg-2">
	<button onclick="jQueryFunc();">jQuery로 보내기</button>
	<h3>3. 버튼 클릭시 서버에서 보낸 값 수신</h3>
	<button id="jq-btn1">서버에서 보낸 값 확인</button>
	<p id="confirm-area"></p>
	<h4>4. 서버로 전송값 보내고 결과 문자열 받아서 처리</h4>
	<p>숫자 2개를 전송하고 더한 값 받기</p>
	첫번째 수 : <input type="text" id="num-1"><br>
	두번째 수 : <input type="text" id="num-2"><br>
	<button id="jq-btn4">전송 및 결과확인</button>
	<p id="p4"></p>
	<h4>5. 서버로 전송값 보내고 결과 JSON으로 받기</h4>
	<p id="p5"></p>
	<button id="jq-btn5">실행</button>
	<h4>6. 서버로 전송값 보내고 결과 JSONArray로 받기</h4>
	<p id="p6"></p>
	<button id="jq-btn6">실행</button>	
	<h4>7. 서버로 전송값 보내고 결과 Gson으로 받기</h4>
	<p id="p7"></p>
	<button id="jq-btn7">실행</button>	
	<br><br><br><br><br><br><br><br><br><br><br>
	<script>
		function jsFunc() {
			// 1. XMLHttpRequest 객체 생성
			var xhttp = new XMLHttpRequest();
			// 2. 요청정보 설정
			var msg = document.querySelector("#msg-1").value;
			xhttp.open("GET", "/ajax/test1.kh?msg="+msg, true);
			// 3. 데이터 처리에 따른 동작함수 설정
			xhttp.onreadystatechange = function() {
				if(this.readyState == 4 && this.status == 200) {
					console.log("서버 전송 성공");
				}else if(this.readyState == 4 && this.status == 404) {
					console.log("서버 전송 실패");
				}
			}
			// 4. 전송
			xhttp.send();
		}
		function jQueryFunc() {
			var msg = $("#msg-2").val();
			$.ajax({
				url : "/ajax/test1.kh",
				data : { "msg" : msg },
				type : "get",
				success : function() {
					console.log("서버 전송 성공");
				},
				error : function() {
					console.log("오류 발생");
				}
			});
		}
		
		$("#jq-btn1").on("click", function() {
			$.ajax({
				url : "/ajax/test2.kh",
				type : "get",
				success : function(data) {
					$("#confirm-area").html(data);
				},
				error : function() {
					console.log("처리실패");
				}
			});
		});
		
		$("#jq-btn4").on("click", function() {
			var num1 = $("#num-1").val();
			var num2 = $("#num-2").val();
			$.ajax({
				url : "/ajax/test3.kh",
				type : "get",
				data : { "num1" : num1, "num2" : num2 },
				success : function(result) {
					$("#p4").html("연산결과 : " + result);
				},
				error : function() {
					console.log("실패");
				}
			});
		});
		
		$("#jq-btn5").on("click", function() {
			$.ajax({
				url : "/ajax/test4.kh",
				type : "get",
				success : function(result) {
					console.log(result);
					console.log(result.boardNo);
					console.log(result.boardWriter);
					$("#p5").html("결과 : " + result.boardNo + ", " + result.boardWriter);
				},
				error : function() {
					alert("ajax 실패!");
				}
			});
		});
		
		$("#jq-btn6").on("click", function() {
			$.ajax({
				url : "/ajax/test5.kh",
				type : "get",
				success : function(dataList) {
// 					console.log(dataList);
					var result = "";
					for(var i = 0; i < dataList.length; i++) {
// 						console.log(dataList[i].boardNo);
// 						console.log(dataList[i].boardTitle);
// 						console.log(dataList[i].boardContents);
						result += dataList[i].boardNo + ", " + dataList[i].boardTitle + ", " + dataList[i].boardContents + "<br>";
					}
					$("#p6").html(result);
				},
				error : function() {
					alert("ajax 오류!");
				}
			});
		});
		$("#jq-btn7").on("click", function() {
			$.ajax({
				url : "/ajax/test6.kh",
				type : "get",
				success : function(dataList) {
					console.log(dataList);
				},
				error : function() {
					alert("ajax 오류!");
				}
			});
		});
	</script>
</body>
</html>










