package com.mastercard.assessment.citysearch.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.mastercard.assessment.citysearch.service.ICitySearchService;
import org.junit.jupiter.api.Test;

public class CitySearchControllerTest {


    private static final String RESPONSE_NO = "no";

    private static final String RESPONSE_YES = "yes";

    private static final ICitySearchService iCitySearchServiceMock = mock(ICitySearchService.class);

    private static final CitySearchController citySearchControllerMock = new CitySearchController(iCitySearchServiceMock);

    @Test
    public void shouldReturnNoForInvalidOriginCity(){
        String actualResponseForEmptyOrigin = citySearchControllerMock.isConnected("", "destination");
        String actualResponseForNullOrigin = citySearchControllerMock.isConnected(null, "destination");
        assertEquals(actualResponseForEmptyOrigin, RESPONSE_NO);
        assertEquals(actualResponseForNullOrigin, RESPONSE_NO);
    }


    @Test
    public void shouldReturnNoForInvalidDestinationCity(){
        String actualResponseForEmptyDest = citySearchControllerMock.isConnected("origin", "");
        String actualResponseForNullDest = citySearchControllerMock.isConnected("origin", null);
        assertEquals(actualResponseForEmptyDest, RESPONSE_NO);
        assertEquals(actualResponseForNullDest, RESPONSE_NO);
    }

    @Test
    public void shouldReturnNoForInvalidDestinationAndOriginCity(){
        String actualResponseForEmpty = citySearchControllerMock.isConnected("", "");
        String actualResponseForNull = citySearchControllerMock.isConnected(null, null);
        assertEquals(actualResponseForEmpty, RESPONSE_NO);
        assertEquals(actualResponseForNull, RESPONSE_NO);
    }

    @Test
    public void shouldReturnYesForSameValidDestinationAndOriginCities(){
        String actualResponse = citySearchControllerMock.isConnected("city1", "city1");
        assertEquals(actualResponse, RESPONSE_YES);
    }

    @Test
    public void shouldReturnYesIfServiceReturnTrue(){
        final ICitySearchService iCitySearchService = mock(ICitySearchService.class);
        final CitySearchController citySearchController = new CitySearchController(iCitySearchService);
        when(iCitySearchService.isRouteAvailable("CITY1", "CITY2")).thenReturn(true);
        String actualResponse = citySearchController.isConnected("city1", "city2");
        assertEquals(actualResponse, RESPONSE_YES);
        verify(iCitySearchService, times(1)).isRouteAvailable("CITY1", "CITY2");
    }

    @Test
    public void shouldReturnNoIfServiceReturnsNo(){
        final ICitySearchService iCitySearchService = mock(ICitySearchService.class);
        final CitySearchController citySearchController = new CitySearchController(iCitySearchService);
        when(iCitySearchService.isRouteAvailable("CITY1", "CITY2")).thenReturn(false);
        String actualResponse = citySearchController.isConnected("city1", "city2");
        assertEquals(actualResponse, RESPONSE_NO);
        verify(iCitySearchService, times(1)).isRouteAvailable("CITY1", "CITY2");
    }





}
