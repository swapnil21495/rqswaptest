package com.example.rqchallenge.dao.implementation;

import com.example.rqchallenge.dao.IEmployeeDAO;
import com.example.rqchallenge.dto.*;
import com.example.rqchallenge.entity.Employee;
import com.example.rqchallenge.util.EmployeeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class EmployeeDAO implements IEmployeeDAO {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeDAO.class);

    private final RestTemplate restTemplate;
    String resourceUrl = "https://dummy.restapiexample.com/api/v1/";

    @Autowired
    EmployeeUtil employeeUtil;

    public EmployeeDAO(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public EmployeeList getAllEmployee() {
        EmployeeList responseObjectList = new EmployeeList();
        try {
            responseObjectList
                    = restTemplate.getForObject(resourceUrl + "employees", EmployeeList.class);
            assert responseObjectList != null;
            logger.info("Response list: {}", responseObjectList.getData().size());
        } catch (Exception exception) {
            logger.error("error in method::getAllEmployee : {}", exception.getMessage());
        }
        return responseObjectList;
    }

    @Override
    public Employee getEmployeeById(String id) {
        Employee employee = new Employee();
        try {
            EmployeeId employeeResponseEntity = restTemplate.getForObject(resourceUrl + "employee/" + id, EmployeeId.class);
            logger.info("Response: {}", employeeResponseEntity);
            employee = new Employee(Objects.requireNonNull(employeeResponseEntity).getData().getId(),
                    employeeResponseEntity.getData().getEmployee_name()
                    , employeeResponseEntity.getData().getEmployee_salary(),
                    employeeResponseEntity.getData().getEmployee_age(), employeeResponseEntity.getData().getProfile_image());
        } catch (Exception exception) {
            logger.error("error for getting employee details : {}", exception.getMessage());
        }
        return employee;
    }

    @Override
    public Employee createEmployee(EmployeeDTO employee) {
        Employee createdEmployee = new Employee();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<EmployeeDTO> requestEntity = new HttpEntity<>(employee, headers);
        try {
            logger.info("Request :" + employee.toString());
            EmployeeCreate employeeResponseEntity = restTemplate.postForObject(resourceUrl + "create", requestEntity, EmployeeCreate.class);
            createdEmployee = new Employee(
                    Objects.requireNonNull(employeeResponseEntity).getData().getId(),
                    employeeResponseEntity.getData().getEmployee_name()
                    , employeeResponseEntity.getData().getEmployee_salary(),
                    employeeResponseEntity.getData().getEmployee_age(), employeeResponseEntity.getData().getProfile_image()
            );
            logger.info("Response Employee details: {}", employeeResponseEntity);
        } catch (Exception exception) {
            logger.error("error creating employee : {}", exception.getMessage());
        }
        return createdEmployee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        try {
            ResponseEntity<Employee> employeeResponseEntity = restTemplate.postForEntity(resourceUrl + "update", employee, Employee.class);
            employee = employeeResponseEntity.getBody();
            logger.info("updating Employee details: {}", employeeResponseEntity);
        } catch (Exception exception) {
            logger.error("updating employee details failed: {}", exception.getMessage());
        }
        return employee;
    }

    @Override
    public EmployeeDelete deleteEmployee(String id) {
        EmployeeDelete employee = new EmployeeDelete();
        try {
            ResponseEntity<EmployeeDelete> response = restTemplate.exchange(resourceUrl + "delete/" + id, HttpMethod.DELETE,
                    null, EmployeeDelete.class);
            employee = response.getBody();
            logger.info("deleting employee details for id : {}", id);
        } catch (Exception exception) {
            logger.error("deletion failed with : {}", exception.getMessage());
        }
        return employee;
    }
}
