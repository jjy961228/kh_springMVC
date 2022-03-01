package org.kh.nexapring.board.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.kh.nexapring.board.domain.Board;
import org.kh.nexapring.board.domain.PageInfo;
import org.kh.nexapring.board.domain.Reply;
import org.kh.nexapring.board.service.BoardService;
import org.kh.nexapring.board.store.BoardStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardStore bStore;
	@Autowired
	private SqlSession sqlSession;
	
	@Override //Controller(getListCount()) -> Service(getListCount()) -> ServiceImpl(getListCount(selectListCount())) -> Store(
	public int getListCount() { 
		int totalCount = bStore.selectListCount(sqlSession);
		return totalCount;
	}
	
	@Override //BoardController(printAll()) -> Service(printAll()) -> ServiceImpl(printAll())
	public List<Board> printAll(PageInfo pi){
		List<Board> bList = bStore.selectAll(sqlSession, pi);
		return bList;
	}
	
	
	@Override
	public Board printOneByNo(Integer boardNo) {
		Board board = bStore.selectOneByNo(sqlSession, boardNo);
		return board;
	}
	
	//게시판 등록: boardWriteForm.jsp -> Controller(boardRegister()) -> Service(boardRegister())  ->ServiceImpl(boardRegister(insertBoard())
	@Override
	public int registerBoard(Board board) {
		int result = bStore.insertBoard(sqlSession, board);
		return result;
	}

	@Override
	public List<Reply> printAllReply(int boardNo) {
		List<Reply> rList = bStore.selectAllReply(sqlSession, boardNo);
		return rList;
	}
	
	@Override
	public int registerReply(Reply reply) {
		int result = bStore.insertReply(sqlSession, reply);
		return result;
	}

	@Override
	public int removeReply(int replyNo) {
		int result = bStore.deleteReply(sqlSession, replyNo);
		return result;
	}


}
