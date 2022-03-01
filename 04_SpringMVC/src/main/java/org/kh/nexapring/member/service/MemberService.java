package org.kh.nexapring.member.service;

import org.kh.nexapring.member.domain.Member;

//MemberService: 화면단과 가깝다 , 따라서 화면단과 가까운 메서드명을쓴다
//	서비스에서는 registerMember이고 , store에서는 insertMember이다	하지만 둘다 하는일은 비슷하다
public interface MemberService {
	//인터페이스 -> 메서드이다 : 상속받는곳에서 반드시 오버라이딩
	public int registerMember(Member member);
	public Member loginMember(Member member);	//MemberController -> MemberService -> MemberServiceImpl 에서 오러라이딩 한다
												//Member로 넘겨줬으니까 자료형은 Member이다
	public int checkIdDup(String memberId);	
	public int modifyMember(Member member);		//회원정보수정: home.jsp -> myPage.jsp -> MemberController -> MemberService -> MemberServiceImpl
	public int removeMember(String memberId);	//같은 비지니스로직 
}
