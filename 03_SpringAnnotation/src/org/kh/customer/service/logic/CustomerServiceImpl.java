package org.kh.customer.service.logic;

import java.util.List;

import org.kh.customer.domian.Customer;
import org.kh.customer.service.CustomerService;
import org.kh.customer.store.CustomerStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//1) 어노테이션 빈 등록
@Component("customerService") //bean등록시의 id값설정
public class CustomerServiceImpl implements CustomerService  {
	
	//2) 어노테이션 의존성 주입(DI)
	@Autowired
	private CustomerStore cStore;
	
	@Override
	public List<Customer> findAll() {
		
		return cStore.selectAll();
	}



}
