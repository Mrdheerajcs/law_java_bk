package com.lawproject.dto;


public class ApiResponse<T> {
    private T response;
    private int status;
    private String message;

    public ApiResponse(T response, String status, String message) {
        this.response = response;
        this.status = Integer.parseInt(status);
        this.message = message;
    }

    public ApiResponse(T response, int status, String message) {
        this.response = response;
        this.status = status;
        this.message = message;
    }

    public T getResponse() { return response; }
    public void setResponse(T response) { this.response = response; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}