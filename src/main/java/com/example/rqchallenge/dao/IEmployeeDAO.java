package com.example.rqchallenge.dao;

import com.example.rqchallenge.dto.EmployeeDTO;
import com.example.rqchallenge.dto.EmployeeDelete;
import com.example.rqchallenge.dto.EmployeeList;
import com.example.rqchallenge.entity.Employee;

import java.util.List;
import java.util.Map;

public interface IEmployeeDAO {
    EmployeeList getAllEmployee();

    Employee getEmployeeById(String id);

    Employee createEmployee(EmployeeDTO employee);

    Employee updateEmployee(Employee employee);

    EmployeeDelete deleteEmployee(String id);
}
