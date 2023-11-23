package com.example.anirudh.Validator;

import com.example.anirudh.Exceptions.InvalidRequestException;
import com.example.anirudh.model.Employee;
import org.apache.commons.lang3.Validate;
import org.apache.commons.validator.routines.EmailValidator;

public class EmployeeServiceValidator {

    public boolean getEmployeeByIdValidator(int id){
        try {
            Validate.isTrue(id > 0, "EmployeeId cannot be negative");
        }
        catch (Exception e) {
            throw new InvalidRequestException(e.getMessage());
        }
        return true;
    }

    public boolean saveEmployeeValidator(Employee employee) {
        try {
            Validate.notBlank(employee.getFirstName());
            Validate.notBlank(employee.getLastName());
            Validate.notBlank(employee.getEmail());
            Validate.notBlank(employee.getCompanyName());
            Validate.isTrue(EmailValidator.getInstance().isValid(employee.getEmail()),
                    String.format("email provided - %s is not valid", employee.getEmail()));
            if (employee.getLastName().length() > 45 || employee.getFirstName().length() > 45
                    || employee.getEmail().length() > 45 || employee.getCompanyName().length() > 45)
                throw new InvalidRequestException("Employee last name length can be max of 45 characters");
        }
        catch (Exception exception) {
            throw new InvalidRequestException(exception.getMessage());
        }
        return true;
    }
}
