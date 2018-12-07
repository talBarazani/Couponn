package com.tal.couponsdemo.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tal.couponsdemo.entities.Coupon;
import com.tal.couponsdemo.services.CouponService;

@RestController
@RequestMapping("rest/api/coupon")

public class CouponController {
	

	private final CouponService couponService;
	private static final Logger logger = LoggerFactory.getLogger(CouponController.class);
		
	
	public CouponController(CouponService couponService) {
		this.couponService = couponService;
	}

	@GetMapping
	public Object findAll(){
		return couponService.findAllCoupons();
	}
	
	@GetMapping("{id}")
	public Object findById(@PathVariable("id") long id){
		return couponService.findCouponById(id);
	}
	
	@PostMapping
	public Coupon crateCoupon(@RequestBody Coupon newCoupon) {
		return couponService.createCoupon(newCoupon);
	}
	
	@DeleteMapping("{id}")
	public boolean deleteById(@PathVariable("id") long id ) {
		return couponService.deleteCouponById(id);
	}
	
	@PutMapping
	public Coupon updateCoupon(@RequestBody Coupon updatedCoupon) {
		return couponService.updateCoupn(updatedCoupon);
	}
	@GetMapping("endDateLess/{endDate}")
	public List<Coupon> findAllCoupnosEndDateLess(@PathVariable("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate){
		return couponService.findAllCoupnosEndDateLess(endDate);
	}
	@GetMapping("/type/{type}")
	public Object findByType(@PathVariable("type") String type) {
		return couponService.findByType(type);
	}
	@GetMapping("/newCoupon")
	public Object findAllOrderByDate() {
		logger.info("send to");
		return couponService.findAllOrderByDate();
	}
	
	

}
