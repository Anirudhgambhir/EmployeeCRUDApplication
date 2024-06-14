package com.example.anirudh.service.impl;

import com.example.anirudh.api.DeleteEmployee;
import com.example.anirudh.api.GetAllEmployees;
import com.example.anirudh.api.GetEmployeeById;
import com.example.anirudh.api.GetEmployeesByCompany;
import com.example.anirudh.api.SaveEmployee;
import com.example.anirudh.service.EmployeeService;
import com.example.anirudh.model.Employee;
import com.example.anirudh.model.getAllEmployeesModel.GetAllEmployeeInput;
import com.example.anirudh.model.getAllEmployeesModel.GetAllEmployeeOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final GetAllEmployees getAllEmployees;
    private final SaveEmployee saveEmployee;
    private final DeleteEmployee deleteEmployee;
    private final GetEmployeeById getEmployeeById;
    private final GetEmployeesByCompany getEmployeesByCompany;

    @Override
    public GetAllEmployeeOutput getAllEmployees(GetAllEmployeeInput getAllEmployeeInput) {
        return getAllEmployees.getAllEmployeesAPI(getAllEmployeeInput);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return saveEmployee.saveEmployeeAPI(employee);
    }

    @Override
    public void deleteEmployee(int employeeId) {
        deleteEmployee.deleteEmployeeAPI(employeeId);
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return getEmployeeById.getEmployeeByIdAPI(employeeId);

    }

    @Override
    public List<Employee> getEmployeesByCompanyName(String companyName) {
        return getEmployeesByCompany.getEmployeesByCompanyAPI(companyName);
    }
}
