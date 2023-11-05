package com.example.anirudh.primer;

import com.example.anirudh.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Component
public class EmployeesPrimer implements ApplicationListener<ApplicationReadyEvent> {

    private final EmployeeService employeeService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Starting primer");
        for (int i = 0; i < 2; i++) {
            employeeService.getAllEmployees();
        }
        log.info("finished primer Calls");
    }
}
