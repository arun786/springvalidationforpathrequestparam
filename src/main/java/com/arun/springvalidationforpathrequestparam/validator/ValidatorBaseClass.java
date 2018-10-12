package com.arun.springvalidationforpathrequestparam.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public abstract class ValidatorBaseClass implements Validator {

    @Autowired
    private Validator validatorBaseClass;

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.invokeValidator(validatorBaseClass, o, errors);
        manualValidation(o, errors);
    }

    protected abstract void manualValidation(Object o, Errors errors);
}
