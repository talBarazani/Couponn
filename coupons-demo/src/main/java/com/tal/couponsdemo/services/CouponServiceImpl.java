package com.tal.couponsdemo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tal.couponsdemo.converters.CouponTypeConverter;
import com.tal.couponsdemo.entities.Coupon;
import com.tal.couponsdemo.entities.CouponType;
import com.tal.couponsdemo.reposotories.CompanyReposotory;
import com.tal.couponsdemo.reposotories.CouponReposotory;

//updateCoupon method is not working
@Service
public class CouponServiceImpl implements CouponService {
	
	@Autowired
	private CouponReposotory couponReposotory;
	@Autowired
	private CouponTypeConverter couopnTypeConverter;
	
	@Override
	public List<Coupon> findAllCoupons(){
		return couponReposotory.findAll();
	}
	@Override
	public Optional<Coupon> findCouponById(long id){
			return couponReposotory.findById(id);
	}
	@Override
	@Transactional
	public Coupon createCoupon(Coupon newCoupon) {
		couponReposotory.save(newCoupon);
		return newCoupon;
	}
	@Override
	@Transactional
	public boolean deleteCouponById(long id) {
		if(couponReposotory.findById(id) == null) {
			return false;
		}else {
			couponReposotory.deleteById(id);
			return true;
		}
	}
	@Override
	@Transactional
	public Coupon updateCoupn(Coupon updatedCoupon) {
		long id = updatedCoupon.getId();
		if(couponReposotory.findById(id) != null) {
			Coupon oldCoupon = couponReposotory.getOne(id);
			oldCoupon = updatedCoupon;
			return oldCoupon;
		}else {
			couponReposotory.save(updatedCoupon);
			return updatedCoupon;
		}
	}
	@Override
	public List<Coupon> findByType(String typeInString){
		CouponType type = couopnTypeConverter.convertToEntityAttribute(typeInString);
		return couponReposotory.findByType(type);
		
	}
	@Override
	public List<Coupon> findAllOrderByDate() {
		return couponReposotory.findAllByOrderByStartDateAsc();
	}
	
	@Override
	public List<Coupon> findAllCoupnosEndDateLess(Date endDate){
		return couponReposotory.findAllCoupnosEndDateLessThan(endDate);
	}

}
