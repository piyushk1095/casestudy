package com.trivago.casestudy.controller;

import com.trivago.casestudy.exception.BusinessException;
import com.trivago.casestudy.service.PriceLoaderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class PriceController {

  @Autowired
  public PriceLoaderServiceImpl priceLoaderService;

  @GetMapping("/prices/{accommodationId}")
  public List<?> getPrices(@PathVariable int accommodationId) throws BusinessException {
    List<?> prices = priceLoaderService.loadPricesFromFile(String.valueOf(accommodationId));
    if (prices.isEmpty()) {
      return Collections.singletonList("No prices found for the accommodation Id.");
    }
    return prices;
  }
}
