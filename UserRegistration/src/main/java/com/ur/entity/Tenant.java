package com.ur.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tenants")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Emergency contact is required")
    @Size(max = 50)
    private String emergencyContact;

    @NotBlank(message = "Employment info is required")
    @Size(max = 100)
    private String employmentInfo;

    private boolean verified;

    @OneToOne
    private User user;

    @ManyToOne
    private Property property;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

    
}
