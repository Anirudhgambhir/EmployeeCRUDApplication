package com.example.anirudh.rest;

import com.example.anirudh.service.EmployeeService;
import com.example.anirudh.model.Employee;
import com.example.anirudh.model.getAllEmployeesModel.GetAllEmployeeInput;
import com.example.anirudh.model.getAllEmployeesModel.GetAllEmployeeOutput;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeRestController {
    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public GetAllEmployeeOutput getAllEmployees(@RequestBody(required = false) GetAllEmployeeInput getAllEmployeeInput) {
        return employeeService.getAllEmployees(getAllEmployeeInput);
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) throws JsonProcessingException {
        return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping("/companies/{companyName}")
    public List<Employee> getEmployeesByCompanyName(@PathVariable String companyName) {
        return employeeService.getEmployeesByCompanyName(companyName);
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee newEmployee) throws JsonProcessingException {
        newEmployee.setId(0);
        return employeeService.saveEmployee(newEmployee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee newEmployee) throws JsonProcessingException {
        return employeeService.saveEmployee(newEmployee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId){
        employeeService.deleteEmployee(employeeId);
    }

}
