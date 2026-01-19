package com.ur.dto.response;


import com.ur.dto.UserDetailsDTO;

public class TenantResponseDTO {

    private Long id;
    private String emergencyContact;
    private String employmentInfo;
    private boolean verified;
    private UserDetailsDTO user;  
    private Long propertyId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmergencyContact() {
		return emergencyContact;
	}
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
	public String getEmploymentInfo() {
		return employmentInfo;
	}
	public void setEmploymentInfo(String employmentInfo) {
		this.employmentInfo = employmentInfo;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public UserDetailsDTO getUser() {
		return user;
	}
	public void setUser(UserDetailsDTO user) {
		this.user = user;
	}
	public Long getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

    
}
