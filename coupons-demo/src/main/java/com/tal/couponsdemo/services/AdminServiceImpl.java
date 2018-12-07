package com.tal.couponsdemo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tal.couponsdemo.aspect.ResponseAspect;
import com.tal.couponsdemo.entities.Client;
import com.tal.couponsdemo.entities.Company;
import com.tal.couponsdemo.entities.Customer;
import com.tal.couponsdemo.reposotories.CompanyReposotory;
import com.tal.couponsdemo.reposotories.CustomerReposotory;

/*
 this is the admin service implemntaition, in this class i declared all of the admin bussines logic, this bean will handle the admin methods,
 there are methos that just pass the data the controllers gets to the reposotories whithout any handling.
 */

@Service
public class AdminServiceImpl implements AdminService,CouponClinentServeice {
	//these repostories are injected to this service in constracture declration, they will be the connection to the DB!.
	private final CompanyReposotory companyReposotory;
	private final CustomerReposotory customerReposotory;
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);


	public AdminServiceImpl(CompanyReposotory companyReposotory, CustomerReposotory customerReposotry) {
		this.companyReposotory = companyReposotory;
		this.customerReposotory = customerReposotry;
	}

	//this method create a company by sending it to the companyReposotory, it does nothing but sending the parameters it gets to the repo.
	@Override
	@Transactional
	public Company createCompany(Company newCompany) {
		logger.info("send " + newCompany.toString() + "to company reposotory");
		companyReposotory.save(newCompany);
		return newCompany;
	}

	//this method delete a company by sending the company id to the companyReposotory, its first check if there any company for the id it gets. .
	@Override
	@Transactional
	public boolean deleteCompanyById(long id) {
		if (companyReposotory.findById(id) == null) {
			logger.info("No company for the id" + id);
			return false;
		} else {
			logger.info("send id" + id + "to company reposotry");
			companyReposotory.deleteById(id);
			return true;
		}
	}
	
	/* this method update company by getting a company object, checking for the company it should update,
	 *  if the id is wrong it will throw exp, else, it will change the passowrd and email.
	*/  
	@Override
	@Transactional
	public Company updateCompany(Company updatedCompany) {
		Company companyToUpdate = companyReposotory.findById(updatedCompany.getId()).orElse(null);
		if (companyToUpdate == null)  throw new RuntimeException("No company for the id" + updatedCompany.getId());
		logger.info("send changes to reposotory");
		companyToUpdate.setEmail(updatedCompany.getEmail());
		companyToUpdate.setPassword(updatedCompany.getPassword());
		return companyToUpdate;
	}
	
	//this method get all companies by sending it to the companyReposotory, it does nothing but sending the parameters it gets to the repo.
	@Override
	public List<Company> findAllCompanies(){
		logger.info("get all companies from the reposotory.. ");
		return companyReposotory.findAll();
	}
	@Override
	public Optional<Company> findCompanyById(long id) {
		logger.info("send id to the reposotory");
		return companyReposotory.findById(id);
	}
	@Override
	@Transactional
	public Customer createCustomer(Customer newCustomer) {
		logger.info("pass the customer to the reposotory");
		return customerReposotory.save(newCustomer);
	}
	@Override
	@Transactional
	public boolean deleteCustomer(long id) {
		if(customerReposotory.findById(id) == null) { 
			logger.info("No company for the id" + id);
			return false;
		}else {
			logger.info("send id" + id + "to customer reposotry");
			customerReposotory.deleteById(id);
			return true;
		}
	}
	
	//the same as the updateCompany method.....
	@Override
	@Transactional
	public Customer updateCustomer(Customer updatedCustomer) {
		Customer customerToUpdate = customerReposotory.findById(updatedCustomer.getId()).orElse(null);
		if (customerToUpdate == null) throw new RuntimeException("No customer for the id" + updatedCustomer.getId());
		logger.info("send changes to reposotory");
		customerToUpdate.setEmail(updatedCustomer.getEmail());
		customerToUpdate.setPassword(updatedCustomer.getPassword());
		return customerToUpdate;
	}
	
	@Override
	public List<Customer> findAllCustomer(){
		logger.info("get all customers from the reposotory.. ");
		return customerReposotory.findAll();
	}
	
	@Override
	public Optional<Customer> findCustomerById(long id){
		return customerReposotory.findById(id);
	}
	
	
	@Override
	public Client login(String email, String password) {
		if(email.equals("admin") && password.equals("1234")) return new Company();
		else throw new RuntimeException("the passowrd or the email arent correct");
	}

	@Override
	public Client get(String email) {
		return null;
	}

	@Override
	public boolean remove(String email) {
		return false;
	}

}
