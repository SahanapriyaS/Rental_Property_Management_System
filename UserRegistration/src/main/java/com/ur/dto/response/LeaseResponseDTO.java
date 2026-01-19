package com.ur.dto.response;

import java.time.LocalDate;

public class LeaseResponseDTO {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double monthlyRent;
    private double deposit;
    private String status;
    private TenantResponseDTO tenant;
    private PropertyResponseDTO property;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public double getMonthlyRent() {
		return monthlyRent;
	}
	public void setMonthlyRent(double monthlyRent) {
		this.monthlyRent = monthlyRent;
	}
	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public TenantResponseDTO getTenant() {
		return tenant;
	}
	public void setTenant(TenantResponseDTO tenant) {
		this.tenant = tenant;
	}
	public PropertyResponseDTO getProperty() {
		return property;
	}
	public void setProperty(PropertyResponseDTO property) {
		this.property = property;
	}

    
}
