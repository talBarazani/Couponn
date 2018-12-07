package com.tal.couponsdemo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;


import com.tal.couponsdemo.entities.Company;
import com.tal.couponsdemo.entities.Coupon;

public interface CompanyService extends CouponClinentServeice{


	Optional<Company> findCompanyById(long id);

	Coupon createCoupon(long id, Coupon newCoupon);

	List<Coupon> findAllCouponsOfCompany(long id);

	boolean deleteCouponById(long couponId, long companyId);

	Coupon updateCoupon(long id,long couponId, Date date, double price);

	List<Coupon> findAllCouponsEndDateLessthan(long id, Date endDate);

	List<Coupon> findAllCouponsUnderPrice(long id, double price);

	List<Coupon> findAllCouponsByType(long id, String couponType);

	Company findCompanyByEmail(String email);

}