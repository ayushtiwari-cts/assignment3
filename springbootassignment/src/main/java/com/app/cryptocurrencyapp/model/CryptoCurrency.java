/**
 * Represents a cryptocurrency.
 * 
 * This class contains the following fields:
 * - symbol: the symbol of the cryptocurrency
 * - availableExchanges: the available exchanges for trading the cryptocurrency
 * - currencyBase: the base currency used for trading the cryptocurrency
 * - currencyQuote: the quote currency used for trading the cryptocurrency
 * 
 * This class uses Lombok to generate getters and setters, as well as constructors.
 * The @Document annotation is used to specify the collection name in MongoDB.
 */
package com.app.cryptocurrencyapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cryptocurrency")
public class CryptoCurrency {
    @Id
    private String symbol;
    private List<String> available_exchanges;
    private String currency_base;
    private String currency_quote;
}
