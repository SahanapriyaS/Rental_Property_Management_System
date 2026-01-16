package com.ur.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotBlank(message = "Name cannot be empty")
	private String name;

	@Column(nullable = false)
	@NotBlank(message = "Email cannot be empty")
	@Email(message = "Invalid email format")
	private String email;

	@Column(name="username",nullable = false,unique=true)
	@NotBlank(message = "Username cannot be empty")
//	@Min(5)
	private String username;

	@Column(nullable = false)
	@NotBlank(message = "Password cannot be empty")
//	@Min(8)
	private String password;

//	@Min(10)
	private Long phone;

	@Column(nullable = false)
	private String provider;

	private boolean enabled;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Authority> authorities;

	public User() {
		super();
	}

	public User(Long id, @NotBlank(message = "Name cannot be empty") String name,
			@NotBlank(message = "Email cannot be empty") @Email(message = "Invalid email format") String email,
			@NotBlank(message = "UserName cannot be empty") @Min(5) String username,
			@NotBlank(message = "Password cannot be empty") @Min(8) String password, @Min(10) Long phone,
			String provider, boolean enabled, Set<Authority> authorities) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.provider = provider;
		this.enabled = enabled;
		this.authorities = authorities;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", username=" + username + ", password="
				+ password + ", phone=" + phone + ", provider=" + provider + ", enabled=" + enabled + ", authorities="
				+ authorities + "]";
	}
	
	

	
}
