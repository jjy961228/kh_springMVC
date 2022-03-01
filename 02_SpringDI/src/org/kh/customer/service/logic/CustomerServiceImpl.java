package org.kh.customer.service.logic;

import java.util.List;

import org.kh.customer.domain.Customer;
import org.kh.customer.service.CustomerService;
import org.kh.customer.store.CustomerStore;
//CustomerServiceImpl 은 CustomerService를 상속받아 만들어졌고,
// 		다시, CustomerServiceImpl과 CustomerStore와 관계를 맺는 모양이다
// 기존의방식 -> DI방식(Interface로 연결)로 하면 약한 결합이 된다
public class CustomerServiceImpl implements CustomerService {
		private CustomerStore cStore; 		
		
		// new해서 만드는건 bean(spring container)이 알아서 해준다	
		//생성자를 이용한 의존성 주입
		public CustomerServiceImpl() { //매개변수 있는 생성자 쓰니까 기본생성자 
		} 
		
		public CustomerServiceImpl(CustomerStore cStore) { // 매개변수 있는 생성자를 이용한
			this.cStore = cStore; // 의존성 주입(DI)
		} // constructor-arg, ref를 사용해서 의존성 주입을 함.
		
	//setStore를 이용해서 CustomerServiceImpl에 의존성 주입(DI):
	//set메서드를 이용해 CustomerServiceImpl<-  customerStore객체의 의존성이 주입됨
		public void setStore(CustomerStore cStore) {
			this.cStore = cStore;
		}

		
		@Override
		public List<Customer> findAll() {
			List<Customer> cList = cStore.selectAll();
			return cList;
		}
		 


}
/*
 * CustomerServiceImpl과 CustomerStore와 관계를 맺기 
 * 기존의 방식) 강한결합이다 (CustomerServiceLogic가 수정되면 CustomerServiceImpl도 수정되어야한다)
 * private CustomerStoreLogic cStore; 변수선언 
 * public CustomerServiceImpl() {	생성자를 이용해 객체 생성
			cStore = new CustomerStoreLogic();
		}
   public List<Customer> findAll() {
			List<Customer> cList = cStore.selectAll();
			return cList;
		}
 * 
 * 
 * */

