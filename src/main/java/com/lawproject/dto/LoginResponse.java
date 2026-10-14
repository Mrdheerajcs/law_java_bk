package com.lawproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {

    @JsonProperty("username")
    private String username;

    public LoginResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}