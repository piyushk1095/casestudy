package com.trivago.casestudy.util;

import com.trivago.casestudy.dto.Price;
import com.trivago.casestudy.exception.BusinessException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class PriceLoaderTest {
    @Mock
    Map<String, List<Price>> priceMap;
    @InjectMocks
    PriceLoader priceLoader;
    private String testFilePath = "src/test/resources/static/prices/";

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadfiles() throws Exception {
        Map<String, List<Price>> priceMap = priceLoader.loadfiles();
        System.out.print(priceMap.size());
        Assertions.assertNotNull(priceMap);
        Assertions.assertFalse(priceMap.isEmpty());
    }

    @Test
    public void testLoadPricesFromJsonFile() throws Exception {
        priceLoader.loadPricesFromJsonFile();
    }

    @Test
    public void testDataLoadFromYamlFile() throws Exception {
        priceLoader.dataLoadFromYamlFile();
    }

    @Test
    public void testLoadFilesThrowsBusinessExceptionOnJsonFileRead() {
        priceLoader.path = "non-existent-path/";
        Assertions.assertThrows(BusinessException.class, () -> priceLoader.loadfiles());
    }

}
