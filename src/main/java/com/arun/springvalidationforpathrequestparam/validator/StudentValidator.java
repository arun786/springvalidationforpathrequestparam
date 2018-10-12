package com.arun.springvalidationforpathrequestparam.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class StudentValidator extends ValidatorBaseClass {
    @Override
    protected void manualValidation(Object o, Errors errors) {

    }
}
