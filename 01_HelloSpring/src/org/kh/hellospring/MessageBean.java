package org.kh.hellospring;

public interface MessageBean {
	//무엇을 작성? -> 추상 메서드 작성({} 몸체없는 메서드)
	//					추상메서드는 반드시 오바이딩 해야한다
	abstract public void sayHello(String name);
}
