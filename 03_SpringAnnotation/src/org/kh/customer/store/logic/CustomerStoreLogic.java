package org.kh.customer.store.logic;

import java.util.ArrayList;
import java.util.List;

import org.kh.customer.domian.Customer;
import org.kh.customer.store.CustomerStore;
import org.springframework.stereotype.Component;

//어노테이션 빈 등록
@Component("customerStore") //bean등록시의 id값설정
public class CustomerStoreLogic implements CustomerStore {

	@Override
	public List<Customer> selectAll() {
		List<Customer> list = new ArrayList<Customer>();
		for(int i = 0; i<10; i++) {
			Customer cOne = new Customer();
			cOne.setId(i + " ");
			cOne.setName(String.valueOf(i)); //int -> String으로 바꾸는 방법
			cOne.setAddress(i + " ");
			cOne.setEmail(new StringBuilder().append(i).toString()); //숫자 -> 문지로 바꾸는 방법
			list.add(cOne);		
		}
		
		return list;
	}

}
