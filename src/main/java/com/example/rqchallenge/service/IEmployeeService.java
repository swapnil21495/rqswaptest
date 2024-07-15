package com.example.rqchallenge.service;

import com.example.rqchallenge.entity.Employee;

import java.util.List;
import java.util.Map;

public interface IEmployeeService
{
    public List<Employee> getAllEmployees();

    public List<Employee> getEmployeesByNameSearch(String searchString);

    public Employee getEmployeeById(String id);

    public Integer getHighestSalaryOfEmployees();

    public List<String> getTopTenHighestEarningEmployeeNames();

    public Employee createEmployee(Map<String, Object> employeeInput);

    public String deleteEmployeeById(String id);
}
