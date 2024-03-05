package com.app.cryptocurrencyapp.service;

import com.app.cryptocurrencyapp.config.AppConfig;
import com.app.cryptocurrencyapp.exceptions.CurrencyAlreadyExistException;
import com.app.cryptocurrencyapp.exceptions.CurrencyNotFoundException;
import com.app.cryptocurrencyapp.model.CryptoCurrency;
import com.app.cryptocurrencyapp.model.CryptoCurrencyList;
import com.app.cryptocurrencyapp.repository.CryptoCurrencyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * This class implements the CryptoCurrencyService interface.
 */
@Service
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {
    @Autowired
    private CryptoCurrencyRepository currencyRepository;

    @Value("${api.url}")
    private String apiValue;

    @Autowired
    private AppConfig appConfig;

    @Override
    public List<CryptoCurrency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public CryptoCurrency getCurrencyBySymbol(String symbol) throws CurrencyNotFoundException {
        CryptoCurrency currency = currencyRepository.findBySymbol(symbol);
        if (currency == null) {
            throw new CurrencyNotFoundException("Currency with symbol " + symbol + " not found");
        }
        return currency;
    }

    @Override
    public CryptoCurrency addCurrency(CryptoCurrency currency) throws CurrencyAlreadyExistException {
        CryptoCurrency existingCurrency = currencyRepository.findBySymbol(currency.getSymbol());
        if (existingCurrency != null) {
            throw new CurrencyAlreadyExistException("Currency with symbol " + currency.getSymbol() + " already exists");
        }
        return currencyRepository.save(currency);
    }

    @Override
    public CryptoCurrency updateCurrency(CryptoCurrency currency) throws CurrencyNotFoundException {
        CryptoCurrency existingCurrency = currencyRepository.findBySymbol(currency.getSymbol());
        if (existingCurrency == null) {
            throw new CurrencyNotFoundException("Currency with symbol " + currency.getSymbol() + " not found");
        }
        return currencyRepository.save(currency);
    }

    @Override
    public boolean deleteCurrency(String symbol) throws CurrencyNotFoundException {
        CryptoCurrency existingCurrency = currencyRepository.findBySymbol(symbol);
        if (existingCurrency == null) {
            throw new CurrencyNotFoundException("Currency with symbol " + symbol + " not found");
        }
        currencyRepository.delete(existingCurrency);
        return true;
    }

    
    @Override
    public CryptoCurrencyList fetchCurrenciesFromExternalAPI() {
        RestTemplate restTemplate = appConfig.restTemplate();
        @SuppressWarnings("null")
        CryptoCurrencyList cryptoCurrencyList = restTemplate.getForObject(apiValue, CryptoCurrencyList.class);
        return cryptoCurrencyList;
    }

    /*Implement method getCurrenciesBySymbolFromExternalAPI of CryptoCurrencyService
     * which will return a list of CryptoCurrency objects by their symbol from an external API.
     * Use the RestTemplate to make a GET request to the external API and retrieve the list of CryptoCurrency objects.
     * Use the @Value annotation to inject the API URL from the application.properties file.
     * Use the AppConfig class to create a RestTemplate bean.
     * Symbol will be passed as an query parameter to the API URL.
     */
    @Override
    public CryptoCurrencyList getCurrenciesBySymbolFromExternalAPI(String symbol) {
        RestTemplate restTemplate = appConfig.restTemplate();
        CryptoCurrencyList cryptoCurrencyList = restTemplate.getForObject(apiValue + "?symbol=" + symbol, CryptoCurrencyList.class);
        return cryptoCurrencyList;
    }

    

}

    