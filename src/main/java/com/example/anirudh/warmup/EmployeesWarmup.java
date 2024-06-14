package com.example.anirudh.warmup;

import com.example.anirudh.service.EmployeeService;
import com.example.anirudh.model.getAllEmployeesModel.GetAllEmployeeInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Component
public class EmployeesWarmup implements ApplicationListener<ApplicationReadyEvent> {

    private final EmployeeService employeeService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Starting primer");
        for (int i = 0; i < 2; i++) {
            employeeService.getAllEmployees(GetAllEmployeeInput.builder().realTimeDataRequired(false).build());
        }
        log.info("finished primer Calls");
    }
}
