package com.ur.dto.response;

public class ServicesResponseDTO {

    private Long id;
    private String name;
    private String description;
    private TenantResponseDTO tenant;
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
	public TenantResponseDTO getTenant() {
		return tenant;
	}
	public void setTenant(TenantResponseDTO tenant) {
		this.tenant = tenant;
	}
    
    

}