package com.example.rqchallenge.util;

import com.example.rqchallenge.dto.EmployeeDTO;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EmployeeUtil {

    public EmployeeDTO createEmployee(Map<String, Object> employeeInput) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        for (Map.Entry<String, Object> employee : employeeInput.entrySet()) {
            switch (employee.getKey()) {
                case "name":
                    employeeDTO.setName(employee.getValue().toString());
                    break;
                case "age":
                    employeeDTO.setAge(employee.getValue().toString());
                    break;
                case "salary":
                    employeeDTO.setSalary(employee.getValue().toString());
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        return employeeDTO;
    }
}
