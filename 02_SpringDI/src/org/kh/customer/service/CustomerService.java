package org.kh.customer.service;

import java.util.List;

import org.kh.customer.domain.Customer;

public interface CustomerService {
	//인터페이스에 적는건? 추상메서드 -> 상속받는애가 반드시 오버라이딩 해야한다
	public List<Customer> findAll();
}
