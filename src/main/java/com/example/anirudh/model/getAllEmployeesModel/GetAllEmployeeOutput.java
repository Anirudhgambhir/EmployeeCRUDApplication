package com.example.anirudh.model.getAllEmployeesModel;

import com.example.anirudh.model.Employee;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GetAllEmployeeOutput {
    private List<Employee> employeeList;
}
