package com.ur.dto.response;


import java.time.LocalDate;

public class InvoiceResponseDTO {

    private Long id;
    private double amount;
    private LocalDate dueDate;
    private String status;
    private LeaseResponseDTO lease;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LeaseResponseDTO getLease() {
		return lease;
	}
	public void setLease(LeaseResponseDTO lease) {
		this.lease = lease;
	}

    
}
