package com.recruitment.crypto.controllers;


import com.recruitment.crypto.Handlers.ResponseHandler;
import com.recruitment.crypto.model.Currency;
import com.recruitment.crypto.properties.PropertyReader;
import io.coinapi.rest.Exchange_rate;
import io.coinapi.rest.REST_methods;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CryptoController {
    PropertyReader propertyReader = new PropertyReader("api.properties");
    REST_methods coinApiRest = new REST_methods(propertyReader.readProperty("api.key"));
    GlobalControllerExceptionHandler exceptionHandler = new GlobalControllerExceptionHandler();

    @GetMapping("/{currency}")
    public ResponseEntity<Object> getRates(
            @PathVariable(value = "currency") String currency,
            @RequestParam(required = false) List<String> filter,
            WebRequest request) {
        Exchange_rate[] rates = new Exchange_rate[0];
        if (filter != null && !filter.isEmpty()){
            try{
                rates = new Exchange_rate[filter.size()];
                for (int i = 0; i < filter.size(); i++) {
                    rates[i] = coinApiRest.get_exchange_rate(currency, filter.get(i));
                }
            } catch (IOException exception) {
                exceptionHandler.handleIOException(exception, request);
            }
        } else {
            try{
                rates = coinApiRest.get_all_exchange_rates(currency);
                if(rates.length == 0) { throw new NullPointerException();}
            } catch (IOException exception){
                exceptionHandler.handleIOException(exception, request);
            } catch (NullPointerException exception) {
                exceptionHandler.handleNullPointerException(exception, request);
            }
        }
        return ResponseHandler.getRates(HttpStatus.OK, rates);
    }

    @PostMapping(
            value = "/exchange", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> getExchangeRates(@RequestBody String parameters, WebRequest request){
        List<Currency> currencies = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(parameters);
        String assetId = jsonObject.getString("from");

        try{
            JSONArray jsonArray = jsonObject.getJSONArray("to");
            Double amount = jsonObject.getDouble("amount");

            String name;
            Double rate;
            for (int i = 0; i < jsonArray.length(); i++) {
                name = jsonArray.getString(i);
                rate = coinApiRest.get_exchange_rate(assetId, name).get_rate();
                currencies.add(new Currency(name, rate, amount));
            }
        } catch (IOException exception){
            exceptionHandler.handleIOException(exception, request);
        }

        return ResponseHandler.getExchangeRates(HttpStatus.OK, assetId, currencies);
    }
}
