package com.trivago.casestudy;

import com.trivago.casestudy.dto.Price;
import com.trivago.casestudy.exception.BusinessException;
import com.trivago.casestudy.service.PriceLoaderService;
import com.trivago.casestudy.service.PriceLoaderServiceImpl;
import com.trivago.casestudy.util.PriceLoader;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestPriceLoaderService {
    @Mock
    private PriceLoader priceLoader;

    @InjectMocks
    private PriceLoaderServiceImpl priceLoaderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadPricesFromFile_ValidAccommodationID() throws BusinessException {
        String accommodationID = "123";
        List<Price> expectedPrices = new ArrayList<>();
        expectedPrices.add(new Price("USD", "100.0"));
        expectedPrices.add(new Price("EUR", "90.0"));
        Map<String, List<Price>> accommodations = new HashMap<>();
        accommodations.put(accommodationID, expectedPrices);
        when(priceLoader.loadfiles()).thenReturn(accommodations);
        List<Price> actualPrices = priceLoaderService.loadPricesFromFile(accommodationID);
        assertEquals(expectedPrices, actualPrices);
    }

    @Test
    void testLoadPricesFromFile_InvalidAccommodationID() throws BusinessException {
        // Arrange
        String accommodationID = "456";
        Map<String, List<Price>> accommodations = new HashMap<>();
        accommodations.put("123", new ArrayList<>());
        when(priceLoader.loadfiles()).thenReturn(accommodations);
        List<Price> actualPrices = priceLoaderService.loadPricesFromFile(accommodationID);
        assertEquals(0, actualPrices.size());
    }
}
