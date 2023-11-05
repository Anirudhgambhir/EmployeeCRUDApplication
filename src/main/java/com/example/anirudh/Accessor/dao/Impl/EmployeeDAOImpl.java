package com.example.anirudh.Accessor.dao.Impl;

import com.example.anirudh.Accessor.dao.EmployeeDAO;
import com.example.anirudh.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeDAOImpl implements EmployeeDAO {


    private final EntityManager entityManager;

    @Override
    public List<Employee> getAllEmployees() {
        // Note - createQuery - String arg shows error during compileTime but no effect during runtime.
        TypedQuery<Employee> getAllEmployeesQuery = entityManager.createQuery("from Employee", Employee.class);
        return getAllEmployeesQuery.getResultList();
    }
}