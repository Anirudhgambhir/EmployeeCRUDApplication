package com.example.anirudh.rest;

import com.example.anirudh.Accessor.dao.EmployeeDAO;
import com.example.anirudh.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeRestController {
    //TODO: Move EmployeeDAO dependency Injection to Service Layer.
    private final EmployeeDAO employeeDAO;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }


}
