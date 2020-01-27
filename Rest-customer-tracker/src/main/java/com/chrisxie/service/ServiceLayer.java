package com.chrisxie.service;

import java.util.List;

import com.chrisxie.entity.Customer;

public interface ServiceLayer {

	public List<Customer> getCustomerList();
	
	public Customer getCustomerById(int customerId);
	
	public void saveCustomer(Customer customerToSave);
	
	public void deleteCustomerById(int customerId);
	
}
