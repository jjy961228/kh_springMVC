package org.kh.nexapring.board.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.kh.nexapring.board.domain.Board;
import org.kh.nexapring.board.domain.PageInfo;
import org.kh.nexapring.board.domain.Reply;
import org.kh.nexapring.board.store.BoardStore;
import org.springframework.stereotype.Repository;
@Repository
public class BoardStoreLogic implements BoardStore{

	@Override
	public int selectListCount(SqlSession sqlSession) {
		int totalCount = sqlSession.selectOne("BoardMapper.selectListCount");
		return totalCount;
	}
	@Override
	public List<Board> selectAll(SqlSession sqlSession, PageInfo pi) {
		/**
		 *  RowBounds는 쿼리문을 변경하지 않고도 페이징을 처리할 수 있게 해주는 클래스
		 *  RowBounds의 동작은 offset값과 limit값을 이용해서 동작함
		 *  offset값은 변하는 값이고 limit값은 고정값
		 *  limit값이 한 페이지당 보여주고 싶은 게시물의 갯수이고
		 *  offset은 건너뛰어야 할 값임
		 *  ex) limit 10, offset 0,  1~10
		 *  			  offset 10, 11~20
		 *  offset값은 currentPage에 의해서 변경됨
		 */
		// 1페이지 -> 1 ~ 10  offset(시작값): 0 , 	limit(한페이지당 보여주는 게시물갯수): 10 
		//						offset: 1,2,3 페이지에따라 달라지는 변수이다	, limit :변하지 않는다
		// 2페이지 -> 11 ~ 20	offset:10,	limit:10
		int limit = pi.getBoardLimit();	//pi.getBoardLimit() :Board의 한 페이지당 보여줄 게시물의 갯수이다 . 	pi로 넘겨받았었다
		int currentPage = pi.getCurrentPage(); // currentPage: 현재목록의 페이지 번호
		int offset= (currentPage-1) * limit;
		RowBounds rowBounds = new RowBounds(offset,limit);	//pi로 페이징처리 정보를 받았으니까 , offset과 limit는 pi를 이용해 구한다 
		List<Board> bList = sqlSession.selectList("BoardMapper.selectAllList", pi, rowBounds);	//pi,rowBounds: 넘겨주는 애들
		return bList;
	}
	@Override
	public Board selectOneByNo(SqlSession sqlSession, Integer boardNo) {
		Board board = sqlSession.selectOne("BoardMapper.selectOneByNo",boardNo);
		return board;
	}
	//게시판 등록: boardWriteForm.jsp -> Controller(boardRegister()) -> Service(boardRegister())  ->ServiceImpl(boardRegister(insertBoard()) ->Store(insertBoard) -> SotreLogic(insertBoard())
	//				->board-mapper.xml 에서 DB와 연동해, INSERT 해서 INT값으로 가져온다
	@Override
	public int insertBoard(SqlSession sqlSession, Board board) {
		int result = sqlSession.insert("BoardMapper.insertBoard", board);
		return result;
	}
	
	
	@Override
	public List<Reply> selectAllReply(SqlSession sqlSession, int boardNo) {
		List<Reply> rList = sqlSession.selectList("BoardMapper.selectReplyList", boardNo);
		return rList;
	}
	
	@Override
	public int insertReply(SqlSession sqlSession, Reply reply) {
		int result = sqlSession.insert("BoardMapper.insertReply", reply);
		return result;
	}
	@Override
	public int deleteReply(SqlSession sqlSession, int replyNo) {
		int result = sqlSession.delete("BoardMapper.deleteReply", replyNo);
		return result;
	}


}
