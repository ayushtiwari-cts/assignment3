package com.app.cryptocurrencyapp.exceptions;

/**
 * Exception thrown when a currency is not found.
 */
public class CurrencyNotFoundException extends Exception {
    /**
     * Constructs a new CurrencyNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public CurrencyNotFoundException(String message) {
        super(message);
    }
}
