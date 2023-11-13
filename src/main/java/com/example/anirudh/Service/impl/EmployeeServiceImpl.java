package com.example.anirudh.Service.impl;

import com.example.anirudh.Accessor.dao.EmployeeDAO;
import com.example.anirudh.Service.EmployeeService;
import com.example.anirudh.Validator.EmployeeServiceValidator;
import com.example.anirudh.Validator.Validate;
import com.example.anirudh.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;
    private final EmployeeServiceValidator validate;

    @Override
    public List<Employee> getAllEmployees() {
        long startTime = System.currentTimeMillis();
        log.info("Starting getAllEmployees");
        List<Employee> employees = employeeDAO.getAllEmployees();
        log.info("getAllEmployees finished the request in {} ms", System.currentTimeMillis() - startTime);
        return employees;
    }

    @Override
    @Transactional
    public Employee saveEmployee(Employee employee) {
        long startTime = System.currentTimeMillis();
        log.info("Starting saveEmployee");
        Employee e = employeeDAO.saveEmployee(employee);
        log.info("saveEmployee finished the request in {} ms", System.currentTimeMillis() - startTime);
        return e;
    }

    @Override
    @Transactional
    public void deleteEmployee(int employeeId) {
        long startTime = System.currentTimeMillis();
        log.info("Starting deleteEmployee");
        employeeDAO.deleteEmployee(employeeId);
        log.info("deleteEmployee finished the request in {} ms", System.currentTimeMillis() - startTime);
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        long startTime = System.currentTimeMillis();
        log.info("Starting getEmployeeById");
        validate.getEmployeeByIdValidator(employeeId);
        Employee e = employeeDAO.getEmployeeById(employeeId);
        log.info("getEmployeeById finished the request in {} ms", System.currentTimeMillis() - startTime);
        return e;
    }
}
