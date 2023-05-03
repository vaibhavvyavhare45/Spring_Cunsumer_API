package com.prowings.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.prowings.model.Customer;
import com.prowings.service.ServiceConsumer;

@RestController
@RequestMapping("/consumer-api")
public class CustomerConsumerInvoker {
	@Autowired
	ServiceConsumer service;

	@GetMapping("/{id}")
	public Customer callCustomerById(@PathVariable("id") int id) throws RestClientException, URISyntaxException {
		return service.callCustomerById(id);
	}

	@PostMapping
	public Customer callSaveCustomer(@RequestBody Customer customer) throws URISyntaxException {
		return service.callSaveCustomer(customer);
	}
	
	@GetMapping("/allCustomer")
	public List<Customer> callByGetAllCustomer() throws RestClientException, URISyntaxException{
		return service.callByGetAllCustomer();
		
	}
	
	@PutMapping("/update-customer")
	public ResponseEntity<String> callUpdateCustomer(@RequestBody Customer customer) throws RestClientException, URISyntaxException{
		try {
			service.callUpdateCustomer(customer);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> callDeleteCustomer(@PathVariable int id) throws URISyntaxException{
		try {
			service.callDeleteCustomer(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (RuntimeException e) {
			System.out.println(e.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	

}
