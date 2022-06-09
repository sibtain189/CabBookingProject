package com.cab.exceptions;

public class CabException extends RuntimeException {
    public CabException() {
    }

    public CabException(String message) {
        super(message);
    }
}
