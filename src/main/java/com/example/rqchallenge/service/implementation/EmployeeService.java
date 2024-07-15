package com.example.rqchallenge.service.implementation;

import com.example.rqchallenge.dao.IEmployeeDAO;
import com.example.rqchallenge.dto.EmployeeDTO;
import com.example.rqchallenge.dto.SalaryEmployee;
import com.example.rqchallenge.entity.Employee;
import com.example.rqchallenge.service.IEmployeeService;
import com.example.rqchallenge.util.EmployeeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    IEmployeeDAO iEmployeeDAO;

    @Autowired
    EmployeeUtil employeeUtil;

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> resp = iEmployeeDAO.getAllEmployee().getData().stream()
                .map(obj -> {
                    return new Employee(obj.getId(), obj.getEmployee_name(),
                            obj.getEmployee_salary(), obj.getEmployee_age(), obj.getProfile_image());
                }).collect(Collectors.toList());
        return resp;
    }

    @Override
    public List<Employee> getEmployeesByNameSearch(String searchString) {

        List<Employee> returnEmployeeList = iEmployeeDAO.getAllEmployee().getData().stream()
                .filter(emp -> emp.getEmployee_name().toLowerCase().contains(searchString.toLowerCase()))
                .map(obj -> {
                    return new Employee(obj.getId(), obj.getEmployee_name(),
                            obj.getEmployee_salary(), obj.getEmployee_age(), obj.getProfile_image());
                })
                .collect(Collectors.toList());
        return returnEmployeeList;
    }

    @Override
    public Employee getEmployeeById(String id) {
        return iEmployeeDAO.getEmployeeById(id);
    }

    @Override
    public Integer getHighestSalaryOfEmployees() {
        Optional<Integer> highestSalary = iEmployeeDAO.getAllEmployee().getData().stream()
                .map(employee -> {
                    String salaryStr = employee.getEmployee_salary();
                    return Integer.parseInt(salaryStr);
                })
                .max(Integer::compare);
        return highestSalary.orElse(null);
    }

    @Override
    public List<String> getTopTenHighestEarningEmployeeNames() {

        List<String> highestSalaryEmployeeList = iEmployeeDAO.getAllEmployee().getData().stream()
                .map(employee -> {
                    String salaryStr = employee.getEmployee_salary();
                    int salary = Integer.parseInt(salaryStr);
                    return new SalaryEmployee(salary, employee);
                })
                .sorted(Comparator.comparingInt(SalaryEmployee::getSalary).reversed())
                .limit(10)
                .map(salaryEmployee -> salaryEmployee.getEmployee().getEmployee_name())
                .collect(Collectors.toList());
        return highestSalaryEmployeeList;
    }

    @Override
    public Employee createEmployee(Map<String, Object> employeeInput) {
        EmployeeDTO employeeDTO = employeeUtil.createEmployee(employeeInput);
        return iEmployeeDAO.createEmployee(employeeDTO);
    }

    @Override
    public String deleteEmployeeById(String id) {
        return iEmployeeDAO.deleteEmployee(id).getMessage();
    }
}
