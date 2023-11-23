package com.example.anirudh.Accessor.dao;

import com.example.anirudh.Exceptions.EmployeeNotFoundException;
import com.example.anirudh.cache.CacheManager;
import com.example.anirudh.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeInformationCacheAccessor {

    private final EmployeeDAO employeeDAO;
    private final CacheManager cacheManager;

    public Employee getEmployee(int employeeId) {
        Employee employee = cacheManager.getEmployee(employeeId);
        // Fetching from DB
        if (Objects.isNull(employee)) {
            //TODO: Add employee entry to cache if found in DB
            log.info("Employee not present in Cache... Fetching from DB");
            employee = employeeDAO.findById(employeeId).orElse(null);
        }
        if (Objects.isNull(employee)) {
            log.error("Employee not present in DB...");
            throw new EmployeeNotFoundException("Employee not present in DB...");
        }
        return employee;
    }
    //TODO: Implement the same for GetALL from Cache as well.

}
