package com.example.rqchallenge.dto;

public class EmployeeDelete {
    private String status;
    private String message;

    public EmployeeDelete(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public EmployeeDelete() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
