package com.lawproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user_tbl")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email", nullable = false, unique = true, length = 50)
    @Email
    @Size(max = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    @Size(max = 100)
    private String password;

    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}