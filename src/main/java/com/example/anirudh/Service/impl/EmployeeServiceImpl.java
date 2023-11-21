package com.example.anirudh.Service.impl;

import com.example.anirudh.Accessor.dao.EmployeeDAO;
import com.example.anirudh.Exceptions.EmployeeNotFoundException;
import com.example.anirudh.Service.EmployeeService;
import com.example.anirudh.Validator.EmployeeServiceValidator;
import com.example.anirudh.cache.caches.impl.EmployeeCache;
import com.example.anirudh.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
    private final EmployeeCache employeeCache;

    @Override
    public List<Employee> getAllEmployees() {
        // TODO: Add the functionality to get Data directly from DB if required.
        long startTime = System.currentTimeMillis();
        log.info("Starting getAllEmployees");
        List<Employee> employees = employeeCache.getAll();
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
        //TODO: Refactor the API code.
        Employee employee;
        long startTime = System.currentTimeMillis();
        log.info("Starting getEmployeeById");
        validate.getEmployeeByIdValidator(employeeId);
        employee = employeeCache.get(employeeId);
        if (Objects.nonNull(employee)) {
            return employee;
        }
        Optional<Employee> employeeOptional = employeeDAO.findById(employeeId);
        if (employeeOptional.isPresent()) {
            employee = employeeOptional.get();
        }
        else
            throw new EmployeeNotFoundException("EmployeeNotFoundException - Employee Not Present");
        log.info("Response Body :- {}", jsonObjectMapper.writeValueAsString(employee));
        log.info("getEmployeeById finished the request in {} ms", System.currentTimeMillis() - startTime);
        return employee;
    }

    public List<Employee> getEmployeesByCompanyName(String companyName) {
        long startTime = System.currentTimeMillis();
        log.info("Starting getEmployeesByCompanyName");
        List<Employee> employeesByCompanyName = employeeDAO.getEmployeesByCompanyName(companyName);
        log.info("getEmployeesByCompanyName finished the request in {} ms", System.currentTimeMillis() - startTime);
        return employeesByCompanyName;
    }
}
