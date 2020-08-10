package com.mastercard.assessment.citysearch.service;

import com.mastercard.assessment.citysearch.graph.CityRoutesGraph;
import com.mastercard.assessment.citysearch.graph.Route;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service to load the local file based data to provide the city search functionality. This class loads the file from
 * resources and uses it as inout to construct the graph.
 */
@Component
public class LocalFileBasedCitySearchService implements ICitySearchService{

    private CityRoutesGraph cityRoutesGraph;

    @Autowired
    public LocalFileBasedCitySearchService(@Value("${data.file.name}") String dataFileName) throws IOException {
        cityRoutesGraph =  new CityRoutesGraph(getRoutesListFromFile(dataFileName));
    }

    @Override
    public boolean isRouteAvailable(String originCity, String destinationCity) {
        Route route = new Route.Builder()
                .withOriginCity(originCity)
                .withDestinationCity(destinationCity)
                .build();
        return cityRoutesGraph.isRouteAvailable(route);
    }

    private List<Route> getRoutesListFromFile(String fileName) throws IOException{
        Resource resource =new ClassPathResource(fileName);
        InputStream inputStream = resource.getInputStream();
        List<String> lines = IOUtils.readLines(inputStream, "UTF-8");
        return lines.stream().map(line -> {
            String[] cities = line.split(",");
            String originCity = cities[0].trim().toUpperCase();
            String destinationCity = cities[1].trim().toUpperCase();
            return new Route.Builder()
                    .withOriginCity(originCity)
                    .withDestinationCity(destinationCity)
                    .build();
        }).collect(Collectors.toList());
    }
}
