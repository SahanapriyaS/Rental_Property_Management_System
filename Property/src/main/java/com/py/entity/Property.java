package com.py.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "property")
public class Property {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private Long regNum;
	
	@Column(nullable=false)
	private Long ownerId;
	
	@Column(nullable = false)
	@NotBlank(message = "Property type cannot be empty")
	private String type;
	
	@Column(nullable = false)
	private Long bhk;
	
	@Column(nullable = false)
	private String houseNum;
	
	@Column(nullable = false)
	private String streetNum;
	
	@Column(nullable = false)
	@NotBlank(message = "City cannot be empty")
	private String city;
	
	@Column(nullable = false)
	@NotBlank(message = "Property pincode cannot be empty")
	@Min(6)
	private Long pincode;
	
	private Double deposit;
	private Double rent;

	public Property() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Property(Long id, Long regNum, Long ownerId,
			@NotBlank(message = "Property type cannot be empty") String type, Long bhk, String houseNum,
			String streetNum, @NotBlank(message = "City cannot be empty") String city,
			@NotBlank(message = "Property pincode cannot be empty") @Min(6) Long pincode, Double deposit, Double rent) {
		super();
		this.id = id;
		this.regNum = regNum;
		this.ownerId = ownerId;
		this.type = type;
		this.bhk = bhk;
		this.houseNum = houseNum;
		this.streetNum = streetNum;
		this.city = city;
		this.pincode = pincode;
		this.deposit = deposit;
		this.rent = rent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRegNum() {
		return regNum;
	}

	public void setRegNum(Long regNum) {
		this.regNum = regNum;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getBhk() {
		return bhk;
	}

	public void setBhk(Long bhk) {
		this.bhk = bhk;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getStreetNum() {
		return streetNum;
	}

	public void setStreetNum(String streetNum) {
		this.streetNum = streetNum;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getRent() {
		return rent;
	}

	public void setRent(Double rent) {
		this.rent = rent;
	}

	
	
	

	
	

}
