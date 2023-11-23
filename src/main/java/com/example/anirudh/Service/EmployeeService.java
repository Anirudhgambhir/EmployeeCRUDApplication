package com.example.anirudh.Service;

import com.example.anirudh.model.Employee;
import com.example.anirudh.model.getAllEmployeesModel.GetAllEmployeeInput;
import com.example.anirudh.model.getAllEmployeesModel.GetAllEmployeeOutput;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface EmployeeService {
    GetAllEmployeeOutput getAllEmployees(GetAllEmployeeInput getAllEmployeeInput);
    List<Employee> getEmployeesByCompanyName(String companyName);

    Employee saveEmployee(Employee employee) throws JsonProcessingException;

    void deleteEmployee(int employeeId);

    Employee getEmployeeById(int employeeId) throws JsonProcessingException;
}
