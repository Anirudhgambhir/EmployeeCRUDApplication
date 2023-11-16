package com.example.anirudh.Accessor.dao;

import com.example.anirudh.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDAO extends JpaRepository<Employee, Integer> {
}
