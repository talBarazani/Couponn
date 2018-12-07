package com.tal.couponsdemo.entities;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;



public class CouponUpdateInfo {
	@Temporal(TemporalType.DATE)
	public Date newEndDate;
	public Double newPrice;
	

	
	
	
	
}
