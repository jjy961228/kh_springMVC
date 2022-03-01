package com.kh.spring;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// store.logic, MessageStoreLogic
// @Component
@Repository
public class MessageDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertMessage(MessageDTO messageDTO) {
		int result = sqlSession.insert("MessageMapper.insertMessage", messageDTO);
		return result;
	}

	// SELECT * FROM MESSAGE
	public List<MessageDTO> listMessage() {
		List<MessageDTO> mList = sqlSession.selectList("MessageMapper.listMessage");
		return mList;
	}
}
