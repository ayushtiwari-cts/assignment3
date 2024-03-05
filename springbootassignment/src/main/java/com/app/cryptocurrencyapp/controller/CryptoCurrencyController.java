package com.app.cryptocurrencyapp.controller;
/*Create class CryptoCurrencyController.
 * This class is a RestController that handles all the requests for the application.
 * It should create a new instance of the CryptoCurrencyService class and use it to handle the requests.
 * It should implement all the methods of CryptoCurrencyService and map them to the appropriate HTTP methods.
 */
import com.app.cryptocurrencyapp.exceptions.CurrencyAlreadyExistException;
import com.app.cryptocurrencyapp.exceptions.CurrencyNotFoundException;
import com.app.cryptocurrencyapp.model.CryptoCurrency;
import com.app.cryptocurrencyapp.model.CryptoCurrencyList;
import com.app.cryptocurrencyapp.service.CryptoCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
//Add Cross Origin for localhost:5173
@CrossOrigin(origins = "http://localhost:5173")
public class CryptoCurrencyController {
    @Autowired
    private CryptoCurrencyService currencyService;
    @GetMapping
    public List<CryptoCurrency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }
    @GetMapping("/{symbol}")
    public ResponseEntity<CryptoCurrency> getCurrencyBySymbol(@PathVariable String symbol) {
        try {
            return ResponseEntity.ok(currencyService.getCurrencyBySymbol(symbol));
        } catch (CurrencyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping("/saveCurrency")
    public ResponseEntity<CryptoCurrency> addCurrency(@RequestBody CryptoCurrency currency) {
        try {
            return ResponseEntity.ok(currencyService.addCurrency(currency));
        } catch (CurrencyAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @PutMapping("/update/{symbol}")
    public ResponseEntity<CryptoCurrency> updateCurrency(@PathVariable String symbol, @RequestBody CryptoCurrency currency) {
        try {
            return ResponseEntity.ok(currencyService.updateCurrency(currency));
        } catch (CurrencyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/deleteCurrency")
    public ResponseEntity<String> deleteCurrency(@RequestBody CryptoCurrency currency) {
        try {
            currencyService.deleteCurrency(currency.getSymbol());
            return ResponseEntity.ok("Currency with symbol " + currency.getSymbol() + " deleted");
        } catch (CurrencyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @GetMapping("/fetchCurrencies")
    public ResponseEntity<CryptoCurrencyList> fetchCurrenciesFromExternalAPI() {
        return ResponseEntity.ok(currencyService.fetchCurrenciesFromExternalAPI());
    }

    
    @GetMapping("/fetchCurrenciesBySymbol")
    public ResponseEntity<CryptoCurrencyList> getCurrenciesBySymbolFromExternalAPI(@RequestParam("symbol") String symbol) {
        return ResponseEntity.ok(currencyService.getCurrenciesBySymbolFromExternalAPI(symbol));
    }

}
