package com.tal.couponsdemo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class CouponsDemoApplication {
	private static final Logger logger = LoggerFactory.getLogger(CouponsDemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(CouponsDemoApplication.class, args);
		logger.info("--Application Started--");
	}
}
