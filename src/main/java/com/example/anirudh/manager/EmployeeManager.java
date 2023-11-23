package com.example.anirudh.manager;

import com.example.anirudh.Accessor.EmployeeInformationCacheAccessor;
import com.example.anirudh.Validator.EmployeeServiceValidator;
import com.example.anirudh.model.Employee;
import com.example.anirudh.model.getAllEmployeesModel.GetAllEmployeeInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeManager {
    private final EmployeeServiceValidator validate;
    private final ObjectMapper jsonObjectMapper;
    private final EmployeeInformationCacheAccessor employeeInformationCacheAccessor;

    public Employee getEmployeeByIdManager(int employeeId) throws JsonProcessingException {
        long startTime = System.currentTimeMillis();
        validate.getEmployeeByIdValidator(employeeId);
        Employee employee = employeeInformationCacheAccessor.getEmployee(employeeId);
        log.info("Response Body :- {}", jsonObjectMapper.writeValueAsString(employee));
        log.info("getEmployeeById finished the request in {} ms", System.currentTimeMillis() - startTime);
        return employee;
    }

    public List<Employee> getAllEmployeesManager(GetAllEmployeeInput getAllEmployeeInput) {
        return Objects.nonNull(getAllEmployeeInput) ?
                employeeInformationCacheAccessor.getAllEmployees(getAllEmployeeInput.isRealTimeDataRequired()) :
                employeeInformationCacheAccessor.getAllEmployees(false);
    }
}
