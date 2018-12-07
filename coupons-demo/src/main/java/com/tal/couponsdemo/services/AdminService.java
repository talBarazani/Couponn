package com.tal.couponsdemo.services;

import java.util.List;
import java.util.Optional;


import com.tal.couponsdemo.entities.Company;
import com.tal.couponsdemo.entities.Customer;

public interface AdminService {

	Company createCompany(Company newCompany);

	boolean deleteCompanyById(long id);

	Company updateCompany(Company updatedCompany);

	List<Company> findAllCompanies();

	Optional<Company> findCompanyById(long id);

	Customer createCustomer(Customer newCustomer);

	boolean deleteCustomer(long id);

	Customer updateCustomer(Customer updatedCustomer);

	List<Customer> findAllCustomer();

	Optional<Customer> findCustomerById(long id);

	Object login(String email, String password);

}