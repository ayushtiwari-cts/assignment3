package com.app.cryptocurrencyapp.service;

import com.app.cryptocurrencyapp.exceptions.CurrencyAlreadyExistException;
import com.app.cryptocurrencyapp.exceptions.CurrencyNotFoundException;

import java.util.List;

import com.app.cryptocurrencyapp.model.CryptoCurrency;
import com.app.cryptocurrencyapp.model.CryptoCurrencyList;

/**
 * This interface represents a service for CryptoCurrency entities.
 */

public interface CryptoCurrencyService {
    /**
     * Retrieves all CryptoCurrency objects from the repository.
     *
     * @return a list of CryptoCurrency objects
     */
    List<CryptoCurrency> getAllCurrencies();

    /**
     * Retrieves a CryptoCurrency object by its symbol.
     *
     * @param symbol the symbol of the CryptoCurrency
     * @return the CryptoCurrency object with the specified symbol
     * @throws CurrencyNotFoundException if the currency is not found
     */
    CryptoCurrency getCurrencyBySymbol(String symbol) throws CurrencyNotFoundException;

    /**
     * Adds a new CryptoCurrency object to the repository.
     *
     * @param currency the CryptoCurrency object to add
     * @throws CurrencyAlreadyExistException if the currency already exists
     * @return the added CryptoCurrency object
     */
    CryptoCurrency addCurrency(CryptoCurrency currency) throws CurrencyAlreadyExistException;

    /**
     * Updates an existing CryptoCurrency object in the repository.
     *
     * @param currency the CryptoCurrency object to update
     * @throws CurrencyNotFoundException if the currency is not found
     * @return the updated CryptoCurrency object
     */
    CryptoCurrency updateCurrency(CryptoCurrency currency) throws CurrencyNotFoundException;

    /**
     * Deletes a CryptoCurrency object from the repository.
     *
     * @param symbol the symbol of the CryptoCurrency to delete
     * @throws CurrencyNotFoundException if the currency is not found
     * @return true if the currency is deleted, false otherwise
     */
    boolean deleteCurrency(String symbol) throws CurrencyNotFoundException;

    
    /**
     * Fetches a list of CryptoCurrency objects from an external API.
     *
     * @return a list of CryptoCurrency objects
     */
    CryptoCurrencyList fetchCurrenciesFromExternalAPI();

    /**
     * Retrieves a list of CryptoCurrency objects by their symbol from an external API.
     *
     * @param symbol the symbol of the CryptoCurrency
     * @return a list of CryptoCurrency objects with the specified symbol
     */
    CryptoCurrencyList getCurrenciesBySymbolFromExternalAPI(String symbol);

}
