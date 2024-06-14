package com.example.anirudh.manager;

import com.example.anirudh.accessor.EmployeeInformationAccessor;
import com.example.anirudh.validator.EmployeeServiceValidator;
import com.example.anirudh.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetEmployeeByIdManager {

    private final EmployeeInformationAccessor employeeInformationCacheAccessor;
    private final EmployeeServiceValidator validate;
    private final ObjectMapper jsonObjectMapper;

    @Autowired
    public GetEmployeeByIdManager(@Qualifier("EmployeeInformationCacheAccessor") EmployeeInformationAccessor employeeInformationCacheAccessor, EmployeeServiceValidator validate, ObjectMapper jsonObjectMapper) {
        this.employeeInformationCacheAccessor = employeeInformationCacheAccessor;
        this.validate = validate;
        this.jsonObjectMapper = jsonObjectMapper;
    }

    public Employee getEmployeeByIdManager(int employeeId) throws JsonProcessingException {
        long startTime = System.currentTimeMillis();
        validate.getEmployeeByIdValidator(employeeId);
        Employee employee = employeeInformationCacheAccessor.getEmployee(employeeId);
        log.info("Response Body :- {}", jsonObjectMapper.writeValueAsString(employee));
        log.info("getEmployeeById finished the request in {} ms", System.currentTimeMillis() - startTime);
        return employee;
    }
}
