package com.tal.couponsdemo.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tal.couponsdemo.entities.Client;
import com.tal.couponsdemo.entities.Company;
import com.tal.couponsdemo.entities.Coupon;
import com.tal.couponsdemo.entities.Customer;
import com.tal.couponsdemo.entities.GeneralResponse;
import com.tal.couponsdemo.services.CustomerService;

@RestController
@RequestMapping("rest/api/customer")
public class CusomerControlller {

	private final CustomerService customerService;
	
	public CusomerControlller(CustomerService customerService) {
		this.customerService = customerService;
	}

	
	@PutMapping("{id}/coupon{couponId}")
	public Object pruchaseCoupon(@PathVariable ("id") long id, @PathVariable ("couponId") long couponId) {
		return customerService.pruchaseCoupon(id, couponId);
	}
	@GetMapping("{id}/coupon")
	public Object findAllCouponsOfCustomer(@PathVariable("id") long id){
		return customerService.getCouponsOfCustomer(id);
	}
	@GetMapping("{id}/coupon/type/{type}")
	public Set<Coupon> findAllCouponsByType(@PathVariable("id") long id,@PathVariable("type") String type ){
		return customerService.getItemsFromCustomerByType(id, type.toUpperCase());
	}
	@GetMapping("{id}/coupon/{underprice}")
	public Set<Coupon> findAllCouponsUnderPrice(@PathVariable("id") long id, @PathVariable("underprice") double underPrice){
		return customerService.findAllCustomerCouponsUnderPrice(id,underPrice);
	}
	@PostMapping("/login/{email}/{password}")
	public Object login(@PathVariable ("password") String password, @PathVariable("email") String email ) {
		return (Customer) customerService.login(email, password);
	}
	@GetMapping("/logout")
	public boolean logout(@RequestHeader(value = "Authorization") String userName) {
		return customerService.remove(userName);
	}
	
	
}
