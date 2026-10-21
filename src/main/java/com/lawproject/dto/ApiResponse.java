package com.lawproject.dto;

public class ApiResponse<T> {
    private T response;
    private int status;
    private String message;

    public ApiResponse(T response, int status, String message) {
        this.response = response;
        this.status = status;
        this.message = message;
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, 200, message);
    }

    public static <T> ApiResponse<T> error(int status, String message) {
        return new ApiResponse<>(null, status, message);
    }

    public T getResponse() { return response; }
    public void setResponse(T response) { this.response = response; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}