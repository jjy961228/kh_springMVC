package org.kh.hellospring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloRun {

	public static void main(String[] args) {
		//기존의 방식: MessageBeanHello 객체를만들고 , 메서드 호출 
		// new MessageBeanHello().sayHello("Spring");
		//MessageBean bean = new MessageBeanHello(); 이렇게 객체생성 안해도되는 이유는? SpringContainer에의해서
		
//1) IOC컨테이너 생성		
		//spring-context.xml 파일을 참조해서 ApplicationContext 객체 생성
		//->bean을 이용해서 MessageBeanHello를 등록했기때문에 MessageBeanHello클래스의 정보가 담겨진다
		ApplicationContext ctx = new GenericXmlApplicationContext("spring-context.xml"); //spring-context.xml : bean등록했던 곳

//2) MessageBean객체 생성
		//업캐스팅이란? 부모의 그릇에 자식의 변수를 넣는것(부모: MessageBean, 자식: MessageBeanHello)
		//MessageBean bean = new MessageBeanHello();  밑에있는건 이모양이고 , 업캐스팅 된 것이다
		//ctx.getBean()메서드를 이용하면 bean에서 등록한 클래스(MessageBeanHello)의 정보를 사용할 수 있다				
		MessageBean bean = (MessageBeanHello)ctx.getBean("messageBean");
		//SpringContainer의 역할: bean으로 등록한 클래스(MessageBeanHello)를 꺼내서 쓸때, 자동으로 객체를 만들어준다
		//지금 자동으로 MessageBean bean = new MessageBeanHello();  객체가 생성된 것이다	

		
//spring-context.xml 을통해서 MessageBeanHello클래스의 정보를 id값(messageBean)  bean으로 등록했고	
		//1과 2의 과정을 통해 여기서 사용할 수 있게되었다
		
		
		
//3) 확인해보기
		bean.sayHello("Spring New!");
	}

}
