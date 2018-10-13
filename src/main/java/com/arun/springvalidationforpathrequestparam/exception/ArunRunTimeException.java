package com.arun.springvalidationforpathrequestparam.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;

@Getter
@Setter
public class ArunRunTimeException extends RuntimeException {
    private String errorCode;
    private String message;
    private Errors errorCodes;

    public ArunRunTimeException(String errorCode, Errors errorCodes) {
        this.errorCode = errorCode;
        this.errorCodes = errorCodes;
    }

    public ArunRunTimeException(String errorCode, String message) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
