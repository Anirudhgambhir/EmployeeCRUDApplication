package com.example.anirudh.API;

import com.example.anirudh.Exceptions.RequestFailureException;
import com.example.anirudh.manager.API.GetAllEmployeesManager;
import com.example.anirudh.model.Employee;
import com.example.anirudh.model.getAllEmployeesModel.GetAllEmployeeInput;
import com.example.anirudh.model.getAllEmployeesModel.GetAllEmployeeOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class GetAllEmployees {
    private final GetAllEmployeesManager employeeManager;

    public GetAllEmployeeOutput getAllEmployeesAPI(GetAllEmployeeInput getAllEmployeeInput) {
        try {
            long startTime = System.currentTimeMillis();
            log.info("Starting getAllEmployees");
            List<Employee> employees = employeeManager.getAllEmployeesManager(getAllEmployeeInput);
            log.info("getAllEmployees finished the request in {} ms", System.currentTimeMillis() - startTime);
            return GetAllEmployeeOutput.builder().employeeList(employees).build();
        } catch (Exception ex) {
            log.error("{} exception caught during execution - {}"
                    , ex.getClass().getSimpleName(), ex.getMessage());
            throw new RequestFailureException(String.format("%s exception caught during execution - %s"
                    , ex.getClass().getSimpleName(), ex.getMessage()));
        }
    }
}
