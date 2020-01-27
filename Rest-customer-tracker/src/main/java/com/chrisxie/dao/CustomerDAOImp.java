package com.chrisxie.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chrisxie.entity.Customer;

@Repository
public class CustomerDAOImp implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Customer> getCustomerList() {

		Session session = sessionFactory.getCurrentSession();
		
		List<Customer> customerList = new LinkedList<>(); 
		
		customerList = session.createQuery("from Customer order by lastName").getResultList();
		
		return customerList;
	}


	@Override
	public Customer getCustomerById(int customerId) {

		Session session = sessionFactory.getCurrentSession();
		
		return session.get(Customer.class, customerId);
	}


	@Override
	public void saveCustomer(Customer customerToSave) {

		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(customerToSave);
		
		
	}


	@Override
	public void deleteCustomerById(int customerId) {

		Session session = sessionFactory.getCurrentSession();
		
		session.delete(session.get(Customer.class, customerId));
		
	}

}
