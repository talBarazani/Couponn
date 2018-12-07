package com.tal.couponsdemo.services;

import com.tal.couponsdemo.entities.Client;

public interface CouponClinentServeice {
	public Client login(String email, String password);
	
	Client get(String email);

	boolean remove(String email);

}
