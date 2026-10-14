package com.lawproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UserReq {

    @Email
    @Size(max = 50)
    private String email;

    @Size(max = 16)
    private String password;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}