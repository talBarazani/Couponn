package com.tal.couponsdemo.controllers;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tal.couponsdemo.CouponsDemoApplication;
import com.tal.couponsdemo.entities.Client;
import com.tal.couponsdemo.entities.Company;
import com.tal.couponsdemo.entities.Coupon;
import com.tal.couponsdemo.entities.Customer;
import com.tal.couponsdemo.services.CompanyService;


/*
this is the company contoller , in this class i declared all of the company tasks for connection with the client and service, this bean will handle the company rest
request, it will declare the rest url, parameters and body, and handle it to pass it to the service..
this methods will triggered by the client when it execute the rest url.
*/
@RestController
@RequestMapping("/rest/api/company")
public class CompanyController {
	//these services are injected to this bean in constructor declration, they will be the connection to the service layer!.
	private final CompanyService companyService;
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	
	// this method will start the creation of the coupon, it will get a coupon instance in a json, will parse it to a java object and send it to the admin service
	@PostMapping("{id}/coupon")
	public Object createCoupon(@PathVariable("id") long id,@RequestBody Coupon newCoupon) {
		logger.info("send to coupon to service:" + newCoupon.toString() );
		return companyService.createCoupon(id, newCoupon);
	}
	//this method will get send id to the service, it will get the list of coupons of that company
	@GetMapping("{id}/coupon")
	public Object findAllCouponsOfCompany(@PathVariable("id") long id){
		logger.info("send to service company id:" + id);
		return companyService.findAllCouponsOfCompany(id);
	}
	//this method will start the delete of the coupon, it will get the coupon id in the url as a path variable, the method will parse it to a a java long primtive and send it to the company service
	@DeleteMapping("{id}/coupon/{couponId}")
	public Object deleteCouponById(@PathVariable("id") long id ,@PathVariable("couponId") long couponId) {
		logger.info("send to the service companyId:" + id +", and coupon id:"+couponId +" for deleting" );
		return companyService.deleteCouponById(id, couponId);
	}
	
	// this method will start the change of the coupon, it will get a date and price as a path variable in , will parse it to a java object and send it to the company service
	@PutMapping("{id}/coupon/{couponId}/{date}/{price}")
	public Object updateCoupon(@PathVariable ("id") long id ,@PathVariable("couponId") long couponId,@PathVariable("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date newEndDate, @PathVariable("price") double newPrice ) {
		logger.info("send to service: date-" + newEndDate + ",price-" + newPrice + ",company id- " + id + " coupon id-" + couponId );
		return companyService.updateCoupon(id,couponId, newEndDate, newPrice );
	}
	
	@GetMapping("{id}/coupon/type/{type}")
	public Object findAllCouponsByType(@PathVariable ("id") long id, @PathVariable("type") String type){
		return companyService.findAllCouponsByType(id, type.toUpperCase());
	}
	@GetMapping("{id}/coupon/underprice/{underprice}")
	public Object findAllCouponsUnderPrice(@PathVariable("id") long id, @PathVariable("underprice") double underPrice){
		return companyService.findAllCouponsUnderPrice(id,underPrice);
	}
	@GetMapping("{id}/coupon/enddate/{underDate}")
	public Object findAllCouponsEndDateLessThan(@PathVariable("id") long id,@PathVariable("underDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate){
		
		return companyService.findAllCouponsEndDateLessthan(id, endDate);
	}
	@PostMapping("/login/{email}/{password}")
	public Object login(@PathVariable ("password") String password, @PathVariable("email") String email ) {
		return (Company) companyService.login(email, password);
	}
	@GetMapping("/logout")
	public Object logout(@RequestHeader(value = "Authorization") String userName) {
		return companyService.remove(userName);
	}
	
	
}
