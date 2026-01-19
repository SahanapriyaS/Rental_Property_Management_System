
package com.ur.dto;

import java.util.Set;

import com.ur.enums.Role;

public class UserDetailsDTO {
	private Long userId;
    private String userName;
    private String name;
    private String email;
    private Long phone;
    private String provider;
    private boolean enabled;
    private Set<Role> roles;

    public UserDetailsDTO() {}

    public UserDetailsDTO(Long userId,String userName, String name, String email, Long phone,
                          String provider, boolean enabled, Set<Role> roles) {
        this.userId=userId;
    	this.userName = userName;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.provider = provider;
        this.enabled = enabled;
        this.roles = roles;
    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

    
}
