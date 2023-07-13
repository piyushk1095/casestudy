package com.trivago.casestudy.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.trivago.casestudy.dto.Accommodation;
import com.trivago.casestudy.dto.Advertiser;
import com.trivago.casestudy.dto.Price;
import com.trivago.casestudy.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PriceLoader {
    @Value("${file.path}")
    String path;
    @Value("${file.partnar1}")
    String partnar1;
    @Value("${file.partnar2}")
    String partnar2;

    private Map<String, List<Price>>  priceMap=new HashMap<>();

    public Map<String, List<Price>>  loadfiles() throws BusinessException {
        loadPricesFromJsonFile();
        dataLoadFromYamlFile();
        return priceMap;
    }

    public void loadPricesFromJsonFile() throws BusinessException {
        try {
            File file = new File(path+partnar2);
            ObjectMapper objectMapper = new ObjectMapper();
            Advertiser advertiserDTO = objectMapper.readValue(file, Advertiser.class);
            for (Accommodation accommodation: advertiserDTO.getAccommodation()) {
                priceMap.put(accommodation.getId(),accommodation.getPrices());
            }
        } catch (IOException e) {
            throw new BusinessException("Please try with valid data given");
        }
    }


    public void dataLoadFromYamlFile() throws BusinessException {
        try {
            ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
            Advertiser advertiser = yamlMapper.readValue(new File(path+partnar1), Advertiser.class);
            for (Accommodation accommodation: advertiser.getAccommodation()) {
                priceMap.put(accommodation.getId(),accommodation.getPrices());
            }
        } catch (IOException e) {
            throw new BusinessException("Please try with valid data given");

        }
    }
}

