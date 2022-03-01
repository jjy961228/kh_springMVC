package org.kh.nexapring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kh.nexapring.board.domain.Board;
import org.kh.nexapring.board.domain.PageInfo;
import org.kh.nexapring.board.domain.Reply;
import org.kh.nexapring.board.service.BoardService;
import org.kh.nexapring.common.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*
 * boarListView.jsp: 자유게시판(게시글목록,게시글작성)
 * boardWriteForm.jsp: 게시글 작성
 * boardDetailView: 게시글 상세
 * */



//board: 페이징처리,파일업로드,다운로드,댓글 
@Controller	//전에는 @Component로 bean등록했지만, @Controller로 한번에 bean등록한다
public class BoardController {
	
	//DI
	@Autowired 
	private BoardService bService;
	
	
	//자유게시판(게시글목록): "/board/list.kh" -> Controller(boardListView(bList를 보내준다)) -> boardListView.jsp(bList를 jstl로 받는다)
	@RequestMapping(value="/board/list.kh", method=RequestMethod.GET)
	public String boardListView(
			Model model,	//model을 썻기에 PageInfo의 멤버변수 값이 get,set 되어있다
			 
			@RequestParam(value="page", required=false) Integer page) {	//requestParam을 써서 boardListView.jsp에서,value="page:에 의해서 name값(page)를 가져오지만,  required=false 필수값은 아니다
																		//Integer: null체크 가능   Int: null체크 불가능
		int currentPage = (page != null)? page : 1; //page != null 이면, page값을 가져오고, 아니면 1값을 가져와~	 즉, page값이 아무것도 없으면 1값으로 가져와라~
		
		//게시물의 전체리스트: 비지니스로직 -> DB에서 전체 게시물 갯수 가져옴
		int totalCount = bService.getListCount();
		
		//Pagination을 static 으로 만들었기때문에 선언하지않고 바로 사용가능하다
		PageInfo pi = Pagination.getPageInfo(currentPage, totalCount); //Pagination -> pageInfo  에 값을 셋팅해주었다
						//pageNavigation과 관련된 정보들을 객체로 넣을것이다 
		
		//게시물을 가져오는것: 비지니스 로직 ->DB에서 데이터를 가져와야함		
		List<Board> bList = bService.printAll(pi);
		if(!bList.isEmpty()) { //성공실패페이지 : 기존의 방식처럼 try,catch가 더 좋다
			//성공페이지
			model.addAttribute("bList", bList); //bList라는 이름으로 bList를 model에 저장한다 -> boardListView.jsp에서 사용한다
			model.addAttribute("pi", pi);	// -> boardListView.jsp에서 사용한다(navi구성할때)
			return "board/boardListView";	//ViewResover(servlet-context.xml) 에의해 (/WEB-INF/views/)board/boardListView(.jsp) 로 이동시켜준다 	
		}else {
			//실패페이지 
			model.addAttribute("msg", "게시글 전체 조회 실패");
			return "common/errorPage";
		}
		
		
		
		
	}
	
	
	
	
	
	@RequestMapping(value="/board/detail.kh", method=RequestMethod.GET)
	public String boardDetailView(
			Model model
			, @RequestParam("boardNo") Integer boardNo) {
		// 조회수 증가 - 비즈니스 로직
		
		// 비즈니스 로직
		Board board = bService.printOneByNo(boardNo);
		if(board != null) {
			model.addAttribute("board", board);
			return "board/boardDetailView";
		}else {
			model.addAttribute("msg", "게시글 조회 실패");
			return "common/errorPage";
		}
	}
	
	
	
	
	
	
	//boardListView.jsp(<a href="/board/writeView.kh">게시글 작성</a>)
	//-> 컨트롤러의 boardWriteView(return "board/boardWriteForm" )
	//-> 게시글 등록 boardWriteForm.jsp(action="/board/register.kh" ) -> 컨트롤러의 boardRegister
	@RequestMapping(value="/board/writeView.kh", method=RequestMethod.GET)
	public String boardWriteView() {
		return "board/boardWriteForm";
	}
	
	
	
	
	//게시글 등록: boardWriteForm.jsp -> Controller(boardRegister) -> Service 
	@RequestMapping(value="/board/register.kh" , method=RequestMethod.POST)
	//boardWirteForm.jsp의 input태그 name값을 Board클래스의 멤버면수명과 맞춰줬기때문에 ModelAttribute를 쓸 수 있다.
	public String boardRegister(	//힘들게 getter,setter,변수선언 이딴거 안해도된다
			Model model,
			@ModelAttribute Board board	) {	
			try {
				//비지니스 로직
				int result = bService.registerBoard(board);
				if(result > 0) {
					//성공페이지
					return "redirect:/board/list.kh";
				}else {
					//실패페이지
					model.addAttribute("msg", "회원가입실패");
					return "common/errorPage";
				}
			}catch(Exception e) {
				model.addAttribute("msg", e.toString());
				return "common/errorPage";
			}
 
	}
	
	
	
	
	
	
	public String saveFile(MultipartFile uploadFile, HttpServletRequest request) {
		// 파일 경로
		String root = request.getSession()
							.getServletContext().getRealPath("resources");
		String savePath = root + "\\buploadFiles";
		// 폴더 선택
		File folder = new File(savePath);
		// 폴더 없으면 자동 생성
		if(!folder.exists()) {
			folder.mkdir();
		}
		// 파일명 변경
		// sample.png -> A
		//     => 202201281437.png
		// sample.png -> B
		//     => 202201271422.png
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String originalFileName = uploadFile.getOriginalFilename();
		String renameFileName = sdf.format(new Date(System.currentTimeMillis()))
				+"."+originalFileName.substring(originalFileName.lastIndexOf(".")+1);
		String filePath = folder + "\\" + renameFileName;
		// 파일 저장
		try {
			uploadFile.transferTo(new File(filePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 파일명 리턴
		return renameFileName;
	}
	@ResponseBody
	@RequestMapping(value="/board/replyList.kh", method=RequestMethod.GET
											, produces="application/json;charset=utf-8")
	public String boardReplyList(
			@RequestParam("boardNo") int boardNo) {
		List<Reply> rList = bService.printAllReply(boardNo);
		if(!rList.isEmpty()) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			return gson.toJson(rList);
		}
		return null;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/board/replyAdd.kh", method=RequestMethod.POST)
	public String boardReplyAdd(
			@ModelAttribute Reply reply) {
		String replyWriter = "admin";
		reply.setReplyWriter(replyWriter);
		int result = bService.registerReply(reply);
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/board/replyDelete.kh", method=RequestMethod.GET)
	public String boardReplyRemove(
			@RequestParam("replyNo") int replyNo) {
		int result = bService.removeReply(replyNo);
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
}