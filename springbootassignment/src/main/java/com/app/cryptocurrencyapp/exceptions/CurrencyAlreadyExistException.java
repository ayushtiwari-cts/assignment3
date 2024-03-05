package com.app.cryptocurrencyapp.exceptions;

/**
 * Exception thrown when a currency already exists.
 */
public class CurrencyAlreadyExistException extends Exception {
    /**
     * Constructs a new CurrencyAlreadyExistException with the specified detail message.
     *
     * @param message the detail message
     */
    public CurrencyAlreadyExistException(String message) {
        super(message);
    }
}
