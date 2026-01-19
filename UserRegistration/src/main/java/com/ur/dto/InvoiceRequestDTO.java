package com.ur.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class InvoiceRequestDTO {

    @Min(value = 0, message = "Amount must be positive")
    private double amount;

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;

    @NotNull(message = "Lease ID is required")
    private Long leaseId;

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

	public Long getLeaseId() {
		return leaseId;
	}

	public void setLeaseId(Long leaseId) {
		this.leaseId = leaseId;
	}

    
}
