package com.trivago.casestudy.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.trivago.casestudy.dto.Accommodation;
import com.trivago.casestudy.dto.Advertiser;
import com.trivago.casestudy.dto.Price;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PriceLoader {

    private String path = "src/main/resources/static/prices/";
    private String partnar1 = "advertiser_100.yaml";
    private String partnar2 = "advertiser_200.json";
    private Map<String, List<Price>>  priceMap=new HashMap<>();

    public Map<String, List<Price>>  loadfiles()
    {
        loadPricesFromJsonFile();
        dataLoadFromYamlFile();
        return priceMap;
    }

    public void loadPricesFromJsonFile(){
        try {
            File file = new File(path+partnar2);
            ObjectMapper objectMapper = new ObjectMapper();
            Advertiser advertiserDTO = objectMapper.readValue(file, Advertiser.class);
            for (Accommodation accommodation: advertiserDTO.getAccommodation()) {
                priceMap.put(accommodation.getId(),accommodation.getPrices());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void dataLoadFromYamlFile() {
        try {
            ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
            Advertiser advertiser = yamlMapper.readValue(new File(path+partnar1), Advertiser.class);
            for (Accommodation accommodation: advertiser.getAccommodation()) {
                priceMap.put(accommodation.getId(),accommodation.getPrices());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

