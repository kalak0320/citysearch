package com.mastercard.assessment.citysearch.controller;

import com.mastercard.assessment.citysearch.service.ICitySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sprint Rest controller for city search operations
 */
@RestController
public class CitySearchController {

    /**
     * City search service for searching if the cities are connected
     */
    private final ICitySearchService iCitySearchService;

    @Autowired
    public CitySearchController(ICitySearchService iCitySearchService){
        this.iCitySearchService = iCitySearchService;
    }

    /**
     * Uses iCitySearchService bean to find if the cities provided as request params are connected or not.
     * if the service returns tru then "Yes" is returned and "No" if the service returns false or if the input is invalid
     * . If the same origin and destination are provided then Yes is returned as response.
     * @param origin Origin city
     * @param destination Destination city
     * @return Return text Yes or no as response
     */
    @RequestMapping(value = "/connected", method = RequestMethod.GET)
    public String isConnected(@RequestParam  final String origin, @RequestParam  final String destination){
        if(!isValidInput(origin, destination)) {
            return "no";
        }
        final String originCity = origin.toUpperCase().trim();
        final String destinationCity = destination.toUpperCase().trim();
        if(origin.equals(destination)){
            return "yes";
        } else if(iCitySearchService.isRouteAvailable(originCity, destinationCity)){
            return "yes";
        } else {
            return "no";
        }
    }

    private boolean isValidInput(final String origin, final String destination){
        return !StringUtils.isEmpty(origin) && !StringUtils.isEmpty(destination);
    }

}
