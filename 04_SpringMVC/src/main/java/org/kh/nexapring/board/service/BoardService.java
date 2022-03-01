package org.kh.nexapring.board.service;

import java.util.List;

import org.kh.nexapring.board.domain.Board;
import org.kh.nexapring.board.domain.PageInfo;
import org.kh.nexapring.board.domain.Reply;

public interface BoardService {
		
	public int getListCount(); //게시물의 전체리스트: Controller(getListCount()) -> Service(getListCount()) -> ServiceImpl(getListCount())
	public List<Board> printAll(PageInfo pi);  ////게시물을 가져오는것: BoardController(printAll()) -> Service(printAll()) -> ServiceImpl(printAll())
	
	abstract public int registerBoard(Board board);	//게시판 등록: boardWriteForm.jsp -> Controller(boardRegister()) -> Service(boardRegister())  ->ServiceImpl(boardRegister(insertBoard())
	
	public Board printOneByNo(Integer boardNo);
	
	public List<Reply> printAllReply(int boardNo);
	public int registerReply(Reply reply);
	public int removeReply(int replyNo);
}
