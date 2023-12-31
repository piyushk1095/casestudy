package com.trivago.casestudy.service;

import com.trivago.casestudy.dto.Price;
import com.trivago.casestudy.exception.BusinessException;
import com.trivago.casestudy.util.PriceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PriceLoaderServiceImpl implements PriceLoaderService {
    @Autowired
    public PriceLoader priceLoader;

    @Override
    public List<Price> loadPricesFromFile(String accommodationID) throws BusinessException {
        Map<String, List<Price>> accommodationsIdVsPriceMapping = priceLoader.loadfiles();
        return accommodationsIdVsPriceMapping.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(accommodationID))
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toList());
    }
}
