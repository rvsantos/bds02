package com.devsuperior.bds02.exceptions;

public class DataViolationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataViolationException(String message) {
        super(message);
    }
}
