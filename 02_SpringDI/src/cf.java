
public class cf {
	/*
	<의존성 주입> : 02_springDI 프로젝트
	setter방법, 생성자 방법

	1)maven으로 바꿔주기
	프로젝트우클릭 new -> configure -> convert to maven project 에서 바꿔주기
	-> 01_HelloSping의 pom.xml에서 <dependencies></dependencies>  복붙해주기 (이를하면 spring프로젝트로 바뀐다)
	-> 프로젝트 클릭후 alt+f5 하면 새싹모양이 생긴다 (이게 spring으로 바뀐증거)
	->

	2)src에 srping-context.xml 파일 만들기 (bean을 사용하기 위해서)
	src 우클릭 new -> spring bean configureation file만들기 -> bean,context 선택, 맨밑에 url 클릭 -> 완료

	----------------------------------------기본셋팅완료-------------------------------------
	1.setter 메서드 이용한 방법
	1) spring-contetx.xml 에서 bean생성
		bean설정시,  setter메서드의 setId() 의 값을 name="id"로 설정한다
		<bean id="messageBean" class="org.kh.hellospring.MessageBeanHello"></bean>
	2) CustomerRun 에서 setter/생성자 이용한 방법으로 의존성 주입
		ApplicationContext ctx = new GenericXmlApplicationContext("spring-context.xml"); //("spring-context.xml") : 파일명
			Customer customer = (Customer)ctx.getBean("customer"); // "customer" : bean태그의 id값
			System.out.println(customer.toString());
			System.out.println();
	*/
}
