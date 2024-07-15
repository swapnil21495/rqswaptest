package com.example.rqchallenge.dto;

public class EmployeeCreate {
    private String status;
    private Employee data;

    public EmployeeCreate() {
    }

    public EmployeeCreate(String status, Employee data) {
        this.status = status;
        this.data = data;
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
}
