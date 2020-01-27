package com.chrisxie.dao;

import java.util.List;

import com.chrisxie.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomerList();
	
	public Customer getCustomerById(int customerId);
	
	public void saveCustomer(Customer customerToSave);
	
	public void deleteCustomerById(int customerId); 
}
