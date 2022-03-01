package org.kh.customer.store;

import java.util.List;

import org.kh.customer.domain.Customer;

public interface CustomerStore {
	//인터페이스에 적는건? 추상메서드 -> 오버라이딩
	public List<Customer> selectAll();
}