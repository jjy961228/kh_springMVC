package org.kh.nexapring.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.kh.nexapring.board.domain.Board;
import org.kh.nexapring.board.domain.PageInfo;
import org.kh.nexapring.board.domain.Reply;

//BoardStore: DB와 가깝다 . 따라서, DB와 가까운단어를 쓴다 insertBoard 등등
public interface BoardStore {
		
	public int selectListCount(SqlSession sqlSession);
	public List<Board> selectAll(SqlSession sqlSession, PageInfo pi);
	
	public int insertBoard(SqlSession sqlSession, Board board);	//게시판 등록: boardWriteForm.jsp -> Controller(boardRegister()) -> Service(boardRegister())  ->ServiceImpl(boardRegister(insertBoard()) ->Store(insertBoard) -> SotreLogic(insertBoard()) 
	
	public Board selectOneByNo(SqlSession sqlSession, Integer boardNo);
	
	
	public List<Reply> selectAllReply(SqlSession sqlSession, int boardNo);
	public int insertReply(SqlSession sqlSession, Reply reply);
	public int deleteReply(SqlSession sqlSession, int replyNo);
		

}
