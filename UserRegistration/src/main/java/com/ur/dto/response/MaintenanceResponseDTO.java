package com.ur.dto.response;

public class MaintenanceResponseDTO {

    private Long id;
    private String category;
    private String priority;
    private String description;
    private String status;
    private PropertyResponseDTO property;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public PropertyResponseDTO getProperty() {
		return property;
	}
	public void setProperty(PropertyResponseDTO property) {
		this.property = property;
	}

    
}

