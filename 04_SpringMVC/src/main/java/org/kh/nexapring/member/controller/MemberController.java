package org.kh.nexapring.member.controller;

import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kh.nexapring.member.domain.Member;
import org.kh.nexapring.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//Request는 session가져올때만 쓴다 !!!!

@Controller
public class MemberController {
	
	//DI(의존성주입)
	@Autowired
	private MemberService mService;
	
	@ResponseBody
	@RequestMapping(value="/member/checkDupId.kh", method=RequestMethod.GET)
	public String idDuplicateCheck(
			@RequestParam("memberId") String memberId) {
		int result = mService.checkIdDup(memberId); 
		// 데이터가 있으면 객체 or 1 or true, 없으면 null or 0 or false
//		if(result > 0) {
//			return "success";
//		}else {
//			return "fail";
//		}
		return String.valueOf(result);
	}
	
	
	@RequestMapping(value="/member/register.kh" , method=RequestMethod.POST)
	public String memberRegister(Model model
			,@ModelAttribute Member member //@ModelAttribute로인해서 getparam, 객체생성후 set해주는걸(밑에주석들) 할 필요가 없어진다
//			,@RequestParam("member-id") String memberId
//			,@RequestParam("member-pwd") String memberPwd
//			,@RequestParam("member-name") String memberName
//			,@RequestParam("member-email") String memberEmail
//			,@RequestParam("member-phone") String memberPhone
			,@RequestParam("post") String post
			,@RequestParam("address1") String address1
			,@RequestParam("address") String address2
			) {// HttpServletRequest request :  request.getParameter 이용해 memberJoin.jsp의 name값을 가져오기위해 썼다 
	//	request.setCharacterEncoding("utf-8");
	
	//	String memberId = request.getParameter("member-id");
	//	String memberPwd = request.getParameter("member-pwd");
	//	String memberName = request.getParameter("member-name");
	//	String memberEmail = request.getParameter("member-email");
	//	String memberPhone = request.getParameter("member-phone");
	//	String post = request.getParameter("post");
	//	String address1 = request.getParameter("address1");
	//	String address2 = request.getParameter("address2");
		//enrollDate,updateDate,mStatus 안적는 이유 : default일때, sysdate로 처리하려고
	//	Member member = new Member();
	//	member.setMemberId(memberId);
	//	member.setMemberPwd(memberPwd);
	//	member.setMemberName(memberName);
	//	member.setMemberEmail(memberEmail);
	//	member.setMemberPhone(memberPhone);
		member.setMemberAddr(post + ","+ address1 +"," + address2);
		
		try {
			int result =mService.registerMember(member);
			if(result > 0) {
				//response.sendRedirect("home.kh");
				return "redirect:home.kh"; //성공페이지로 이동(home.kh)
											//MemberControler -> HomeContorller -> home.jsp
			}else {
				//request.setAttribute("msg", "회원가입 실패");	=model.add
				model.addAttribute("msg", "회원가입 실패");
				return "common/errorPage";
			}			
		}catch(Exception e) {
			model.addAttribute("msg", e.toString());	//회원가입 실패인데, 왜 어떤오류때문에 실패했는지 나타내주기위해 썼다
			return "common/errorPage";
		}
		
		
		
		// return null; 리턴을 둘다해줬으니까 필요가없어진다
	}
	
	
	
	
	@RequestMapping(value="/member/login.kh" , method=RequestMethod.POST)
	public String memberLogin(HttpServletRequest request
				, @RequestParam("user-id") String memberId	// String memberId = request.getParameter("user-id"); 대체가능
				, @RequestParam("user-pwd") String memberPwd//	String memberPwd = request.getParameter("user-pwd"); 대체가능
			) {
		
//		String memberId = request.getParameter("user-id");
//		String memberPwd = request.getParameter("user-pwd");
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPwd(memberPwd);
		try {
			Member loginUser = mService.loginMember(member);
			//비지니스로직: MemberController -> MemberService -> MemberServiceImpl -> MemberStore -> MemberStoreLogic -> member-mapper.xml과 최종연동해 session에 로그인 정보를 담았다
			if(loginUser != null) {
				//아이디정보(loginUser)가 있으면 , 세션에담기
				HttpSession session = request.getSession();
				session.getAttribute("loginUser, loginUser");	//원래는 id값만 받아야한다
				return "redirect:/home.kr";
			}else {
				//세션파기
				return "common/errorPage";
			}
		}catch(Exception e) {
			request.setAttribute("msg", e.toString());
			return "common/errorPage";
		}	
		
	}
	
	//핸들러 매핑: home.jsp에 있는 주소를 받아서
	@RequestMapping(value="/member/logout.kh" , method=RequestMethod.GET)
	public String memberLogout(HttpServletRequest request) {//로그아웃: 기존의 session에 로그인 정보가 들어있다 , 이것을 가져와서 null(x)이면, 파기
		HttpSession session = request.getSession();	//세션 가져오기
		 if(session != null) {//세션 파기 후, 시작페이지 이동
			 session.invalidate();
			 return "redirect:/home.kh";
		 }else {
			 request.setAttribute("msg", "로그아웃 실패!");
			 return "common/errorPage";
		 }
	}
	
	
	
	@RequestMapping(value="/member/modify.kh" , method=RequestMethod.GET)
	public String memberMyInfo(HttpServletRequest request) {
		//비지니스 로직을 작성하여 DB에서 데이터를 가져온 후에 request.setAttribute 필요하나 생략
		//-> Session에 있는 데이터로 대체(비추천) why? Session에는 민감한 정보(비밀번호, 회원번호 등)가 들어있으니까 다 보여주면 안되서
		
		return "member/myPage";
	}
	
	
	
	
	//HandlerMapping :
		//Dispatcher Servlet(web.xml) -> HandlerMapping(@RequestMapping) : url을 어디로 매핑할거니 ? 라고 물어본다
	@RequestMapping(value="/member/joinView.kh", method=RequestMethod.GET)
	public String memberJoinView() {
		return "member/memberJoin";	//Dispatcher Servlet(web.xml) -> ViewResovler(servlet-context.xml) : 어떤화면 보여줄거니? 	이건그냥 url매핑이아니고, jsp페이지 경로로 보여주는거다
									//									servlet-context.xml에 의해 앞에 (/WEB-INF/views/) member/memberJoin (.jsp) 붙여준다
	}
	
	
		
	
	
	//회원정보수정: home.jsp -> myPage.jsp -> MemberController -> MemberService
	//핸들러 매핑: myPage.jsp(action="/member/modify.kh") 매핑
	@RequestMapping(value="/member/modify.kh", method=RequestMethod.POST)
	public String memberModify(
			HttpServletRequest request	//아직까지는 request 써주니까 남아있는것이다
										//reqeust는 session 쓸때 필요하다
			, Model model
			, @ModelAttribute Member member
			,@RequestParam("post") String post
			,@RequestParam("address1") String address1
			,@RequestParam("address2") String address2)
	{
//		request.setCharacterEncoding("utf-8");
//		String memberId = request.getParameter("member-id");
//		String memberPwd = request.getParameter("member-pwd");
//		String memberName = request.getParameter("member-name");
//		String memberEmail = request.getParameter("member-email");
//		String memberPhone = request.getParameter("member-phone");
//		String memberAddr = request.getParameter("member-addr");
//		Member member = new Member();
//		member.setMemberId(memberId);
//		member.setMemberPwd(memberPwd);
//		member.setMemberName(memberName);
//		member.setMemberEmail(memberEmail);
//		member.setMemberPhone(me2mberPhone);
//		member.setMemberAddr(memberAddr);
		member.setMemberAddr(post+","+address1+","+address2);
		HttpSession session = request.getSession();	//세션하기위해 꼭 서줘야한다
		try {
			int result = mService.modifyMember(member);
			if(result > 0) {
				session.setAttribute("loginUser", member); //회원정보 수정하자마자 바로 바뀌게 하기위해 써준다
				return "redirect:/home.kh";
			}else {
				model.addAttribute("msg", "회원 정보 수정 실패");
//				request.setAttribute("msg", "회원정보수정 실패");
				return "common/errorPage";
			}
		}catch(Exception e) {
			model.addAttribute("msg", e.toString());
//			request.setAttribute("msg", e.toString());
			return "common/errorPage";
		}
	}
	
	
	
	//탈퇴하기
	//핸들러 매핑(myPage.jsp 의 onclick="location.href='/member/remove.kh?memberId=${loginUser.memberId }'")
	//myPage.jsp -> Controller(memberRemove();)
	@RequestMapping(value="/member/remove.kh", method=RequestMethod.GET)
	public String memberRemove(
			//HttpServletRequest request
			Model model
			,@RequestParam("memberId") String memberId) {		
		
		//String memberId= request.getParameter("memberId");	//mypage.jsp에서 보낸 쿼리스트링 값을 받는다
		try {
			//비지니스로직
			int result = mService.removeMember(memberId);
			if(result > 0) {//성공페이지
				return "redirect:/member/logout.kh";
				
			}else {
				//request.setAttribute("msg", "회원탈퇴실패");
				model.addAttribute("msg", "회원탈퇴실패");
				return "common/errorPage";
			}
		}catch(Exception e){//실패페이지
			model.addAttribute("msg", e.toString());
			//request.setAttribute("msg", e.toString());
			return "common/errorPage";
		}
		
	}
	
	

	
/*(@RequestParam으로 바꾸기 전 원래상태 )
@RequestMapping(value="/member/login.kh" , method=RequestMethod.POST)
	public String memberLogin(HttpServletRequest request) {
		String memberId = request.getParameter("user-id");
		String memberPwd = request.getParameter("user-pwd");
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPwd(memberPwd);
		try {
			Member loginUser = mService.loginMember(member);
			//비지니스로직: MemberController -> MemberService -> MemberServiceImpl -> MemberStore -> MemberStoreLogic -> member-mapper.xml과 최종연동해 session에 로그인 정보를 담았다
			if(loginUser != null) {
				//아이디정보(loginUser)가 있으면 , 세션에담기
				HttpSession session = request.getSession();
				session.getAttribute("loginUser, loginUser");	//원래는 id값만 받아야한다
				return "redirect:/home.kr";
			}else {
				//세션파기
				return "common/errorPage";
			}
		}catch(Exception e) {
			request.setAttribute("msg", e.toString());
			return "common/errorPage";
		}	
		
	} 
 
 
@RequestMapping(value="/member/register.kh" , method=RequestMethod.POST)
	public String memberRegister(HttpServletRequest request) throws UnsupportedEncodingException {// HttpServletRequest request :  request.getParameter 이용해 memberJoin.jsp의 name값을 가져오기위해 썼다 
		request.setCharacterEncoding("utf-8");
		String memberId = request.getParameter("member-id");
		String memberPwd = request.getParameter("member-pwd");
		String memberName = request.getParameter("member-name");
		String memberEmail = request.getParameter("member-email");
		String memberPhone = request.getParameter("member-phone");
		String post = request.getParameter("post");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		//enrollDate,updateDate,mStatus 안적는 이유 : default일때, sysdate로 처리하려고
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPwd(memberPwd);
		member.setMemberName(memberName);
		member.setMemberEmail(memberEmail);
		member.setMemberPhone(memberPhone);
		member.setMemberAddr(post + ","+ address1 +"," + address2);
		
		try {
			int result =mService.registerMember(member);
			if(result > 0) {
				//response.sendRedirect("home.kh");
				return "redirect:home.kh"; //성공페이지로 이동(home.kh)
											//MemberControler -> HomeContorller -> home.jsp
			}else {
				request.setAttribute("msg", "회원가입 실패");
				return "common/errorPage";
			}			
		}catch(Exception e) {
			request.setAttribute("msg", e.toString());	//회원가입 실패인데, 왜 어떤오류때문에 실패했는지 나타내주기위해 썼다
			return "common/errorPage";
		}
		
		
		
		// return null; 리턴을 둘다해줬으니까 필요가없어진다
	}
	
	
	
	
*/
	
	
	
	
	
	
	
	
	
	
	
	
}
