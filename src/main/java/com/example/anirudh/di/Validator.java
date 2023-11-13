package com.example.anirudh.di;

import com.example.anirudh.Validator.EmployeeServiceValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Validator {

    @Bean
    public EmployeeServiceValidator getEmployeesValidate(){
        return new EmployeeServiceValidator();
    }
}
