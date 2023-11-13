package com.example.anirudh.Validator;

import org.apache.commons.lang3.Validate;

public class EmployeeServiceValidator {

    public boolean getEmployeeByIdValidator(int id){
        Validate.isTrue(id > 0, "EmployeeId cannot be negative");
        return true;
    }
}
