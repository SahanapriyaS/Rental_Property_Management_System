
package com.ur.dto;

import com.ur.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SignupRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private Long phone;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotNull
    private Role role;

    public SignupRequest() {}

    public SignupRequest(String name, String email, Long phone, String username, String password, Role role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Long getPhone() { return phone; }
    public void setPhone(Long phone) { this.phone = phone; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    @Override
    public String toString() {
        return "SignupRequest [name=" + name + ", email=" + email + ", phone=" + phone
                + ", username=" + username + ", role=" + role + "]";
    }
}
