package com.example.anirudh.Accessor;

import com.example.anirudh.Accessor.dao.EmployeeDAO;
import com.example.anirudh.Exceptions.EmployeeNotFoundException;
import com.example.anirudh.cache.CacheManager;
import com.example.anirudh.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeInformationCacheAccessor implements EmployeeInformationAccessor {

    private final EmployeeDAO employeeDAO;
    private final CacheManager cacheManager;

    @Override
    public Employee saveEmployee(Employee employee) {
        throw new IllegalCallerException("Cache does not Support Save Employee Function");
    }

    @Override
    public Employee getEmployee(int employeeId) {
        Employee employee = cacheManager.getEmployee(employeeId);
        // Fetching from DB
        if (Objects.isNull(employee)) {
            log.info("Employee not present in Cache... Fetching from DB");
            Optional<Employee> employeeOptional = employeeDAO.findById(employeeId);
            employeeOptional.ifPresent(cacheManager::setEmployee);
            employee = employeeOptional.orElse(null);
        }
        if (Objects.nonNull(employee))
            return employee;

        log.error("Employee not present in DB...");
        throw new EmployeeNotFoundException("Employee not present in DB...");
    }

    @Override
    public List<Employee> getAllEmployees() {
        //TODO: Fix realTimeDataRequired issue : Currently all calls are going to Cache only
        // GET request does not support requestBody, need to find alternative solution for this.
        return cacheManager.getAllEmployees();
    }

}
