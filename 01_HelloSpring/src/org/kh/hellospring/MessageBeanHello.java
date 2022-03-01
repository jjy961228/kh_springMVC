package org.kh.hellospring;

public class MessageBeanHello implements MessageBean {
	//부모타입: MessageBean , 자식타입: MessageBeanHello
	 
	//인터페이스(MessageBean) 을 상속받아서 MessageBeanHello을 만들었다
	@Override
	public void sayHello(String name) {
		System.out.println("name을 받아서 출력");
		System.out.println("Hello" + name);
		
	}
	
}
