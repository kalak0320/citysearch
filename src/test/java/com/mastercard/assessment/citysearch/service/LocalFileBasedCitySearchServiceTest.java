package com.mastercard.assessment.citysearch.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LocalFileBasedCitySearchServiceTest {

    LocalFileBasedCitySearchService localFileBasedCitySearchService;

    @BeforeAll
    public void setup() throws Exception{
        localFileBasedCitySearchService = new LocalFileBasedCitySearchService("city_test.txt");
    }

    @Test
    public void shouldReturnTrueIfTheRouteExistsinFile(){
        boolean result = localFileBasedCitySearchService.isRouteAvailable("BOSTON", "NEWARK");
        assertTrue(result);
    }


    @Test
    public void shouldReturnFalseIfTheRouteExistsinFile(){
        boolean result = localFileBasedCitySearchService.isRouteAvailable("BOSTON", "TRENTON");
        assertFalse(result);
    }

}
