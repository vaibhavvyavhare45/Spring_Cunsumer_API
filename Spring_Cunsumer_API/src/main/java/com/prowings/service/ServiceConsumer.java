  package com.prowings.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.prowings.model.Customer;

@Service
public class ServiceConsumer {
	RestTemplate template= new RestTemplate();
	@Value("${baseUrl}")
	String url;
	
	public Customer callCustomerById(int id) throws RestClientException, URISyntaxException {
		
		String uri=url+id;
		return template.getForObject(new URI(uri), Customer.class);
	}

	public Customer callSaveCustomer(Customer customer) throws RestClientException, URISyntaxException {
		// TODO Auto-generated method stub
		return template.postForObject(new URI(url), customer, Customer.class);
	}
	
	
	

	public void callUpdateCustomer(Customer customer) throws RestClientException, URISyntaxException {
		
		template.put(url, customer, Customer.class);
		
	}

	public List<Customer> callByGetAllCustomer() throws RestClientException,URISyntaxException {
		
//		ResponseEntity<Customer[]> entity=template.getForEntity(new URI(url),Customer[].class);
		
//		Object[] obj=entity.getBody();
//      List la= Arrays.asList(obj);//or other way
		
//		List ls=Arrays.asList(entity.getBody());	
		
		//other way use
		
		List<Customer> l=Arrays.asList(template.getForObject(new URI(url), Customer[].class));
		return l;
	}

	public void callDeleteCustomer(int id) throws RestClientException, URISyntaxException {
		String uri=url+id;
		template.delete(uri,Integer.toString(id));
	}

	

	
	

	
}
