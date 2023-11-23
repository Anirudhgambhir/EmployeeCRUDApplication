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
        // First Name
        Validate.notBlank(employee.getFirstName());
        if (employee.getFirstName().length() > 45)
            throw new InvalidRequestException("Employee first name length can be max of 45 characters");

        // Last Name
        Validate.notBlank(employee.getLastName());
        if (employee.getLastName().length() > 45)
            throw new InvalidRequestException("Employee last name length can be max of 45 characters");

        // email
        Validate.notBlank(employee.getEmail());
        if (employee.getEmail().length() > 45)
            throw new InvalidRequestException("Employee email length can be max of 45 characters");
        Validate.isTrue(EmailValidator.getInstance().isValid(employee.getEmail()),
                String.format("email provided - %s is not valid", employee.getEmail()));

        // company
        Validate.notBlank(employee.getCompanyName());
        if (employee.getCompanyName().length() > 45)
            throw new InvalidRequestException("Employee company length can be max of 45 characters");

        return true;
    }
}
