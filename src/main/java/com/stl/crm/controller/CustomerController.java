package com.stl.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stl.crm.domain.Customer;
import com.stl.crm.security.CrmUserDetails;
import com.stl.crm.security.CustomUserDetails;
import com.stl.crm.service.CustomerService;

@RestController
@RequestMapping("/api/")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

    /**
     * 
     * this method maps the following URL & http method
     * URL: http://hostname:port/crm-oauth2/api/customers
     * HTTP method: GET
     * 
     */	
	@RequestMapping(value="/customers", method = RequestMethod.GET)
	public ResponseEntity<?> getCustomers() {

		/**
		 * Obtaining information about the current user
		 */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CrmUserDetails principal = (CrmUserDetails) authentication.getPrincipal();
        System.out.println("logged in user name:: " + principal.getUsername());
		
		Iterable<Customer> customerList = customerService.getCustomers();
		return new ResponseEntity<>(customerList, HttpStatus.OK);
	}

    /**
     * 
     * this method maps the following URL & http method
     * URL: http://hostname:port/crm-oauth2/api/customers/{customerId}
     * HTTP method: GET
     * 
     */		
	@RequestMapping(value="/customers/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<?> getCustomer(@PathVariable long customerId) {
		Customer customer = customerService.getCustomer(customerId);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}	

    /**
     * 
     * this method maps the following URL & http method
     * URL: http://hostname:port/crm-oauth2/api/customers
     * HTTP method: POST
     * 
     */		
	@RequestMapping(value="/customers", method = RequestMethod.POST)
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		Customer newCustomer = customerService.addCustomer(customer);
		return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
	}
	
    /**
     * 
     * this method maps the following URL & http method
     * URL: http://hostname:port/crm-oauth2/api/customers/customerId
     * HTTP method: PUT
     * 
     */	
    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCustomer(@PathVariable long customerId, 
    										@RequestBody Customer customer) {
    	Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
    	return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }
	
    /**
     * 
     * this method maps the following URL & http method
     * URL: http://hostname:port/crm-oauth2/api/customers/customerId
     * HTTP method: DELETE
     * 
     */
    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteCustomer(@PathVariable long customerId) {
    	Customer customer = customerService.getCustomer(customerId);
    	customerService.deleteCustomer(customer);
    	return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 
     * this method maps the following URL & http method
     * URL: http://hostname:port/appName
     * HTTP method: GET
     * 
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> home() {
    	return new ResponseEntity<>("CRM REST API, JPA, Spring Security, and OAuth2", HttpStatus.OK);
    }
  
}
