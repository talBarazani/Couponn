package com.tal.couponsdemo.reposotories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tal.couponsdemo.entities.Customer;
@Repository
public interface CustomerReposotory extends JpaRepository<Customer, Long> {
	public Optional<Customer> findByEmail(String email);

}
