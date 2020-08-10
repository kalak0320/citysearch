package com.mastercard.assessment.citysearch.service;

/**
 * Interface to implement creating the ICitySearchService
 */
public interface ICitySearchService {

    /**
     * Verifies if the cities are connected or not.
     * @param originCity
     * @param destinationCity
     * @return
     */
    boolean isRouteAvailable(String originCity, String destinationCity);

}
