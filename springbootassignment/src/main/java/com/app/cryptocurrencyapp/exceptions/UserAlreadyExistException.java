package com.app.cryptocurrencyapp.exceptions;

/**
 * Exception thrown when a user already exists.
 */
public class UserAlreadyExistException extends Exception {
    /**
     * Constructs a new UserAlreadyExistException with the specified detail message.
     *
     * @param message the detail message
     */
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
