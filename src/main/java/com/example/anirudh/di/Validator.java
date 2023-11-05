package com.example.anirudh.di;

import com.example.anirudh.Validator.GetAllEmployeesValidator;
import com.example.anirudh.Validator.Validate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Validator {

    @Bean
    public Validate getAllEmployeesValidate(){
        return new GetAllEmployeesValidator();
    }
}
