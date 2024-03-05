package com.app.cryptocurrencyapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * This class is a global exception handler for handling various custom exceptions in the application.
 * It provides exception handling methods for specific custom exceptions, as well as a generic exception handler for any other unhandled exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CurrencyAlreadyExistException.class)
    public ResponseEntity<String> handleCurrencyAlreadyExistException(CurrencyAlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(CurrencyNotFoundException.class)
    public ResponseEntity<String> handleCurrencyNotFoundException(CurrencyNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<String> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Add more exception handlers for other custom exceptions if needed

    // Add a generic exception handler for any other unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
}




