package com.tal.couponsdemo.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tal.couponsdemo.CouponsDemoApplication;
import com.tal.couponsdemo.entities.Company;
import com.tal.couponsdemo.entities.Customer;
import com.tal.couponsdemo.services.AdminService;

/*
	this is the admin contoller , in this class i declared all of the admin task for connection with the client and service, this bean will handle the admin rest
	request, it will declare the rest url, parameters and body, and handle it to pass it to the service..
	this methods will start when ever they get the rest request.
*/
@RestController
@RequestMapping("/rest/api/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	// i will inject the admin service in the constructor
	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	 
	// this method will start the creation of the company, it will get a company instance in a json, will parse it to a java object and send it to the admin service
	@PostMapping("/company")
	public Object createCompany(@RequestBody Company newCompany) {
		logger.info("send to service to create " + newCompany.toString());
		return adminService.createCompany(newCompany);
	}
	
	//this method will start the delete of the compnay, it will get the company id in the url as a path variable, the method will parse it to a a java long primtive and send it to the admin service
	@DeleteMapping("/company/{id}")
	public Object deleteCompany(@PathVariable("id") long id) {
		logger.info("send to the service id:" + id +"for deleting" );
		return adminService.deleteCompanyById(id);
	}

	// this method will start the change of the company, it will get a company instance in a json, will parse it to a java object and send it to the admin service
	@PutMapping("/company")
	public Object updateCompany(@RequestBody Company updatedCompany) {
		logger.info("send to service" +updatedCompany.toString());
		return adminService.updateCompany(updatedCompany);
	}
	
	//this method simply get the the call to show all companies, it will just pass it to the admin service
	@GetMapping("/company")
	public Object findAllCompanies() {
		return adminService.findAllCompanies();
	}
	
	//this method simply get an id parse it to long primitve and send it the admin service
	@GetMapping("/company/{id}")
	public Object findCompanyById(@PathVariable("id") long id) {
		logger.info("send to service id:" + id + "for delete");
		return adminService.findCompanyById(id);
	}
	
	// this method will start the creation of the customer, it will get a customer instance in a json, will parse it to a java object and send it to the admin service
	@PostMapping("/customer")
	public Object createcustomer(@RequestBody Customer newCustomer) {
		logger.info("send to service to create-" + newCustomer.toString());
		return adminService.createCustomer(newCustomer);
	}
	//this method will start the delete of the customer, it will get the customer id in the url as a path variable, the method will parse it to a a java long primtive and send it to the admin service
	@DeleteMapping("/customer/{id}")
	public Object deleteCustomer(@PathVariable("id") long id) {
		logger.info("send to the service customer id:" + id +"for deleting" );
		return adminService.deleteCustomer(id);
	}
	
	// this method will start the change of the customer, it will get a customer instance in a json, will parse it to a java object and send it to the admin service
	@PutMapping("/customer/{id}")
	public Object updateCustomer(@RequestBody Customer updatedCustomer) {
		logger.info("send to service customer " +updatedCustomer.toString());
		return adminService.updateCustomer(updatedCustomer);
	}
	
	//
	@GetMapping("/customer")
	public Object findAllCustomer() {
		return adminService.findAllCustomer();
	}

	@GetMapping("/customer/{id}")
	public Object findCustomerById(@PathVariable("id") long id) {
		return adminService.findCustomerById(id);
	}
	@PostMapping("/login/{email}/{password}")
	public Object login(@PathVariable ("password") String password, @PathVariable("email") String email ) {
		logger.info("data send: email:" + email + ", password:" + password);
		return (Company) adminService.login(email, password);
	}

}
