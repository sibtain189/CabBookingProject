package com.cab.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class AdminException extends UserException {
    public AdminException() {
        super();
    }

    public AdminException(String msg) {
        super(msg);
    }
}
