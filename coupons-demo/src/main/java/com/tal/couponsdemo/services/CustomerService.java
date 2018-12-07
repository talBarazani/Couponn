package com.tal.couponsdemo.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;


import com.tal.couponsdemo.entities.Coupon;
import com.tal.couponsdemo.entities.Customer;

public interface CustomerService extends CouponClinentServeice{

	List<Customer> findAllCustomer();

	Optional<Customer> findCustomerById(long id);

	Customer createCustomer(Customer newCustomer);

	boolean deleteCustomerById(long id);

	Coupon pruchaseCoupon(long id, long couponId);

	Set<Coupon> findAllCustomerCouponsUnderPrice(long id, double underPrice);

	Set<Coupon> getItemsFromCustomerByType(long id, String type);

	Set<Coupon> getCouponsOfCustomer(long id);

}