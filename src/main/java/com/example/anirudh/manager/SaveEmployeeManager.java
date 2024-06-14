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
public class SaveEmployeeManager {

    private final EmployeeServiceValidator validate;
    private final ObjectMapper jsonObjectMapper;
    private final EmployeeInformationAccessor employeeInformationAccessor;

    @Autowired
    public SaveEmployeeManager(EmployeeServiceValidator validate, ObjectMapper jsonObjectMapper, @Qualifier("EmployeeInformationDBAccessor") EmployeeInformationAccessor employeeInformationAccessor) {
        this.validate = validate;
        this.jsonObjectMapper = jsonObjectMapper;
        this.employeeInformationAccessor = employeeInformationAccessor;
    }

    public Employee saveEmployeeManager(Employee employee) throws JsonProcessingException {
        long startTime = System.currentTimeMillis();
        validate.saveEmployeeValidator(employee);
        Employee e = employeeInformationAccessor.saveEmployee(employee);
        log.info("saveEmployee finished the request in {} ms", System.currentTimeMillis() - startTime);
        return e;
    }
}
