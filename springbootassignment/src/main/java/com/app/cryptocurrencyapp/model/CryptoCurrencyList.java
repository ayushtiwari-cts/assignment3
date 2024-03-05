/**
 * Represents a list of cryptocurrency objects.
 */
package com.app.cryptocurrencyapp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoCurrencyList {
    
    private List<CryptoCurrency> data;
}

