package com.recruitment.crypto.Handlers;

import com.recruitment.crypto.model.Currency;
import io.coinapi.rest.Exchange_rate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> getRates(HttpStatus status, Exchange_rate[] rates) {
        Map<String, Object> response= new HashMap<String, Object>();
        response.put("source", rates[0].get_asset_id_base());
        for ( Exchange_rate rate: rates) {
            response.put(rate.get_asset_id_quote(), rate.get_rate());
        }
        return new ResponseEntity<Object>(response, status);
    }
    public static ResponseEntity<Object> getExchangeRates(HttpStatus status, String assetId, List<Currency> currencies) {
        Map<String, Object> response= new HashMap<String, Object>();
        response.put("from", assetId);
        for ( Currency currency: currencies) {
            response.put("Name", currency.getName());
            response.put("Rate", currency.getRate());
            response.put("Amount", currency.getAmount());
            response.put("Fee", Currency.getFee());
            response.put("Result", currency.getResult());
        }
        return new ResponseEntity<Object>(response, status);
    }
}
