package com.devsuperior.bds02.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
    private static final String DEPENDENCIES_ASSOCIATED = "Dependencies associated";
    private static final String RESOURCE_NOT_FOUND = "Resource Not Found";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e,
            HttpServletRequest request) {
        StandardError err = new StandardError.StandardErrorBuilder()
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(RESOURCE_NOT_FOUND)
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(DataViolationException.class)
    public ResponseEntity<StandardError> entityNotFound(DataViolationException e,
            HttpServletRequest request) {
        StandardError err = new StandardError.StandardErrorBuilder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(DEPENDENCIES_ASSOCIATED)
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
