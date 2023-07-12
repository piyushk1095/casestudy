package com.trivago.casestudy.service;

import com.trivago.casestudy.dto.Price;
import com.trivago.casestudy.exception.BusinessException;

import java.util.List;

public interface PriceLoaderService {

    public List<Price> loadPricesFromFile(String accommodationID) throws BusinessException;
}
