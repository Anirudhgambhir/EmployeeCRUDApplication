package com.example.anirudh.accessor;

import com.example.anirudh.accessor.dao.EmployeeDAO;
import com.example.anirudh.exceptions.EmployeeNotFoundException;
import com.example.anirudh.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeInformationDBAccessor implements EmployeeInformationAccessor{

    private final EmployeeDAO employeeDAO;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Override
    public Employee getEmployee(int employeeId) {
        Employee employee = employeeDAO.findById(employeeId).orElse(null);
        if (Objects.nonNull(employee))
            return employee;
        log.error("Employee not present in DB...");
        throw new EmployeeNotFoundException("Employee not present in DB...");
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDAO.findAll();
    }
}
