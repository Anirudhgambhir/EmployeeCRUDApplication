package com.example.anirudh.api;

import com.example.anirudh.exceptions.RequestFailureException;
import com.example.anirudh.manager.GetEmployeeByIdManager;
import com.example.anirudh.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class GetEmployeeById {

    private final GetEmployeeByIdManager getEmployeeByIdManager;

    public Employee getEmployeeByIdAPI(int employeeId) {
        try {
            log.info("Starting getEmployeeById");
            return getEmployeeByIdManager.getEmployeeByIdManager(employeeId);
        } catch (Exception ex) {
            log.error("{} exception caught during execution - {}"
                    , ex.getClass().getSimpleName(), ex.getMessage());
            throw new RequestFailureException(String.format("%s exception caught during execution - %s"
                    , ex.getClass().getSimpleName(), ex.getMessage()));
        }
    }
}
