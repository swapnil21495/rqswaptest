package com.example.rqchallenge.dto;

public class EmployeeId {

    private String status;
    private Employee data;
    private String message;

    public EmployeeId(String status, Employee data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public EmployeeId() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getData() {
        return data;
    }

    public void setData(Employee data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
