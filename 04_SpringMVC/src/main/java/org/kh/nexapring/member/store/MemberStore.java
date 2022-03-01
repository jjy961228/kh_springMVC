package org.kh.nexapring.member.store;

import org.apache.ibatis.session.SqlSession;
import org.kh.nexapring.member.domain.Member;

public interface MemberStore {
	//회원가입
	public int insertMember(SqlSession session, Member member);
	
	//로그인: MemberController -> MemberService -> MemberServiceImpl -> MemberStore -> MemberStoreLogic
	public Member selectLoginMember(SqlSession session, Member member);
	
	public int checkIdDup(SqlSession session, String memberId);
	
	//회원정보수정: home.jsp -> myPage.jsp -> MemberController -> MemberService -> MemberServiceImpl(updateMember();) -> MemberStore -> MemberStoreLogic
	public int updateMember(SqlSession session, Member member); 
	
	//회원탈퇴 : 같은비지니스로직이다
	public int deleteMember(SqlSession session, String memberId);
}
