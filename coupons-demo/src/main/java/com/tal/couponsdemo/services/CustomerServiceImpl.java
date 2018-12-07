package com.tal.couponsdemo.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tal.couponsdemo.entities.Client;
import com.tal.couponsdemo.entities.Company;
import com.tal.couponsdemo.entities.Coupon;
import com.tal.couponsdemo.entities.Customer;
import com.tal.couponsdemo.reposotories.CouponReposotory;
import com.tal.couponsdemo.reposotories.CustomerReposotory;

@Service
public class CustomerServiceImpl implements CustomerService, CouponClinentServeice {

	private final CustomerReposotory customerReposotory;
	private final CouponReposotory couponReposotory;
	private RestTemplate restTemplate;
	private HashMap<String, Customer> cache;

	public CustomerServiceImpl(CustomerReposotory customerReposotory, CouponReposotory couponReposotory) {
		this.customerReposotory = customerReposotory;
		this.couponReposotory = couponReposotory;
	}

	@PostConstruct
	public void initialize() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public List<Customer> findAllCustomer() {
		return customerReposotory.findAll();
	}

	@Override
	public Optional<Customer> findCustomerById(long id) {
		return customerReposotory.findById(id);
	}

	@Override
	@Transactional
	public Customer createCustomer(Customer newCustomer) {
		customerReposotory.save(newCustomer);
		return newCustomer;
	}

	@Override
	@Transactional
	public boolean deleteCustomerById(long id) {
		if (customerReposotory.findById(id) == null) {
			return false;
		} else {
			customerReposotory.deleteById(id);
			return true;
		}
	}

	@Transactional
	@Override
	public Coupon pruchaseCoupon(long id, long couponId) {
		Coupon coupon = couponReposotory.findById(couponId).get();
		Customer customer = customerReposotory.findById(id).get();
		Set<Coupon> coupons = customer.getCoupons();
		if(!coupons.contains(coupon)) {
			coupons.add(coupon);
			customer.setCoupons(coupons);
			customerReposotory.save(customer);
			restTemplate.postForObject("http://localhost:8888/rest/api/income/customer/"+customer.getId().toString()+ "/" + customer.getName(),coupon,
					Void.class);
			return coupon;	
		}else throw new UnsupportedOperationException("You Cant pruchase the same coupon more the once");
		
	}

	@Override
	public Set<Coupon> getCouponsOfCustomer(long id) {
		return customerReposotory.findById(id).get().getCoupons();
	}

	@Override
	public Set<Coupon> getItemsFromCustomerByType(long id, String type) {
		return couponReposotory.getItemsFromCustomerByType(id, type);
	}

	@Override
	public Set<Coupon> findAllCustomerCouponsUnderPrice(long id, double underPrice) {
		return couponReposotory.findAllCustomerCouponsUnderPrice(id, underPrice);
	}

	@Override
	public Client login(String email, String password) {
		Customer customer = customerReposotory.findByEmail(email).orElse(null);
		if(customer == null) throw new RuntimeException("User is not exist");
		if(!customer.getPassword().equals(password)) throw new RuntimeException("Wrong details");
		return customer;
	}

	@Override
	public Client get(String email) {
		Customer customer = cache.get(email);
		return customer;
	}

	@Override
	public boolean remove(String email) {
		cache.remove(email);
		return true;
	}

}
