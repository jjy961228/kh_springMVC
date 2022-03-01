package org.kh.nexapring.common;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.kh.nexapring.board.domain.Board;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class AjaxController {
	
	// ajaxTest.jsp 보여주기용
	@RequestMapping(value="/ajax/view.kh", method=RequestMethod.GET)
	public String ajaxView() {
		return "common/ajaxTest";
	}

	//
	@ResponseBody
	@RequestMapping(value="/ajax/test1.kh", method=RequestMethod.GET)
	public String ajaxExercise1(
			@RequestParam("msg") String msg) {
		System.out.println("전송받은 데이터 : " + msg);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/ajax/test2.kh", method=RequestMethod.GET, produces="text/plain;charset=utf-8")
	public String ajaxExercise2() {
		return "서버에서 왔습니다.";
	}
	
	@ResponseBody
	@RequestMapping(value="/ajax/test3.kh", method=RequestMethod.GET)
	public String ajaxExercise3(
			@RequestParam("num1") int num1
			,@RequestParam("num2") int num2) {
		int result = num1 + num2;
		// int -> String : 
		// 1. result+"";
		// 2. String.valueOf(result);
		// 3. new StringBuffer().append(result).toString();
		// String -> int : Integer.parseInt
		return result+"";
	}
	
	@ResponseBody
	@RequestMapping(value="/ajax/test4.kh", method=RequestMethod.GET
												, produces="application/json;charset=utf-8")
	public String ajaxExercise4() {
		Board board = new Board();
		board.setBoardNo(1);
		board.setBoardTitle("게시글 테스트1");
		board.setBoardContents("게시글 테스트입니다.");
		board.setBoardWriter("khuser11");
		board.setBoardFilename("kh.png");
		JSONObject jsonObj = new JSONObject(); // board -> json형태로 변환
		jsonObj.put("boardNo", board.getBoardNo());
		jsonObj.put("boardTitle", board.getBoardTitle());
		jsonObj.put("boardContents", board.getBoardContents());
		jsonObj.put("boardWriter", board.getBoardWriter());
		jsonObj.put("boardFilename", board.getBoardFilename());
		return jsonObj.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value="/ajax/test5.kh", method=RequestMethod.GET
							, produces="application/json;charset=utf-8")
	public String ajaxExercise5() {
		List<Board> bList = new ArrayList<Board>();
		bList.add(new Board(1, "게시글1", "게시글1 내용", "khuser01", "kh.png"));
		bList.add(new Board(2, "게시글2", "게시글2 내용", "khuser02", "kh2.png"));
		bList.add(new Board(3, "게시글3", "게시글3 내용", "khuser03", "kh3.png"));
		bList.add(new Board(4, "게시글4", "게시글4 내용", "khuser04", "kh4.png"));
		JSONArray jsonArr = new JSONArray();
		for(Board bOne : bList) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("boardNo", bOne.getBoardNo());
			jsonObj.put("boardTitle", bOne.getBoardTitle());
			jsonObj.put("boardContents", bOne.getBoardContents());
			jsonObj.put("boardWriter", bOne.getBoardWriter());
			jsonObj.put("boardFilename", bOne.getBoardFilename());
			jsonArr.add(jsonObj);
		}
		return jsonArr.toJSONString();
	}
	@ResponseBody
	@RequestMapping(value="/ajax/test6.kh", method=RequestMethod.GET
							, produces="application/json;charset=utf-8")
	public String ajaxExercise6() {
		List<Board> bList = new ArrayList<Board>();
		bList.add(new Board(1, "게시글1", "게시글1 내용", "khuser01", "kh.png"));
		bList.add(new Board(2, "게시글2", "게시글2 내용", "khuser02", "kh2.png"));
		bList.add(new Board(3, "게시글3", "게시글3 내용", "khuser03", "kh3.png"));
		bList.add(new Board(4, "게시글4", "게시글4 내용", "khuser04", "kh4.png"));
		Gson gson = new Gson();
		return gson.toJson(bList);
	}
}

























