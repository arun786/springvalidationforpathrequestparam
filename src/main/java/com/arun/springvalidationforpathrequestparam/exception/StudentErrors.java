package com.arun.springvalidationforpathrequestparam.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
@Setter
public class StudentErrors {
    private String errorCode;
    private List<ObjectError> errorCodes;
}
