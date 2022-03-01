package org.kh.customer.service;

import java.util.List;

import org.kh.customer.domian.Customer;

public interface CustomerService {
	//추상메서드
	abstract public List<Customer> findAll();

}
