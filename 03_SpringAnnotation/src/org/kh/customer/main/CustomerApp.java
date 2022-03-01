package org.kh.customer.main;

import java.util.List;

import org.kh.customer.domian.Customer;
import org.kh.customer.service.CustomerService;
import org.kh.customer.service.logic.CustomerServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CustomerApp {
	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext("spring-context.xml"); //bean factory: 빈 등록한게 모두 들어가있음
		CustomerService cService =(CustomerServiceImpl)ctx.getBean("customerService"); //bean등록시 어노테이션으로 등록한 id값
		List<Customer> list = cService.findAll();
		for(Customer customer : list) {
			System.out.println(customer.toString());
		}
		
		
		
	}
}
