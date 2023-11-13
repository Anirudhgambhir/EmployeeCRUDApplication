package com.example.anirudh.Service;

import com.example.anirudh.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee saveEmployee(Employee employee);

    void deleteEmployee(int employeeId);

    Employee getEmployeeById(int employeeId);
}
