package com.dev.mercadolivre.model;

import com.dev.mercadolivre.model.validations.FutureDate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class UserModel {
    private Integer id;
    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 6)
    private String password;
    @Email
    private String email;
    @NotBlank
    private String role;
    @FutureDate
    private LocalDateTime createdAt;

    public UserModel(String username, String password, String email, LocalDateTime createdAt, String role) {
        this.username = username;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.email = email;
        this.createdAt = createdAt;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
