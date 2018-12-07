package com.tal.couponsdemo.reposotories;


import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tal.couponsdemo.entities.Coupon;
import com.tal.couponsdemo.entities.CouponType;



@Repository
public interface CouponReposotory extends JpaRepository<Coupon, Long> {
	@Query("SELECT c from Coupon c where c.endDate < :currDate")
	public List<Coupon> findAllCoupnosEndDateLessThan(@Param("currDate") Date currDate);
	public List<Coupon> findByType(CouponType type);
	public List<Coupon> findAllByOrderByStartDateAsc();
	
//	@Query("SELECT comp.coupons from Company comp where comp.id = :id and comp.coupons in (select c from Coupon c where c.type = (:type))")
//	List<Coupon> getItemsFromCompanyByType(@Param ("id") long id , @Param("type") CouponType type);
	//@Query("SELECT comp from Company comp left join fetch comp.coupons c where comp.id = :id and c.type = :type")
//	@Query(value = "SELECT * from coupons c left join companies_coupons com on c.id = com.coupon_id where c.type = :type and com.company_id = :id",nativeQuery=true)
//	List<Coupon> getItemsFromCompanyByType(@Param("id") long id, @Param("type") CouponType type);
	@Query(value = "SELECT * from coupons c left join companies_coupons com on c.id = com.coupon_id where c.type = :type and com.company_id = :id",nativeQuery=true)
	List<Coupon> getItemsFromCompanyByType(@Param("id") long id, @Param("type") String type);
	@Query(value = "SELECT * from coupons c left join companies_coupons com on c.id = com.coupon_id where c.price < :underPrice and com.company_id = :id",nativeQuery=true)
	List<Coupon> findAllCompanyCouponsUnderPrice(@Param("id") long id, @Param("underPrice") double underPrice);
	@Query(value = "SELECT * from coupons c left join companies_coupons com on c.id = com.coupon_id where c.end_date < :endDate and com.company_id = :id",nativeQuery=true)
	List<Coupon> findAllCompanyCouponsEndDateLessThan(@Param("id") long id, @Param("endDate") Date endDate);
	@Query(value = "SELECT * from coupons c left join customers_coupons cus on c.id = cus.coupon_id where c.type = :type and cus.customer_id = :id",nativeQuery=true)
	Set<Coupon> getItemsFromCustomerByType(@Param("id") long id, @Param("type") String type);
	@Query(value = "SELECT * from coupons c left join customers_coupons cus on c.id = cus.coupon_id where c.price < :underPrice and cus.customer_id = :id",nativeQuery=true)
	Set<Coupon> findAllCustomerCouponsUnderPrice(@Param("id") long id, @Param("underPrice") double underPrice);



}
