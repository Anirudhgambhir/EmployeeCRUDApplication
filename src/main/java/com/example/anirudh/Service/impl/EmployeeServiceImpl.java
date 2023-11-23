package com.example.anirudh.Service.impl;

import com.example.anirudh.Accessor.dao.EmployeeDAO;
import com.example.anirudh.Exceptions.EmployeeNotFoundException;
import com.example.anirudh.Exceptions.RequestFailureException;
import com.example.anirudh.Service.EmployeeService;
import com.example.anirudh.Validator.EmployeeServiceValidator;
import com.example.anirudh.cache.CacheManager;
import com.example.anirudh.manager.EmployeeManager;
import com.example.anirudh.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;
    private final EmployeeServiceValidator validate;
    private final ObjectMapper jsonObjectMapper;
    private final CacheManager cacheManager;

    private final EmployeeManager employeeManager;

    @Override
    public List<Employee> getAllEmployees() {
        // TODO: Add the functionality to get Data directly from DB if required.
        long startTime = System.currentTimeMillis();
        log.info("Starting getAllEmployees");
        List<Employee> employees = cacheManager.getAllEmployees();
        log.info("getAllEmployees finished the request in {} ms", System.currentTimeMillis() - startTime);
        return employees;
    }

    @Override
    public Employee saveEmployee(Employee employee) throws JsonProcessingException {
        long startTime = System.currentTimeMillis();
        log.info("Starting saveEmployee");
        log.info("Request Body :- {}", jsonObjectMapper.writeValueAsString(employee));
        validate.saveEmployeeValidator(employee);
        Employee e = employeeDAO.save(employee);
        log.info("Response Body :- {}", jsonObjectMapper.writeValueAsString(e));
        log.info("saveEmployee finished the request in {} ms", System.currentTimeMillis() - startTime);
        return e;
    }

    @Override
    public void deleteEmployee(int employeeId) {
        long startTime = System.currentTimeMillis();
        log.info("Starting deleteEmployee");
        employeeDAO.deleteById(employeeId);
        log.info("deleteEmployee finished the request in {} ms", System.currentTimeMillis() - startTime);
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws JsonProcessingException {
        try {
            log.info("Starting getEmployeeById");
            return employeeManager.getEmployeeByIdManager(employeeId);
        }
        catch (Exception ex) {
            log.error("{} exception caught during execution - {}"
                    , ex.getClass().getSimpleName(), ex.getMessage());
            throw new RequestFailureException(String.format("%s exception caught during execution - %s"
                    , ex.getClass().getSimpleName(), ex.getMessage()));
        }
    }

    public List<Employee> getEmployeesByCompanyName(String companyName) {
        long startTime = System.currentTimeMillis();
        log.info("Starting getEmployeesByCompanyName");
        List<Employee> employeesByCompanyName = employeeDAO.getEmployeesByCompanyName(companyName);
        log.info("getEmployeesByCompanyName finished the request in {} ms", System.currentTimeMillis() - startTime);
        return employeesByCompanyName;
    }
}
