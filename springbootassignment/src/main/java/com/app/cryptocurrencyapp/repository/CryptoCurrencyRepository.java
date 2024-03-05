package com.app.cryptocurrencyapp.repository;

import com.app.cryptocurrencyapp.model.CryptoCurrency;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface represents a repository for CryptoCurrency entities.
 * It extends the MongoRepository interface, providing CRUD operations for CryptoCurrency objects in MongoDB.
 */
@Repository
public interface CryptoCurrencyRepository extends MongoRepository<CryptoCurrency, String> {
    /**
     * Retrieves all CryptoCurrency objects from the repository.
     *
     * @return a list of CryptoCurrency objects
     */
    @SuppressWarnings("null")
    List<CryptoCurrency> findAll();

    /**
     * Retrieves a CryptoCurrency object by its symbol.
     *
     * @param symbol the symbol of the CryptoCurrency
     * @return the CryptoCurrency object with the specified symbol, or null if not found
     */
    CryptoCurrency findBySymbol(String symbol);
}

