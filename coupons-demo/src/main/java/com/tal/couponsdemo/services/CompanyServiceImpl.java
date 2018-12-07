package com.tal.couponsdemo.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tal.couponsdemo.entities.Client;
import com.tal.couponsdemo.entities.Company;
import com.tal.couponsdemo.entities.Coupon;
import com.tal.couponsdemo.entities.Customer;
import com.tal.couponsdemo.reposotories.CompanyReposotory;
import com.tal.couponsdemo.reposotories.CouponReposotory;

/*
this is the company service implemntaition, in this class i declared all of the company bussinus logic, this bean will handle the company methods,
there are methos that just pass the data the controllers gets to the reposotories whithout any handling.
*/

@Service 
public class CompanyServiceImpl implements CompanyService , CouponClinentServeice{
	
	//these repostories are injected to this service in constructor declration, they will be the connection to the DB!.
	private final CompanyReposotory companyReposotory;
	private final CouponReposotory couponReposotory;
	
	//the restTemplate will use the service to send rest request to another application
	private RestTemplate restTemplate;
	
	// the cache will use as a conatiner for logged companies
	private HashMap<String, Company> cache;
		
	
	public CompanyServiceImpl(CompanyReposotory companyReposotory, CouponReposotory couponReposotory) {
		this.companyReposotory = companyReposotory;
		this.couponReposotory = couponReposotory;
	}
	@PostConstruct
	public void initialize() {
		this.restTemplate = new RestTemplate();
	}
	
	@Override
	public Optional<Company> findCompanyById(long id) {
		return companyReposotory.findById(id);
	}
	
	//couponos action:
	
	//this method get an email string from the controller and check for a compnay with the same email. this will be use in the logging part
	@Override
	public Company findCompanyByEmail(String email) {
		Company company = companyReposotory.findByEmail(email).orElse(null);
		if(company == null) throw new RuntimeException("No company for that email");
		return company;
	}
	
	/*this method create a coupon and add it to the company Coupon List property,
	 *  by add it to the coupons list propery the hibrenate know to add the coupon to the join table
	 *  coupons-company.
	 *  after the coupon create, the restTemplate send a rest post request to another application that
	 *  that handle the income
	*/
	@Override
	@Transactional
	public Coupon createCoupon(long id, Coupon newCoupon) {
		Company currCompany = companyReposotory.findById(id).get();
		couponReposotory.save(newCoupon);
		currCompany.getCoupons().add(newCoupon);
		restTemplate.postForObject("http://localhost:8888/rest/api/income/company/"+currCompany.getId().toString()+ "/"
									+ currCompany.getName() + "/new",null,Void.class);
		return newCoupon;
	}
	
	/*this method get coupon id and  compnay id, first it find the company, than check if there any coupon for the id, if there is it will delete it from the DB and 
	 * the coupons property of the company.
	 */
	@Override
	@Transactional
	public boolean deleteCouponById(long companyId, long couponId) {
		Company company = companyReposotory.findById(companyId).get();
		Coupon coupon = couponReposotory.findById(couponId).orElse(null);
		if(coupon == null) throw new RuntimeException("the id worng");
		company.getCoupons().remove(coupon);
		couponReposotory.delete(coupon);
		return true;
	}

	
	//a method to get the coupon from the the coupons property list
	public Coupon findCouponById(long id, long couponId) {
		Company company = companyReposotory.findById(id).orElse(null);
		if(company == null) throw new RuntimeException("the company id is wrong");
		Coupon coupon = couponReposotory.findById(couponId).orElse(null);
		if(coupon == null) throw new RuntimeException("the coupon id is wrong");
		int index =  company.getCoupons().indexOf(coupon);
		return company.getCoupons().get(index);
		}
	
	/*
	 this method update a coupon,  
	 */
	@Transactional
	@Override
	public Coupon updateCoupon(long id,long couponId, Date date, double price) {
		Company currCompany = companyReposotory.findById(id).get();
		Coupon couponToUpdate = findCouponById(id, couponId);
		couponToUpdate.setEndDate(date);
		couponToUpdate.setPrice(price);
		restTemplate.postForObject("http://localhost:8888/rest/api/income/company/"+currCompany.getId().toString()+ "/"
									+ currCompany.getName()+"/update",null,Void.class);
		return findCouponById(id, couponId);
	}
	@Override
	public List<Coupon> findAllCouponsOfCompany(long id){
		Optional<Company> currCompany = companyReposotory.findById(id);
		return currCompany.get().getCoupons();
	}
	@Override
	public List<Coupon> findAllCouponsByType(long id, String type){
		return couponReposotory.getItemsFromCompanyByType(id, type);
	}
	@Override
	public List<Coupon> findAllCouponsUnderPrice(long id, double underPrice){
		return couponReposotory.findAllCompanyCouponsUnderPrice(id, underPrice);
	}
	@Override
	public List<Coupon> findAllCouponsEndDateLessthan(long id, Date endDate){
		return couponReposotory.findAllCompanyCouponsEndDateLessThan(id, endDate);
	}
	@Override
	public Client login(String email, String password) {
		Company company = companyReposotory.findByEmail(email).orElse(null);
		if(company == null) throw new RuntimeException("User is not exist");
		if(!company.getPassword().equals(password)) throw new RuntimeException("Wrong details");
		return company;
	}

	@Override
	public Client get(String email) {
		Company company = cache.get(email);
		return company;
	}

	@Override
	public boolean remove(String email) {
		cache.remove(email);
		return true;
	}

}
