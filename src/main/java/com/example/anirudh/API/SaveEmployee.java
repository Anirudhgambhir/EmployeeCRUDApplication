package com.example.anirudh.API;

import com.example.anirudh.Exceptions.RequestFailureException;
import com.example.anirudh.Validator.EmployeeServiceValidator;
import com.example.anirudh.manager.API.SaveEmployeeManager;
import com.example.anirudh.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class SaveEmployee {
    private final SaveEmployeeManager saveEmployeeManager;
    private final EmployeeServiceValidator validate;
    private final ObjectMapper jsonObjectMapper;

    public Employee saveEmployeeAPI(Employee employee) {
        try {
            long startTime = System.currentTimeMillis();
            log.info("Starting saveEmployee");
            log.info("Request Body :- {}", jsonObjectMapper.writeValueAsString(employee));
            validate.saveEmployeeValidator(employee);
            Employee e = saveEmployeeManager.saveEmployeeManager(employee);
            log.info("Response Body :- {}", jsonObjectMapper.writeValueAsString(e));
            log.info("saveEmployee finished the request in {} ms", System.currentTimeMillis() - startTime);
            return e;
        } catch (Exception e) {
            log.error("{} exception caught during execution - {}"
                    , e.getClass().getSimpleName(), e.getMessage());
            throw new RequestFailureException(String.format("%s exception caught during execution - %s"
                    , e.getClass().getSimpleName(), e.getMessage()));
        }
    }
}
