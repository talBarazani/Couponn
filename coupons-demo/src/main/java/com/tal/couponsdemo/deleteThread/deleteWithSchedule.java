package com.tal.couponsdemo.deleteThread;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tal.couponsdemo.CouponsDemoApplication;
import com.tal.couponsdemo.entities.Coupon;
import com.tal.couponsdemo.reposotories.CouponReposotory;

@Component
public class deleteWithSchedule {
	@Autowired
	private CouponReposotory couponReposotory;
	
	private static final Logger logger = LoggerFactory.getLogger(deleteWithSchedule.class);


	@Scheduled(cron = "1 0 0 * * *")
	public void deleteExpired(){
		logger.info("delete theard is in started");
		Calendar cal = Calendar.getInstance();
		Date currDate=cal.getTime();
		List<Coupon> expiredCoupons = couponReposotory.findAllCoupnosEndDateLessThan(currDate); 
		for (Coupon coupon : expiredCoupons) {
			couponReposotory.delete(coupon);
			logger.info(coupon.toString()  + "has deleted");
		}
	}

}
