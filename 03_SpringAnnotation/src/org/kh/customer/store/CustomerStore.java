package org.kh.customer.store;

import java.util.List;

import org.kh.customer.domian.Customer;

public interface CustomerStore {
	//인터페이스 : 추상메서드 필요
	//인터페이스 사용 이유 : 전체정보조회 역할은 selectAll로 쓸수있게 메서드의 이름들을 정해준다~
	abstract List<Customer> selectAll();
	
}
