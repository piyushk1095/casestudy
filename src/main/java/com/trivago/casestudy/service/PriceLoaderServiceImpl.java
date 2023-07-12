package com.trivago.casestudy.service;

import com.trivago.casestudy.dto.Price;
import com.trivago.casestudy.util.PriceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PriceLoaderServiceImpl implements PriceLoaderService {
    @Autowired
    public PriceLoader priceLoaderService;

    @Override
    public List<Price> loadPricesFromFile(String accommodationID) {
        Map<String, List<Price>> accommodations = priceLoaderService.loadfiles();
        List<Price> priceList = accommodations.entrySet()
                    .stream()
                    .filter(entry -> entry.getKey().equals(accommodationID))
                    .flatMap(entry -> entry.getValue().stream())
                    .collect(Collectors.toList());
        return priceList;
    }
}
