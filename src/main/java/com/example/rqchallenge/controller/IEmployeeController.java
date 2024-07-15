package com.example.rqchallenge.controller;

import com.example.rqchallenge.entity.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface IEmployeeController {


    ResponseEntity<?> getAllEmployees() throws IOException;


    ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString);


    ResponseEntity<Employee> getEmployeeById(String id);


    ResponseEntity<Integer> getHighestSalaryOfEmployees();


    ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames();


    ResponseEntity<Employee> createEmployee(Map<String, Object> employeeInput);


    ResponseEntity<String> deleteEmployeeById(String id);

}
