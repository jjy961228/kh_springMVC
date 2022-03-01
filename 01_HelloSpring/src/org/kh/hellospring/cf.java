package org.kh.hellospring;

public class cf {
	/*
	 * <1. Spring 기본셋팅>
	<Ecleapse 에서 Spring 셋팅>  	
	수업시간에는 STS를 사용하지않고 ECLEAPSE에서 Srping과 연동해 사용한다 
	STS?  Spring 전용 이클립스 같은것

1. Ecleapse 기본셋팅
	<글꼴>
	window -> preperences -> (General)(Appearacne)colors and fonts -> basic 
	-> Text Font -> 글꼴: D2Coding
	<색>
	window -> preperences -> (java)(Editor)Syntax Coloring -> java 
	-> Class색 : red 
	
	<utf-8> 
	window -> preperences -> (General)workspace -> other: utf-8
	<web친구들-utf-8> 
	window -> preperences -> Web 
	-> CSS Files,HTML files, JSP files 의 encoding: utf-8
	<utf-8>
	window -> preperences 
	-> (General)(Editors)(Text Editors)Spelling의 encpding:utf-8


	 * <02-MemberWeb프로젝트 만드는 순서>
	1. 프로젝트만들기
	 new -> 다이나믹웹프로젝트 
	-> source folders on bulild 에서 edit 누르고 폴더명을 src로변경
		Default output folder: build\classes 
	-> webContet적고 , Generate web.xml ~~ 체크 후 finish

	3.서버만들기 및 등록 
	new -> server -> tomcat8.5클릭 -> servername: memberWeb_server 
	-> 프로젝트 등록:  Available 에서 MemberWeb클릭     	add	configured에 추가
	-> finish

	4. 만들어진 서버의 포트번호 바꿔주기
	overview : Port Name에서 8080 -> 8888 
	Modules : path창에서 /로 바꿔주기

	5. 서버가  잘 동작하나 확인하기
	webContent 폴더밑에 index.html 파일하나 만들고, 
	서버올리고, 주소창에 127.0.0.1:8888 입력 
	
	6.Libraries 추가
	
	index.jsp: 로그인창 form
	enroll.html: 회원가입 form
	loginSuccess.html: 로그인성공 form
	memberEroor.html: 로그인 에러 form
	memberMyInfo:  회원상세정보조회 form

	<market에서 필요한거 다운>
	1. HELP -> find창에서 sts검색 
	->Eclipse Marketplace -> Spring Tools 3 (Standalone Editoin) 3.1.14 			RELEASE 다운   

 
	<Spring 프로젝트 만드는 방법>
	패키지 익스플로러 -> Spring Legacy project -> simple java 선택 -> finish
	-> 패키지 아이콘에 s가 붙는다(spring이라서)
2. maven 다운로드 
<maven 다운로드 >
	maven 검색해서 다운받는다 , 단, 수업시간에는 강사님이준비한거 쓴다
	-> 파일(apache-maven-3.5.3)다운받고 압축을푼다
		압축을 푸는게 maven이 설치되는것이다
		, 원하는 경로에 추가(tools폴더)
	-> apache-maven-3.5.3 에서 repository 폴더 생성	 
<경로설정 및 Eclispse와 연동: repostory에 저장할거야~>
	repository경로복사(C:\tools\apache-maven-3.5.3\repository) 
	->window -> preperencies
	 -> (Maven)User Settings : user Settings :  Browse버튼 클릭 
	-> C:\tools\apache-maven-3.5.3\conf\setting.xml 파일 클릭
	-> open file 클릭 -> applay
	-> setting.xml 파일에서 복사한 repository경로를
	<localRepository>C:\tools\apache-maven-3.5.3\repository</localRepository> 
	이렇게 추가한다   54번째줄에서 
	
	
	
	
	Spring context:  해보기
<기본셋팅>
	1. maven
	maven repository검색 -> 창들어와서 -> spring context검색
	->spring context 클릭 ->버전:  5.2.18 RELEASE  -> Maven꺼 복사해오기
	->이클립스의 pom.xml(01_HelloSpring/pom.xml) 파일 들어가서
	-> 5행의 <version>밑에 복사해온거 추가하기
<dependencies>
	  <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-context</artifactId>
	    <version>5.2.18.RELEASE</version>
	</dependency> 
  </dependencies>
	
	->C:\tools\apache-maven-3.5.3\repository  경로에 추가되었음을 알 수 있다

	2. 
	src 우클릭 new -> spring Bean Configureation file 선택
	-> file name: spring-context.xml ->  beans선택후, 맨마지막꺼 선택

<실제로 Spring context 해보기>
Spring context.xml의 기능 ? 
	1. 객체생성
	2. 트렌젝션관리
	3. DI설정
	4. AOP
1)bean등록
<!-- Run에서, MessageBeanHello를 사용하기위해선 bean을이용해 클래스를 등록해야한다
	bean을 이용한 클래스 등록: 
	클래스를 bean으로 등록을 하는 과정이고
	bean으로 등록된 클래스는 Spring-Container, Bean Container에의해서 관리가 된다
						 -->
2)bean 사용
ApplicationContext ctx = new GenericXmlApplicationContext("spring-context.xml");
MessageBean bean = (MessageBeanHello)ctx.getBean("messageBean");
bean.sayHello("Spring New!");
	bean의 Spring-Container에 의해서 자동으로 객체 생성되고, ctx.getBean을 이용해 필요시마다 갖다 쓸 수 있다 .
	
	*/
}
