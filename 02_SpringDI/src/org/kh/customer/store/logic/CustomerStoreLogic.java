package org.kh.customer.store.logic;

import java.util.ArrayList;
import java.util.List;

import org.kh.customer.domain.Customer;
import org.kh.customer.store.CustomerStore;

public class CustomerStoreLogic implements CustomerStore {

	@Override
	public List<Customer> selectAll() {
		List<Customer> cList = new ArrayList<Customer>();
		for(int i = 0; i<10; i++) {
			Customer cOne = new Customer();
			cOne.setId(i+ "");
			cOne.setName(i+ "");
			cOne.setAddress(String.valueOf(i)); //숫자 -> 문자로 바꿔준다
			cOne.setEmail(new StringBuffer().append(i).toString()); //숫자 -> 문자로 바꿔준다
			cList.add(cOne);
		}
		return cList;
	}

}