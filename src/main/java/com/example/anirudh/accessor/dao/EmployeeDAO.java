package com.example.anirudh.accessor.dao;

import com.example.anirudh.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeDAO extends JpaRepository<Employee, Integer> {

    @Query(value = "select * from Employee where company = %?1", nativeQuery = true)
    List<Employee> getEmployeesByCompanyName(String companyName);
}
