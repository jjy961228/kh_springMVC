package org.kh.customer.main;

import java.util.List;

import org.kh.customer.domain.Customer;
import org.kh.customer.service.CustomerService;
import org.kh.customer.service.logic.CustomerServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CustomerRun {

	public static void main(String[] args) {
		//Spring-context.xml의 setter메서드,생성자 이용해 의존성 주입한걸 출력한다(customer)
		//ApplicationContext:  bean들의 공장이다: bean이 모두 모여있다
		ApplicationContext ctx = new GenericXmlApplicationContext("spring-context.xml"); //("spring-context.xml") : 파일명
		Customer customer = (Customer)ctx.getBean("customer"); // "customer" : bean태그의 id값
		System.out.println(customer.toString());
		System.out.println();
		
		//CustomerServiceImpl을 꺼내기 위해서는
		//getBean의 전달값:spring-context.xml 에서 bean의 id값을 넣어줘야한다
		CustomerService cService
			=(CustomerServiceImpl)ctx.getBean("customerService");
		List<Customer> list = cService.findAll();
		for(Customer cOne : list) {
			System.out.println(cOne.toString());
		}
		
	}

}
