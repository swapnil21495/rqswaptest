package com.example.rqchallenge.dto;

public class SalaryEmployee {
    private final int salary;
    private final com.example.rqchallenge.dto.Employee employee;

    public SalaryEmployee(int salary, com.example.rqchallenge.dto.Employee employee) {
        this.salary = salary;
        this.employee = employee;
    }

    public int getSalary() {
        return salary;
    }

    public com.example.rqchallenge.dto.Employee getEmployee() {
        return employee;
    }
}