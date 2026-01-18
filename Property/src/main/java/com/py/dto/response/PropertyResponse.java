package com.py.dto.response;

public class PropertyResponse {
	
    private Long id;
	private Long regNum;
	private String type;
	private Long bhk;
	private String houseNum;
	private String streetNum;
	private String city;
	private Long pincode;
	private Double deposit;
	private Double rent;
	
	private OwnerDTO owner;
	
	public PropertyResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PropertyResponse(Long id, Long regNum, String type, Long bhk, String houseNum, String streetNum, String city,
			Long pincode, Double deposit, Double rent, OwnerDTO owner) {
		super();
		this.id = id;
		this.regNum = regNum;
		this.type = type;
		this.bhk = bhk;
		this.houseNum = houseNum;
		this.streetNum = streetNum;
		this.city = city;
		this.pincode = pincode;
		this.deposit = deposit;
		this.rent = rent;
		this.owner = owner;
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

	public OwnerDTO getOwner() {
		return owner;
	}

	public void setOwner(OwnerDTO owner) {
		this.owner = owner;
	}
	
	
	

}
