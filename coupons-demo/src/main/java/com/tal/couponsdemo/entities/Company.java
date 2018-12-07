package com.tal.couponsdemo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "companies")
public class Company extends Client {
	
	@OneToMany(cascade = { 
	        CascadeType.PERSIST, 
	        CascadeType.MERGE,CascadeType.REMOVE
	})
	@JoinTable(name = "companies_coupons",
			joinColumns = @JoinColumn (name = "company_id"),
			inverseJoinColumns = @JoinColumn(name = "coupon_id")
    )
	private List<Coupon> coupons = new ArrayList<>();
	
	public Company() {
	}

	public Company(String compName, String password,Long id, @NotNull String email) {
		super(id,compName, email, password);
	}



	
	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}
	@Override
	public String toString() {
		return "{name:"+ this.getName()+ ", email:"+ this.getEmail()+", Password:" +this.getPassword() + " }";
		
	}
	



	
	

	
	
}
