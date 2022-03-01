package org.kh.nexapring.member.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.kh.nexapring.member.domain.Member;
import org.kh.nexapring.member.store.MemberStore;
import org.springframework.stereotype.Repository;

@Repository
public class MemberStoreLogic implements MemberStore{

	@Override
	public int insertMember(SqlSession session, Member member) {
		//MemberController -> MemberService -> MemberServiceImpl -> MemberStore -> MemberStoreLogic -> member-mapper.xml과 최종연동
		//member-mapper.xml과 연결
		//member-mapper.xml 의 namespace값: MemberMapper /<insert>태그의 id값: insertMember
		int result = session.insert("MemberMapper.insertMember", member);
		return result;
	}
	
	@Override
	public Member selectLoginMember(SqlSession session, Member member) {
		//member-mapper.xml과 연결
		Member memberOne =  session.selectOne("MemberMapper.selectLoginMember",member);
		return memberOne;
	}

	@Override
	public int checkIdDup(SqlSession session, String memberId) {
		int result = session.selectOne("MemberMapper.checkIdDup", memberId);
		return result;
	}

	
	@Override
	//회원정보수정: home.jsp -> myPage.jsp -> MemberController -> MemberService -> MemberServiceImpl(updateMember();) -> MemberStore
	//				-> member-mapper.xml과 연결
	public int updateMember(SqlSession session, Member member) {
		int result = session.update("MemberMapper.updateMember", member);
		return result;
	}

	@Override
	public int deleteMember(SqlSession session, String memberId) {
		int result = session.delete("MemberMapper.deleteMember", memberId);
		return result;
	}

}
