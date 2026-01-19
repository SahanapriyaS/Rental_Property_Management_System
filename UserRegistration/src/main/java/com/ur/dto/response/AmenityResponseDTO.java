package com.ur.dto.response;


public class AmenityResponseDTO {

    private Long id;
    private String name;
    private String description;
    private PropertyResponseDTO property;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public PropertyResponseDTO getProperty() {
		return property;
	}
	public void setProperty(PropertyResponseDTO property) {
		this.property = property;
	}

    
}
