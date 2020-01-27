package com.chrisxie.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chrisxie.entity.Customer;
import com.chrisxie.service.ServiceLayer;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	@Autowired
	private ServiceLayer serviceLayer;
	
	//get all customers
	@GetMapping("/customers")
	public List<Customer> getCustomerList(){
		
		
		return serviceLayer.getCustomerList();
		
		
	}
	
	//get a single customer by id
	@GetMapping("/customers/{customerId}")
	public Customer getCustomerById(@PathVariable int customerId){
		
		Customer customer = serviceLayer.getCustomerById(customerId);
		
		if (customer == null) {
			
			throw new CustomerNotFoundException("Customer id not found!!! Customer id -> " + customerId);
		}
		
		
		return customer;
		
		
	}
	
	
	//add a new customer
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customertoAdd) {
		
		customertoAdd.setId(0);	//setting the id to 0 or null will make Hibernate do the insertion every time
		
		serviceLayer.saveCustomer(customertoAdd);
		
		return customertoAdd;
		
	}
	
	//update a customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customerToUodate) {
		
		serviceLayer.saveCustomer(customerToUodate);
		
		return customerToUodate;
	}
	
	//delete a customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		Customer customerToDelete = serviceLayer.getCustomerById(customerId);
		
		if (customerToDelete == null) {
			
			throw new CustomerNotFoundException("The customer could not be deleted because customer id not found! Customer id -> " + customerId);
		}
		
		serviceLayer.deleteCustomerById(customerId);
		
		return "Deleted customer: Customer id -> " + customerId;
		
	}
	
	
	
}
