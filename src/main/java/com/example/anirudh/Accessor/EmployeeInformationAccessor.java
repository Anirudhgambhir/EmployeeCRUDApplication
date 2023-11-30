package com.example.anirudh.Accessor;

import com.example.anirudh.model.Employee;

import java.util.List;

public interface EmployeeInformationAccessor {

    Employee saveEmployee(Employee employee);

    Employee getEmployee(int employeeId);

    List<Employee> getAllEmployees();
}
