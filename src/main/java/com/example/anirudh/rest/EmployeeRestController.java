package com.example.anirudh.rest;

import com.example.anirudh.Service.EmployeeService;
import com.example.anirudh.model.Employee;
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
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee newEmployee) {
        newEmployee.setId(0);
        return employeeService.saveEmployee(newEmployee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee newEmployee) {
        return employeeService.saveEmployee(newEmployee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId){
        employeeService.deleteEmployee(employeeId);
    }

}
