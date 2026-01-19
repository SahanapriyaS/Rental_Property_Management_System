package com.ur.dto.response;


import java.time.LocalDate;

public class PaymentResponseDTO {

    private Long id;
    private double amount;
    private LocalDate paymentDate;
    private String mode;
    private String status;
    private InvoiceResponseDTO invoice;
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
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public InvoiceResponseDTO getInvoice() {
		return invoice;
	}
	public void setInvoice(InvoiceResponseDTO invoice) {
		this.invoice = invoice;
	}

    
}
