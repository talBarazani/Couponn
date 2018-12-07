package com.tal.couponsdemo.reposotories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tal.couponsdemo.entities.Company;

@Repository
public interface CompanyReposotory extends JpaRepository<Company, Long> {
	public Optional<Company> findByEmail(String email);
}
