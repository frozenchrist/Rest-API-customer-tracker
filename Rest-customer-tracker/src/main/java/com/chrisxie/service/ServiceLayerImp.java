package com.chrisxie.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrisxie.dao.CustomerDAO;
import com.chrisxie.entity.Customer;

@Service
public class ServiceLayerImp implements ServiceLayer {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomerList() {
		// TODO Auto-generated method stub
		return customerDAO.getCustomerList();
	}

	@Override
	@Transactional
	public Customer getCustomerById(int customerId) {
		// TODO Auto-generated method stub
		return customerDAO.getCustomerById(customerId);
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customerToSave) {

		customerDAO.saveCustomer(customerToSave);
	}

	@Override
	@Transactional
	public void deleteCustomerById(int customerId) {
	
			customerDAO.deleteCustomerById(customerId);
	}

}
