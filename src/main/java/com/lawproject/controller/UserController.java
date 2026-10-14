package com.lawproject.controller;

import com.lawproject.dto.*;
import com.lawproject.entity.User;
import com.lawproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "User Controller", description = "User Management APIs")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login-user")
    @Operation(summary = "Login user", description = "Authenticate user with username and password")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody AuthRequest authRequest) {
        boolean isAuthenticated = userService.login(authRequest);
        if (isAuthenticated) {
            LoginResponse loginResponse = new LoginResponse(authRequest.getUsername());
            return ResponseEntity.ok(new ApiResponse<>(loginResponse, "200", "success"));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse<>(null, "401", "Invalid credentials"));
    }

    @PostMapping("/create-user")
    @Operation(summary = "Create new user", description = "Register a new user")
    public ResponseEntity<ApiResponse<User>> createUser(@Valid @RequestBody UserReq userReq) {
        User user = userService.createUser(userReq);
        if (user != null) {
            return ResponseEntity.ok(new ApiResponse<>(user, "200", "User created successfully"));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(null, "409", "User already exists"));
    }

    @PutMapping("/forgot-pwd/{email}")
    @Operation(summary = "Forgot password", description = "Reset password for user")
    public ResponseEntity<ApiResponse<String>> forgotPassword(
            @PathVariable String email,
            @RequestParam String newPassword) {
        boolean updated = userService.forgotPassword(email, newPassword);
        if (updated) {
            return ResponseEntity.ok(new ApiResponse<>("Password updated", "200", "success"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(null, "404", "User not found"));
    }
}