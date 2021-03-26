package com.example.JPAexample.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;


public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private final String INCORRECT_REQUEST = "INCORRECT_REQUEST";
    private final String BAD_REQUEST = "BAD_REQUEST";
    private final String NOT_ACCEPTABLE = "NOT_ACCEPTABLE";

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleNotFoundException(RecordNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse err = new ErrorResponse(INCORRECT_REQUEST, details);

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingInfoException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidTraceException(MissingInfoException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse err = new ErrorResponse(BAD_REQUEST, details);
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAcceptableException.class)
    public final ResponseEntity<ErrorResponse> handleNotAllowedException(NotAcceptableException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse err = new ErrorResponse(NOT_ACCEPTABLE, details);
        return new ResponseEntity<>(err, HttpStatus.NOT_ACCEPTABLE);
    }

}
