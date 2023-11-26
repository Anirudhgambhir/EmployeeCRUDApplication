package com.example.anirudh.Accessor;

import com.example.anirudh.model.Employee;

import java.util.List;

public interface EmployeeInformationAccessor {

    public Employee saveEmployee(Employee employee);

    public Employee getEmployee(int employeeId);

    public List<Employee> getAllEmployees();
}
