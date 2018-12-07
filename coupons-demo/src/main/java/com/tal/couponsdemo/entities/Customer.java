package com.tal.couponsdemo.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "customers")
public class Customer extends Client {

	
	@ManyToMany(cascade = { CascadeType.PERSIST})
	@JoinTable(name = "customers_coupons", joinColumns = @JoinColumn(name = "customer_id"),
				inverseJoinColumns = @JoinColumn(name = "coupon_id")
				)

	private Set<Coupon> coupons = new HashSet<>();
 
	public Customer() {
	}

	public Customer(String custName, Long id, @NotNull String email, String password) {
		super(id,custName , email, password);
	}

	public Set<Coupon> getCoupons() {
		return this.coupons;
	}

	public void setCoupons(Set<Coupon> coupons) {
		this.coupons = coupons;
	}

}
