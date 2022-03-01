package org.kh.nexapring.member.service.logic;

import org.kh.nexapring.member.domain.Member;
import org.kh.nexapring.member.service.MemberService;
import org.kh.nexapring.member.store.MemberStore;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//bean등록 하는이유 ? 컨테이너에서 다 관리할 수 있게 만든다
//MemberServiceImpl 의 Bean등록	
@Service
public class MemberServiceImpl implements MemberService{
	
	//의존성 주입 : 약한결합이 된다 MemberServiceImpl - MemberStore 가 약한결합이됐다
	//			약한결합을 위해서 클래스와 클래스 사이에 인터페이스(MemberStore)로 연결
	@Autowired
	private MemberStore mStore;
	
	//의존성 주입
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public Member loginMember(Member member) {
		////MemberController -> MemberService -> MemberServiceImpl -> MemberStore
		Member memberOne = mStore.selectLoginMember(sqlSession,member);
		return memberOne;
	}

	@Override
	public int checkIdDup(String memberId) {
		int result = mStore.checkIdDup(sqlSession, memberId);
		return result;
	}

	@Override
	public int registerMember(Member member) {
		int result = mStore.insertMember(sqlSession, member);
		return result;
	}
	
	@Override
	//회원정보수정: home.jsp -> myPage.jsp -> MemberController -> MemberService -> MemberServiceImpl(updateMember();) -> MemberStore
	public int modifyMember(Member member) {
		int result = mStore.updateMember(sqlSession, member);
		return result;
	}

	@Override
	public int removeMember(String memberId) {
		int result = mStore.deleteMember(sqlSession, memberId);
		return result;
	}

}
