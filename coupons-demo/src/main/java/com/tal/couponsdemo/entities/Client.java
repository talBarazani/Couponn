package com.tal.couponsdemo.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=100,unique=true)
	private String name;
	
	@Column(length=100,unique= true)
	@NotNull
	private String email;
	
	@Column
	@NotNull
	private String  password;
	
	public Client() {
	}

	
	
	

	public Client(Long id, String name, @NotNull String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}





	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
