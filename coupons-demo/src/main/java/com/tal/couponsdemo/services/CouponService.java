package com.tal.couponsdemo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.tal.couponsdemo.entities.Coupon;

public interface CouponService {

	List<Coupon> findAllCoupons();

	Optional<Coupon> findCouponById(long id);

	Coupon createCoupon(Coupon newCoupon);

	boolean deleteCouponById(long id);

	Coupon updateCoupn(Coupon updatedCoupon);

	List<Coupon> findAllCoupnosEndDateLess(Date endDate);

	List<Coupon> findByType(String typeInString);

	List<Coupon> findAllOrderByDate();

}