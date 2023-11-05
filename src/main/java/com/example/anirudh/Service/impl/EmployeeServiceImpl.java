package com.example.anirudh.Service.impl;

import com.example.anirudh.Accessor.dao.EmployeeDAO;
import com.example.anirudh.Service.EmployeeService;
import com.example.anirudh.Validator.Validate;
import com.example.anirudh.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;
    private final Validate validate;

    @Override
    public List<Employee> getAllEmployees() {
        long startTime = System.currentTimeMillis();
        log.info("Starting getAllEmployees");
        validate.validate();
        List<Employee> employees = employeeDAO.getAllEmployees();
        log.info("getAllEmployees finished the request in {} ms", System.currentTimeMillis() - startTime);
        return employees;
    }
}
