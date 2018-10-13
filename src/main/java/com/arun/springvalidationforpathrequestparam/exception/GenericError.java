package com.arun.springvalidationforpathrequestparam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RestController
public class GenericError extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ArunRunTimeException.class)
    public ResponseEntity<List<Error>> handleArunRunTimeException(ArunRunTimeException e, WebRequest request) {
        Errors errorCodes = e.getErrorCodes();

        List<Error> lstError = new ArrayList<>();
        List<ObjectError> allErrors = new ArrayList<>();
        if (errorCodes != null) {
            allErrors = errorCodes.getAllErrors();
        }

        if (!allErrors.isEmpty()) {
            allErrors.forEach(ec -> {
                String field = "";
                if (ec instanceof FieldError) {
                    field = ((FieldError) ec).getField();
                }
                Error error = new Error("INVALID_INPUT", field + " : " + ec.getDefaultMessage());
                lstError.add(error);
            });
        } else if (e.getErrorCode() != null) {
            Error error = new Error(e.getErrorCode(), e.getMessage());
            lstError.add(error);
        }

        return new ResponseEntity<>(lstError, HttpStatus.BAD_REQUEST);
    }
}
